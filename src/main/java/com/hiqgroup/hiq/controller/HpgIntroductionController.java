package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HpgIntroduction;
import com.hiqgroup.hiq.form.HpgIntroductionForm;
import com.hiqgroup.hiq.service.HpgIntroductionService;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtUserService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 平台介绍(HpgIntroduction)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-16 22:12:02
 */
@RestController
@RequestMapping("hpgIntroduction")
public class HpgIntroductionController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HpgIntroductionService hpgIntroductionService;
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
    public HpgIntroduction selectOne(String id) {
        return this.hpgIntroductionService.queryById(id);
    }

    /**
     * 获取平台介绍
     *
     * @return 数据列表
     */
    @ApiOperation(value = "获取平台介绍")
    @GetMapping("getIntroduction")
    public Result getIntroduction() {
        try {
            List<HpgIntroduction> hpgIntroductions = this.hpgIntroductionService.queryAll(new HpgIntroduction());
            HpgIntroductionForm hpgIntroductionForm = null;
            if (hpgIntroductions.size()>0) {
                hpgIntroductionForm = new HpgIntroductionForm();
                CommonUtil.copyObjProperties(hpgIntroductionForm,hpgIntroductions.get(0));
                hpgIntroductionForm.setEdtbyusername((this.smtUserService.queryById(hpgIntroductions.get(0).getEdtbyuserid()) == null)? "" :
                        this.smtUserService.queryById(hpgIntroductions.get(0).getEdtbyuserid()).getUsername());
                hpgIntroductionForm.setPublishername((this.smtUserService.queryById(hpgIntroductions.get(0).getPublisher()) == null)? "" :
                        this.smtUserService.queryById(hpgIntroductions.get(0).getPublisher()).getUsername());
                hpgIntroductionForm.setWritername((this.smtUserService.queryById(hpgIntroductions.get(0).getWriter()) == null)? "" :
                        this.smtUserService.queryById(hpgIntroductions.get(0).getWriter()).getUsername());
            }
            JSONObject resData = new JSONObject();
            resData.put("data",hpgIntroductionForm);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取平台介绍出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 设置平台介绍
     ** @param hpgIntroductionForm
     * @return 设置反馈
     */
    @ApiOperation(value = "设置平台介绍")
    @PostMapping("setIntroduction")
    public Result setIntroduction(@RequestBody HpgIntroductionForm hpgIntroductionForm, HttpServletRequest request) {
        HpgIntroduction hpgIntroduction = this.hpgIntroductionService.queryById(hpgIntroductionForm.getId());
        JSONObject resData = new JSONObject();
        try {
            Map currentUser = jwtConfig.getCurrentUser();
            if (hpgIntroduction == null){  //新增
                hpgIntroduction = new HpgIntroduction();
                CommonUtil.copyObjProperties(hpgIntroduction,hpgIntroductionForm);
                hpgIntroduction.setId(UUID.randomUUID().toString());
                hpgIntroduction.setEdtbyuserid(currentUser.get("id").toString());
                hpgIntroduction.setWriter(currentUser.get("id").toString());
                Date date =new Date();
                hpgIntroduction.setEdttime(date);
                if (hpgIntroductionForm.getStatus().equals("1")){
                    hpgIntroduction.setPublishdate(null);
                }else{
                    hpgIntroduction.setPublishdate(date);
                }
                this.hpgIntroductionService.insert(hpgIntroduction);
                resData.put("id",hpgIntroduction.getId());
                resData.put("writer",currentUser.get("id").toString());
                resData.put("writername",currentUser.get("username").toString());
            }else{  //修改
                CommonUtil.copyObjProperties(hpgIntroduction,hpgIntroductionForm);
                hpgIntroduction.setEdtbyuserid(currentUser.get("id").toString());
                Date date =new Date();
                hpgIntroduction.setEdttime(date);
                if (hpgIntroductionForm.getStatus().equals("1")){
                    hpgIntroduction.setPublishdate(null);
                }else{
                    hpgIntroduction.setPublishdate(date);
                }
                this.hpgIntroductionService.update(hpgIntroduction);
                resData.put("id",hpgIntroduction.getId());
                resData.put("writer",hpgIntroductionForm.getWriter());
                resData.put("writername",hpgIntroductionForm.getWritername());
            }
            if (hpgIntroductionForm.getStatus().equals("1")){
                resData.put("publishername", "");
                resData.put("publishdate",null);
                resData.put("status", "1");
            } else {
                resData.put("publishername", (this.smtUserService.queryById(hpgIntroductionForm.getPublisher()) == null)? "": this.smtUserService.queryById(hpgIntroductionForm.getPublisher()).getUsername());
                resData.put("publishdate",hpgIntroduction.getPublishdate());
                resData.put("status", "2");
            }
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgIntroduction",request.getRemoteAddr(),
                    "修改【平台介绍】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success(resData);
        }catch (Exception e) {
            resData.put("id",hpgIntroduction.getId());
            resData.put("writername",hpgIntroductionForm.getWritername());
            resData.put("publishername",hpgIntroductionForm.getPublishername());
            resData.put("publishdate",null);
            resData.put("status", "1");
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"设置平台介绍出错！"+e.getMessage(),resData);
        }
    }

    /**
     * 获取平台介绍显示到前端
     *
     * @return 数据列表
     */
    @ApiOperation(value = "获取平台介绍显示到前端")
    @GetMapping("showIntroduction")
    public Result showIntroduction() {
        HpgIntroduction hpgIntroductionParam = new HpgIntroduction();
        hpgIntroductionParam.setStatus("2");
        try {
            List<HpgIntroduction> hpgIntroductions = this.hpgIntroductionService.queryAll(hpgIntroductionParam);
            HpgIntroductionForm hpgIntroductionForm = null;
            if (hpgIntroductions.size()>0) {
                if (!hpgIntroductions.get(0).getPublisher().equals("")) {
                    hpgIntroductions.get(0).setViewtotal(((hpgIntroductions.get(0).getViewtotal() == null)? 0:hpgIntroductions.get(0).getViewtotal())+1);
                    this.hpgIntroductionService.update(hpgIntroductions.get(0));
                    hpgIntroductionForm = new HpgIntroductionForm();
                    CommonUtil.copyObjProperties(hpgIntroductionForm, hpgIntroductions.get(0));
                    hpgIntroductionForm.setEdtbyusername((this.smtUserService.queryById(hpgIntroductions.get(0).getEdtbyuserid()) == null)? "" :
                            this.smtUserService.queryById(hpgIntroductions.get(0).getEdtbyuserid()).getUsername());
                    hpgIntroductionForm.setPublishername((this.smtUserService.queryById(hpgIntroductions.get(0).getPublisher()) == null)? "" :
                            this.smtUserService.queryById(hpgIntroductions.get(0).getPublisher()).getUsername());
                    hpgIntroductionForm.setWritername((this.smtUserService.queryById(hpgIntroductions.get(0).getWriter()) == null)? "" :
                            this.smtUserService.queryById(hpgIntroductions.get(0).getWriter()).getUsername());
                }
            }
            JSONObject resData = new JSONObject();
            resData.put("data",hpgIntroductionForm);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取平台介绍出错！"+e.getMessage(), resData);
        }
    }
}
