package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HqtCheckprice;
import com.hiqgroup.hiq.entity.SmtUser;
import com.hiqgroup.hiq.form.HqtCheckPriceForm;
import com.hiqgroup.hiq.form.PagedQueryForm;
import com.hiqgroup.hiq.service.HqtCheckpriceService;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtCompanyService;
import com.hiqgroup.hiq.service.SmtUserService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * 检测报价表(HqtCheckprice)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-26 14:25:43
 */
@RestController
@RequestMapping("hqtCheckprice")
public class HqtCheckpriceController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HqtCheckpriceService hqtCheckpriceService;
    @Resource
    private SmtUserService smtUserService;
    @Resource
    private SmtCompanyService smtCompanyService;
    @Resource
    private HqtStatisticService hqtStatisticService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HqtCheckprice selectOne(String id) {
        return this.hqtCheckpriceService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) throws ParseException {
        HqtCheckprice hqtCheckpriceParam = new HqtCheckprice();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hqtCheckpriceParam.setCompanyid(search.get("companyid").toString());
        hqtCheckpriceParam.setProjectname(search.get("projectname").toString());
        hqtCheckpriceParam.setStandardid(search.get("standardid").toString());
        hqtCheckpriceParam.setStandardname(search.get("standardname").toString());
        if ((search.get("creatorname") != null) && !search.get("creatorname").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("creatorname").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCheckpriceParam.setCreator(smtUsers.get(0).getId());
            }else{
                hqtCheckpriceParam.setCreator("###");
            }
        }
        List<Map<String, List<String>>> ins = new ArrayList<>();
        Map currentUser = jwtConfig.getCurrentUser();
        String rightValue = search.get("rightvalue").toString();
        if (currentUser.get("usercompanytype").toString().equals("2")) {  //当前用户为运营机构用户
            if (rightValue.substring(8).equals("0")) { //无全部数据权限
                List<String> inData = new ArrayList<>();
                if (rightValue.substring(6, 7).equals("1")){   //有单位数据权限
                    SmtUser smtUserParam = new SmtUser();
                    smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                    List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                    for (SmtUser smtUser: smtUsers) {
                        inData.add(smtUser.getId());
                    }
                }else if (rightValue.substring(7, 8).equals("1")) {  //有部门数据权限
                    SmtUser smtUserParam = new SmtUser();
                    smtUserParam.setDeptid(currentUser.get("userdept").toString());
                    List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                    for (SmtUser smtUser: smtUsers) {
                        inData.add(smtUser.getId());
                    }
                }else{  //无部门数据权限，则仅获取接单人为当前用户的数据
                    inData.add(currentUser.get("id").toString());
                }
                Map<String, List<String>> in = new HashMap();
                in.put("Creator",inData);
                ins.add(in);
            }
        }
        try {
            List<HqtCheckprice> hqtCheckprices = this.hqtCheckpriceService.queryAllByLimit(hqtCheckpriceParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(), ins, null);
            List<HqtCheckPriceForm> hqtCheckPriceForms = new ArrayList<>();
            for (HqtCheckprice hqtCheckprice: hqtCheckprices) {
                HqtCheckPriceForm hqtCheckPriceForm = new HqtCheckPriceForm();
                CommonUtil.copyObjProperties(hqtCheckPriceForm,hqtCheckprice);
                hqtCheckPriceForm.setEdtbyusername((this.smtUserService.queryById(hqtCheckprice.getEdtbyuserid()) == null)? "未知" :
                        this.smtUserService.queryById(hqtCheckprice.getEdtbyuserid()).getUsername());
                hqtCheckPriceForm.setCreatorname((this.smtUserService.queryById(hqtCheckprice.getCreator()) == null)? "未知" :
                        this.smtUserService.queryById(hqtCheckprice.getCreator()).getUsername());
                hqtCheckPriceForm.setCompanyname((this.smtCompanyService.queryById(hqtCheckprice.getCompanyid()) == null)? "未知" :
                        this.smtCompanyService.queryById(hqtCheckprice.getCompanyid()).getCompanyname());
                hqtCheckPriceForms.add(hqtCheckPriceForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total",this.hqtCheckpriceService.getCount(hqtCheckpriceParam, ins, null));
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",hqtCheckPriceForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取检测价格表出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     ** @param hqtCheckPriceForm
     * @return 更新反馈
     */
    @ApiOperation(value = "更新检测价格表信息")
    @PostMapping("update")
    public Result update(@RequestBody HqtCheckPriceForm hqtCheckPriceForm, HttpServletRequest request) {
        HqtCheckprice hqtCheckprice = this.hqtCheckpriceService.queryById(hqtCheckPriceForm.getId());
        try {
            CommonUtil.copyObjProperties(hqtCheckprice,hqtCheckPriceForm);
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCheckprice.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            hqtCheckprice.setEdttime(date);
            this.hqtCheckpriceService.update(hqtCheckprice);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckPrice",request.getRemoteAddr(),
                    "修改【"+hqtCheckPriceForm.getCompanyname()+"-"+hqtCheckPriceForm.getProjectname()+"】", request.getHeader("User-Agent"));
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
     ** @param hqtCheckPriceForm
     * @return 更新反馈
     */
    @ApiOperation(value = "新增检测价格表信息")
    @PostMapping("create")
    public Result create(@RequestBody HqtCheckPriceForm hqtCheckPriceForm, HttpServletRequest request) {
        HqtCheckprice hqtCheckprice = new HqtCheckprice();
        try {
            CommonUtil.copyObjProperties(hqtCheckprice,hqtCheckPriceForm);
            hqtCheckprice.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCheckprice.setEdtbyuserid(currentUser.get("id").toString());
            hqtCheckprice.setCreator(currentUser.get("id").toString());
            Date date =new Date();
            hqtCheckprice.setEdttime(date);
            this.hqtCheckpriceService.insert(hqtCheckprice);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckPrice",request.getRemoteAddr(),
                    "新增【"+hqtCheckPriceForm.getCompanyname()+"-"+hqtCheckPriceForm.getProjectname()+"】", request.getHeader("User-Agent"));
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
     ** @param checkpriceIds
     * @return 更新反馈
     */
    @ApiOperation(value = "删除检测价格表信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> checkpriceIds, HttpServletRequest request) {
        try {
            for (String checkpriceId:checkpriceIds) {
                HqtCheckprice hqtCheckprice = this.hqtCheckpriceService.queryById(checkpriceId);
                String companyName = this.smtCompanyService.queryById(hqtCheckprice.getCompanyid()).getCompanyname();
                this.hqtCheckpriceService.deleteById(checkpriceId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckPrice",request.getRemoteAddr(),
                        "删除【"+companyName+"-"+hqtCheckprice.getProjectname()+"】", request.getHeader("User-Agent"));
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
}
