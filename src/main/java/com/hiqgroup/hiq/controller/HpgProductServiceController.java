package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HpgProductService;
import com.hiqgroup.hiq.form.HpgProductServiceForm;
import com.hiqgroup.hiq.service.HpgProductServiceService;
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
 * 产品与服务(HpgProductService)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-16 22:12:03
 */
@RestController
@RequestMapping("hpgProductService")
public class HpgProductServiceController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HpgProductServiceService hpgProductServiceService;
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
    public HpgProductService selectOne(String id) {
        return this.hpgProductServiceService.queryById(id);
    }

    /**
     * 获取产品与服务
     *
     * @return 数据列表
     */
    @ApiOperation(value = "获取产品与服务")
    @GetMapping("getProductService")
    public Result getProductService() {
        try {
            List<HpgProductService> hpgProductServices = this.hpgProductServiceService.queryAll(new HpgProductService());
            HpgProductServiceForm hpgProductServiceForm = null;
            if (hpgProductServices.size()>0) {
                hpgProductServiceForm = new HpgProductServiceForm();
                CommonUtil.copyObjProperties(hpgProductServiceForm,hpgProductServices.get(0));
                hpgProductServiceForm.setEdtbyusername((this.smtUserService.queryById(hpgProductServices.get(0).getEdtbyuserid()) == null)? "" :
                        this.smtUserService.queryById(hpgProductServices.get(0).getEdtbyuserid()).getUsername());
                hpgProductServiceForm.setPublishername((this.smtUserService.queryById(hpgProductServices.get(0).getPublisher()) == null)? "" :
                        this.smtUserService.queryById(hpgProductServices.get(0).getPublisher()).getUsername());
                hpgProductServiceForm.setWritername((this.smtUserService.queryById(hpgProductServices.get(0).getWriter()) == null)? "" :
                        this.smtUserService.queryById(hpgProductServices.get(0).getWriter()).getUsername());
            }
            JSONObject resData = new JSONObject();
            resData.put("data",hpgProductServiceForm);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取产品与服务出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 设置产品与服务
     ** @param hpgProductServiceForm
     * @return 设置反馈
     */
    @ApiOperation(value = "设置产品与服务")
    @PostMapping("setProductService")
    public Result setProductService(@RequestBody HpgProductServiceForm hpgProductServiceForm, HttpServletRequest request) {
        HpgProductService hpgProductService = this.hpgProductServiceService.queryById(hpgProductServiceForm.getId());
        JSONObject resData = new JSONObject();
        try {
            Map currentUser = jwtConfig.getCurrentUser();
            if (hpgProductService == null){
                hpgProductService = new HpgProductService();
                CommonUtil.copyObjProperties(hpgProductService,hpgProductServiceForm);
                hpgProductService.setId(UUID.randomUUID().toString());
                hpgProductService.setEdtbyuserid(currentUser.get("id").toString());
                hpgProductService.setWriter(currentUser.get("id").toString());
                Date date =new Date();
                hpgProductService.setEdttime(date);
                if (hpgProductServiceForm.getStatus().equals("1")){
                    hpgProductService.setPublishdate(null);
                }else{
                    hpgProductService.setPublishdate(date);
                }
                this.hpgProductServiceService.insert(hpgProductService);
                resData.put("writer",currentUser.get("id").toString());
                resData.put("writername",currentUser.get("username").toString());
            }else{
                CommonUtil.copyObjProperties(hpgProductService,hpgProductServiceForm);
                hpgProductService.setEdtbyuserid(currentUser.get("id").toString());
                Date date =new Date();
                hpgProductService.setEdttime(date);
                if (hpgProductServiceForm.getStatus().equals("1")){
                    hpgProductService.setPublishdate(null);
                }else{
                    hpgProductService.setPublishdate(date);
                }
                this.hpgProductServiceService.update(hpgProductService);
                resData.put("writer",hpgProductServiceForm.getWriter());
                resData.put("writername",hpgProductServiceForm.getWritername());
            }
            if (hpgProductServiceForm.getStatus().equals("1")){
                resData.put("publishername", "");
                resData.put("publishdate",null);
                resData.put("status", "1");
            } else {
                resData.put("publishername", (this.smtUserService.queryById(hpgProductServiceForm.getPublisher()) == null)? "": this.smtUserService.queryById(hpgProductServiceForm.getPublisher()).getUsername());
                resData.put("publishdate",hpgProductService.getPublishdate());
                resData.put("status", "2");
            }
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgProductService",request.getRemoteAddr(),
                    "修改【产品与服务】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success(resData);
        }catch (Exception e) {
            resData.put("writername",hpgProductServiceForm.getWritername());
            resData.put("publishername",hpgProductServiceForm.getPublishername());
            resData.put("publishdate",null);
            resData.put("status", "1");
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"设置产品与服务出错！"+e.getMessage(),resData);
        }
    }

    /**
     * 获取产品与服务显示到前端
     *
     * @return 数据列表
     */
    @ApiOperation(value = "获取产品与服务显示到前端")
    @GetMapping("showProductService")
    public Result showProductService() {
        HpgProductService hpgProductServiceParam = new HpgProductService();
        hpgProductServiceParam.setStatus("2");
        try {
            List<HpgProductService> hpgProductServices = this.hpgProductServiceService.queryAll(hpgProductServiceParam);
            HpgProductServiceForm hpgProductServiceForm = null;
            if (hpgProductServices.size()>0) {
                if (!hpgProductServices.get(0).getPublisher().equals("")) {
                    hpgProductServices.get(0).setViewtotal(((hpgProductServices.get(0).getViewtotal() == null)? 0:hpgProductServices.get(0).getViewtotal())+1);
                    this.hpgProductServiceService.update(hpgProductServices.get(0));
                    hpgProductServiceForm = new HpgProductServiceForm();
                    CommonUtil.copyObjProperties(hpgProductServiceForm, hpgProductServices.get(0));
                    hpgProductServiceForm.setEdtbyusername((this.smtUserService.queryById(hpgProductServices.get(0).getEdtbyuserid()) == null)? "" :
                            this.smtUserService.queryById(hpgProductServices.get(0).getEdtbyuserid()).getUsername());
                    hpgProductServiceForm.setPublishername((this.smtUserService.queryById(hpgProductServices.get(0).getPublisher()) == null)? "" :
                            this.smtUserService.queryById(hpgProductServices.get(0).getPublisher()).getUsername());
                    hpgProductServiceForm.setWritername((this.smtUserService.queryById(hpgProductServices.get(0).getWriter()) == null)? "" :
                            this.smtUserService.queryById(hpgProductServices.get(0).getWriter()).getUsername());
                 }
            }
            JSONObject resData = new JSONObject();
            resData.put("data",hpgProductServiceForm);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取产品与服务出错！"+e.getMessage(), resData);
        }
    }
}
