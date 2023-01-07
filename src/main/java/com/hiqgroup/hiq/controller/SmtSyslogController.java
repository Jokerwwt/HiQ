package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.SmtSyslog;
import com.hiqgroup.hiq.entity.SmtUser;
import com.hiqgroup.hiq.form.PagedQueryForm;
import com.hiqgroup.hiq.form.SmtSyslogForm;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtMenuService;
import com.hiqgroup.hiq.service.SmtSyslogService;
import com.hiqgroup.hiq.service.SmtUserService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统日志表(SmtSyslog)表控制层
 *
 * @author liugaqiong
 * @since 2021-05-04 21:59:15
 */
@RestController
@RequestMapping("smtSyslog")
public class SmtSyslogController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private SmtSyslogService smtSyslogService;
    @Resource
    private SmtUserService smtUserService;
    @Resource
    private SmtMenuService smtMenuService;
    @Resource
    private HqtStatisticService hqtStatisticService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtSyslog selectOne(String id) {
        return this.smtSyslogService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) throws ParseException {
        SmtSyslog smtSyslogParam = new SmtSyslog();
//        根据用户名查询
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        SmtUser smtUserParam = new SmtUser();
        if ((search.get("username") != null) && !search.get("username").equals("")) {
            smtUserParam.setUsername(search.get("username").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                smtSyslogParam.setUserid(smtUsers.get(0).getId());
            }else{
                smtSyslogParam.setUserid("###");
            }
        }
//        根据操作内容查询
        smtSyslogParam.setOperate(search.get("operate").toString());
//        时间查询
        List<Map<String, List<String>>> betweens = new ArrayList<>();
        if ((search.get("operateTimeB") != null) && (search.get("operateTimeE") != null)){
            List<String> betweenData = new ArrayList<>();
            betweenData.add(search.get("operateTimeB").toString());
            betweenData.add((search.get("operateTimeE").toString().trim().equals("")? "3000-12-31":search.get("operateTimeE").toString()));
            Map<String, List<String>> between = new HashMap();
            between.put("OperateTime",betweenData);
            betweens.add(between);
        }
        try{
            List<SmtSyslog> smtSyslogs =this.smtSyslogService.queryAllByLimit(smtSyslogParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(),param.getOrdertype(),null, betweens);
            List<SmtSyslogForm> smtSyslogForms = new ArrayList<>();
            for (SmtSyslog smtSyslog: smtSyslogs) {
                SmtSyslogForm smtSyslogForm = new SmtSyslogForm();
                CommonUtil.copyObjProperties(smtSyslogForm,smtSyslog);
                smtSyslogForm.setUsername((this.smtUserService.queryById(smtSyslog.getUserid()) == null)? "未知" :
                        this.smtUserService.queryById(smtSyslog.getUserid()).getUsername());
                smtSyslogForm.setMenuname((smtSyslog.getMenuid() == null)? "":this.smtMenuService.queryById(smtSyslog.getMenuid()).getMenuuname());
                smtSyslogForms.add(smtSyslogForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total",this.smtSyslogService.getCount(smtSyslogParam,null,betweens));
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",smtSyslogForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取系统日志出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 批量删除数据
     ** @param syslogIds
     * @return 更新反馈
     */
    @ApiOperation(value = "删除操作日志信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String>  syslogIds, HttpServletRequest request) {
        try {
            for (String syslogId:syslogIds) {
                this.smtSyslogService.deleteById(syslogId);
            }
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtSysLog",request.getRemoteAddr(),
                    "删除【"+syslogIds.size()+"条】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }
}
