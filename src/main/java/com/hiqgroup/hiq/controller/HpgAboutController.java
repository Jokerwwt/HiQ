package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HpgAbout;
import com.hiqgroup.hiq.form.HpgAboutForm;
import com.hiqgroup.hiq.service.HpgAboutService;
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
 * 关于我们(HpgAbout)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-16 22:11:58
 */
@RestController
@RequestMapping("hpgAbout")
public class HpgAboutController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HpgAboutService hpgAboutService;
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
    public HpgAbout selectOne(String id) {
        return this.hpgAboutService.queryById(id);
    }

    /**l
     * 获取关于我们
     *
     * @return 数据列表
     */
    @ApiOperation(value = "获取关于我们")
    @GetMapping("getAbout")
    public Result getAbout() {
        try {
            List<HpgAbout> hpgAbouts = this.hpgAboutService.queryAll(new HpgAbout());
            HpgAboutForm hpgAboutForm = null;
            if (hpgAbouts.size()>0) {
                hpgAboutForm = new HpgAboutForm();
                CommonUtil.copyObjProperties(hpgAboutForm,hpgAbouts.get(0));
                hpgAboutForm.setEdtbyusername((this.smtUserService.queryById(hpgAbouts.get(0).getEdtbyuserid()) == null)? "" :
                        this.smtUserService.queryById(hpgAbouts.get(0).getPublisher()).getUsername());
                hpgAboutForm.setPublishername((this.smtUserService.queryById(hpgAbouts.get(0).getPublisher()) == null)? "" :
                        this.smtUserService.queryById(hpgAbouts.get(0).getPublisher()).getUsername());
                hpgAboutForm.setWritername((this.smtUserService.queryById(hpgAbouts.get(0).getWriter()) == null)? "" :
                        this.smtUserService.queryById(hpgAbouts.get(0).getWriter()).getUsername());
            }
            JSONObject resData = new JSONObject();
            resData.put("data",hpgAboutForm);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取关于我们出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 设置关于我们
     ** @param hpgAboutForm
     * @return 设置反馈
     */
    @ApiOperation(value = "设置关于我们")
    @PostMapping("setAbout")
    public Result setAbout(@RequestBody HpgAboutForm hpgAboutForm, HttpServletRequest request) {
        HpgAbout hpgAbout = this.hpgAboutService.queryById(hpgAboutForm.getId());
        JSONObject resData = new JSONObject();
        try {
            Map currentUser = jwtConfig.getCurrentUser();
            if (hpgAbout == null){
                hpgAbout = new HpgAbout();
                CommonUtil.copyObjProperties(hpgAbout,hpgAboutForm);
                hpgAbout.setId(UUID.randomUUID().toString());
                hpgAbout.setEdtbyuserid(currentUser.get("id").toString());
                hpgAbout.setWriter(currentUser.get("id").toString());
                Date date =new Date();
                hpgAbout.setEdttime(date);
                if (hpgAboutForm.getStatus().equals("1")){
                    hpgAbout.setPublishdate(null);
                }else{
                    hpgAbout.setPublishdate(date);
                }
                this.hpgAboutService.insert(hpgAbout);
                resData.put("writer",currentUser.get("id").toString());
                resData.put("writername",currentUser.get("username").toString());
            }else{
                CommonUtil.copyObjProperties(hpgAbout,hpgAboutForm);
                hpgAbout.setEdtbyuserid(currentUser.get("id").toString());
                Date date =new Date();
                hpgAbout.setEdttime(date);
                if (hpgAboutForm.getStatus().equals("1")){
                    hpgAbout.setPublishdate(null);
                }else{
                    hpgAbout.setPublishdate(date);
                }
                this.hpgAboutService.update(hpgAbout);
                resData.put("writer",hpgAboutForm.getWriter());
                resData.put("writername",hpgAboutForm.getWritername());
            }
            if (hpgAboutForm.getStatus().equals("1")){
                resData.put("publishername", "");
                resData.put("status", "1");
                resData.put("publishdate",null);
            } else {
                resData.put("publishername", (this.smtUserService.queryById(hpgAboutForm.getPublisher()) == null)? "": this.smtUserService.queryById(hpgAboutForm.getPublisher()).getUsername());
                resData.put("publishdate",hpgAbout.getPublishdate());
                resData.put("status", "2");
            }
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgAbout",request.getRemoteAddr(),
                    "修改【关于我们】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success(resData);
        }catch (Exception e) {
            resData.put("writername",hpgAboutForm.getWritername());
            resData.put("publishername",hpgAboutForm.getPublishername());
            resData.put("publishdate",null);
            resData.put("status", "1");
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"设置关于我们出错！"+e.getMessage(),resData);
        }
    }

    /**
     * 获取关于我们显示到前端
     *
     * @return 数据列表
     */
    @ApiOperation(value = "获取关于我们显示到前端")
    @GetMapping("showAbout")
    public Result showAbout() {
        HpgAbout hpgAboutParam = new HpgAbout();
        hpgAboutParam.setStatus("2");
        try {
            List<HpgAbout> hpgAbouts = this.hpgAboutService.queryAll(hpgAboutParam);
            HpgAboutForm hpgAboutForm = null;
            if (hpgAbouts.size()>0) {
                if (!hpgAbouts.get(0).getPublisher().equals("")) {
                    hpgAbouts.get(0).setViewtotal(((hpgAbouts.get(0).getViewtotal() == null)? 0:hpgAbouts.get(0).getViewtotal())+1);
                    this.hpgAboutService.update(hpgAbouts.get(0));
                    hpgAboutForm = new HpgAboutForm();
                    CommonUtil.copyObjProperties(hpgAboutForm, hpgAbouts.get(0));
                    hpgAboutForm.setEdtbyusername((this.smtUserService.queryById(hpgAbouts.get(0).getEdtbyuserid()) == null)? "" :
                            this.smtUserService.queryById(hpgAbouts.get(0).getEdtbyuserid()).getUsername());
                    hpgAboutForm.setPublishername((this.smtUserService.queryById(hpgAbouts.get(0).getPublisher()) == null)? "" :
                            this.smtUserService.queryById(hpgAbouts.get(0).getPublisher()).getUsername());
                    hpgAboutForm.setWritername((this.smtUserService.queryById(hpgAbouts.get(0).getWriter()) == null)? "" :
                            this.smtUserService.queryById(hpgAbouts.get(0).getWriter()).getUsername());
                }
            }
            JSONObject resData = new JSONObject();
            resData.put("data",hpgAboutForm);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取关于我们出错！"+e.getMessage(), resData);
        }
    }

}
