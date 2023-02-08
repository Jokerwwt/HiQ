package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HpgDailytype;
import com.hiqgroup.hiq.form.PagedQueryForm;
import com.hiqgroup.hiq.service.HpgDailytypeService;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * 日常动态类别(HpgDailytype)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-16 22:12:00
 */
@RestController
@RequestMapping("hpgDailytype")
public class HpgDailytypeController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HpgDailytypeService hpgDailytypeService;
    @Resource
    private HqtStatisticService hqtStatisticService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HpgDailytype selectOne(String id) {
        return this.hpgDailytypeService.queryById(id);
    }

    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param){
        HpgDailytype hpgDailytype=new HpgDailytype();
        try{
            List<HpgDailytype> hpgDailytypes=this.hpgDailytypeService.queryAllByLimit(
                    hpgDailytype,(param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(), null, null
            );
            JSONObject resdata=new JSONObject();
            resdata.put("total",this.hpgDailytypeService.getCount(hpgDailytype, null, null));
            resdata.put("newtoken",jwtConfig.getNewToken());
            resdata.put("data",hpgDailytypes);
            return ResultUtil.success(resdata);
        }catch (Exception e){
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取日常动态类别出错！"+e.getMessage(), resData);
        }
    }


    @ApiOperation(value = "删除日常动态类别信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> dailyTypeIds, HttpServletRequest request) {
        try {
            for (String dailyTypeId:dailyTypeIds) {
                HpgDailytype hpgDailytype = this.hpgDailytypeService.queryById(dailyTypeId);
                this.hpgDailytypeService.deleteById(dailyTypeId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgDailyType",request.getRemoteAddr(),
                        "删除【"+hpgDailytype.getTypename()+"】", request.getHeader("User-Agent"));
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

    @ApiOperation(value = "新增日常动态类别信息")
    @PostMapping("create")
    public Result create(@RequestBody HpgDailytype hpgDailytype, HttpServletRequest request) {
        try {
            hpgDailytype.setId(UUID.randomUUID().toString());
            this.hpgDailytypeService.insert(hpgDailytype);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgDailyType",request.getRemoteAddr(),
                    "新增【"+hpgDailytype.getTypename()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }

    /**
     * 通过主键更新单条数据
     ** @param
     * @return 更新反馈
     */
    @ApiOperation(value = "更新日常动态类别信息")
    @PostMapping("update")
    public Result update(@RequestBody HpgDailytype hpgDailytype, HttpServletRequest request) {
        try {
            this.hpgDailytypeService.update(hpgDailytype);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgDailyType",request.getRemoteAddr(),
                    "修改【"+hpgDailytype.getTypename()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }

    /**
     * 获取全部数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAll")
    public Result queryAll() {
        try {
            List<HpgDailytype> hpgDailytypes = this.hpgDailytypeService.queryAll(new HpgDailytype());
            JSONObject resData = new JSONObject();
            resData.put("total",hpgDailytypes.size());
            resData.put("data",hpgDailytypes);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取日常动态类别出错！"+e.getMessage(), resData);
        }
    }

}
