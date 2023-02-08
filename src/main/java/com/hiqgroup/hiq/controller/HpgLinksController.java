package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HpgLinks;
import com.hiqgroup.hiq.form.HpgLinksForm;
import com.hiqgroup.hiq.form.PagedQueryForm;
import com.hiqgroup.hiq.service.HpgLinksService;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtUserService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 友情链接(HpgLinks)表控制层
 *
 * @author liugaqiong
 * @since 2021-12-26 16:31:40
 */
@RestController
@RequestMapping("hpgLinks")
public class HpgLinksController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HpgLinksService hpgLinksService;
    @Resource
    private SmtUserService smtUserService;
    @Resource
    private HqtStatisticService hqtStatisticService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HpgLinks selectOne(String id) {
        return this.hpgLinksService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) {
        HpgLinks hpgLinksParam = new HpgLinks();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hpgLinksParam.setTitle("%"+search.get("title").toString()+"%");
        hpgLinksParam.setStatus(search.get("status").toString());
        try {
            List<HpgLinks> hpgLinksList = this.hpgLinksService.queryAllByLimit(hpgLinksParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(),null,null);
            List<HpgLinksForm> hpgLinksForms = new ArrayList<>();
            for (HpgLinks hpgLinks: hpgLinksList) {
                HpgLinksForm hpgLinksForm = new HpgLinksForm();
                CommonUtil.copyObjProperties(hpgLinksForm,hpgLinks);
                hpgLinksForm.setEdtbyusername((this.smtUserService.queryById(hpgLinks.getEdtbyuserid()) == null)? "" :
                        this.smtUserService.queryById(hpgLinks.getEdtbyuserid()).getUsername());
                hpgLinksForms.add(hpgLinksForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total",this.hpgLinksService.getCount(hpgLinksParam,null,null));
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",hpgLinksForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取友情链接出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     ** @param hpgLinksForm
     * @return 更新反馈
     */
    @ApiOperation(value = "更新友情链接信息")
    @PostMapping("update")
    public Result update(@RequestBody HpgLinksForm hpgLinksForm, HttpServletRequest request) {
        HpgLinks hpgLinks = this.hpgLinksService.queryById(hpgLinksForm.getId());
        try {
            CommonUtil.copyObjProperties(hpgLinks,hpgLinksForm);
            Map currentUser = jwtConfig.getCurrentUser();
            hpgLinks.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            hpgLinks.setEdttime(date);
            this.hpgLinksService.update(hpgLinks);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgLinks",request.getRemoteAddr(),
                    "修改【"+hpgLinks.getTitle()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("edttime",date);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("edttime",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage(),resData);
        }
    }

    /**
     * 通过主键新增单条数据
     ** @param hpgLinksForm
     * @return 更新反馈
     */
    @ApiOperation(value = "新增友情链接信息")
    @PostMapping("create")
    public Result create(@RequestBody HpgLinksForm hpgLinksForm, HttpServletRequest request) {
        HpgLinks hpgLinks = new HpgLinks();
        try {
            CommonUtil.copyObjProperties(hpgLinks,hpgLinksForm);
            hpgLinks.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            hpgLinks.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            hpgLinks.setEdttime(date);
            this.hpgLinksService.insert(hpgLinks);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgLinks",request.getRemoteAddr(),
                    "新增【"+hpgLinks.getTitle()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("edttime",date);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("edttime",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage(),resData);
        }
    }

    /**
     * 批量删除数据
     ** @param linkIds
     * @return 更新反馈
     */
    @ApiOperation(value = "删除友情链接信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> linkIds, HttpServletRequest request) {
        try {
            for (String linkId:linkIds) {
                HpgLinks hpgLinks = this.hpgLinksService.queryById(linkId);
                this.hpgLinksService.deleteById(linkId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgLinks",request.getRemoteAddr(),
                        "删除【"+hpgLinks.getTitle()+"】", request.getHeader("User-Agent"));
                if (!syslogRes.equals("")){
                    return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
                }
                //---------------------
            }
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("getLinkList")
    public Result getLinkList() {
        HpgLinks hpgLinksParam = new HpgLinks();
        hpgLinksParam.setStatus("1");
        try {
            List<HpgLinks> hpgLinksList = this.hpgLinksService.queryAll(hpgLinksParam);
            JSONObject resData = new JSONObject();
            resData.put("data", hpgLinksList);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取友情链接出错！" + e.getMessage(), resData);
        }
    }
}
