package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HqtCheckapplyorder;
import com.hiqgroup.hiq.entity.HqtCustomerorder;
import com.hiqgroup.hiq.entity.SmtUser;
import com.hiqgroup.hiq.form.HqtCheckApplyOrderForm;
import com.hiqgroup.hiq.form.PagedQueryForm;
import com.hiqgroup.hiq.service.*;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 委托检测申请单(HqtCheckapplyorder)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-21 21:46:43
 */
@RestController
@RequestMapping("hqtCheckapplyorder")
public class HqtCheckapplyorderController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HqtCheckapplyorderService hqtCheckapplyorderService;
    @Resource
    private SmtUserService smtUserService;
    @Resource
    private SmtCompanyService smtCompanyService;
    @Resource
    private HqtCustomerorderService hqtCustomerorderService;
    @Resource
    private HqtStatisticService hqtStatisticService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Result selectOne(String id) throws InvocationTargetException, IllegalAccessException {
        HqtCheckapplyorder hqtCheckapplyorder = this.hqtCheckapplyorderService.queryById(id);
        HqtCheckApplyOrderForm hqtCheckApplyOrderForm = new HqtCheckApplyOrderForm();
        try {
            CommonUtil.copyObjProperties(hqtCheckApplyOrderForm, hqtCheckapplyorder);
            if ((hqtCheckapplyorder.getCheckers() != null) && !hqtCheckapplyorder.getCheckers().equals("")) {
                String[] checkers = hqtCheckapplyorder.getCheckers().split(",");
                String checkersName = "";
                for (String checker : checkers) {
                    checkersName = checkersName + "," + this.smtCompanyService.queryById(checker).getCompanyname();
                }
                hqtCheckApplyOrderForm.setCheckersname(checkersName.trim().substring(1));
            }
            hqtCheckApplyOrderForm.setEdtbyusername((this.smtUserService.queryById(hqtCheckapplyorder.getEdtbyuserid()) == null) ? "未知" :
                    this.smtUserService.queryById(hqtCheckapplyorder.getEdtbyuserid()).getUsername());
            hqtCheckApplyOrderForm.setCustomername((this.smtCompanyService.queryById(hqtCheckapplyorder.getCustomerid()) == null) ? "" :
                    this.smtCompanyService.queryById(hqtCheckapplyorder.getCustomerid()).getCompanyname());
            hqtCheckApplyOrderForm.setAcceptername((this.smtUserService.queryById(hqtCheckapplyorder.getAccepter()) == null) ? "未知" :
                    this.smtUserService.queryById(hqtCheckapplyorder.getAccepter()).getUsername());
            hqtCheckApplyOrderForm.setOrdermanname((this.smtUserService.queryById(hqtCheckapplyorder.getOrderman()) == null) ? "未知" :
                    this.smtUserService.queryById(hqtCheckapplyorder.getOrderman()).getUsername());
            hqtCheckApplyOrderForm.setTocompanyname((this.smtCompanyService.queryById(hqtCheckapplyorder.getTocompany()) == null) ? "" :
                    this.smtCompanyService.queryById(hqtCheckapplyorder.getTocompany()).getCompanyname());
            JSONObject resData = new JSONObject();
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",hqtCheckApplyOrderForm);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取委托检测申请单出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) throws ParseException {
        HqtCheckapplyorder hqtCheckapplyorderParam = new HqtCheckapplyorder();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        if ((search.get("customerid") != null) && !search.get("customerid").equals(""))
            hqtCheckapplyorderParam.setCustomerid(search.get("customerid").toString());
        if ((search.get("tocompany") != null) && !search.get("tocompany").equals(""))
            hqtCheckapplyorderParam.setTocompany(search.get("tocompany").toString());
        hqtCheckapplyorderParam.setOrdername(search.get("ordername").toString());
        hqtCheckapplyorderParam.setSamplename(search.get("samplename").toString());
        if ((search.get("orderdate") != null) && !search.get("orderdate").equals(""))
            hqtCheckapplyorderParam.setOrderdate(new SimpleDateFormat("yyyy-MM-dd").parse(search.get("orderdate").toString()));
        if (!search.get("status").equals(""))
            hqtCheckapplyorderParam.setStatus(search.get("status").toString());
        if ((search.get("acceptername") != null) && !search.get("acceptername").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("acceptername").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCheckapplyorderParam.setAccepter(smtUsers.get(0).getId());
            }else{
                hqtCheckapplyorderParam.setAccepter("###");
            }
        }
        Map currentUser = jwtConfig.getCurrentUser();
        String rightValue = search.get("rightvalue").toString();
        List<Map<String, List<String>>> ins = new ArrayList<>();
        if (currentUser.get("usercompanytype").toString().equals("2")) {  //当前用户为运营机构用户
//            hqtCheckapplyorderParam.setStatus("-1|1|2|3|4|5");
            if (rightValue.substring(14).equals("0")) { //无全部数据
                List<String> inData = new ArrayList<>();
                if (rightValue.substring(12, 13).equals("1")){  //有单位数据权限
                    SmtUser smtUserParam = new SmtUser();
                    smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                    List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                    inData.add("");
                    for (SmtUser smtUser: smtUsers) {
                        inData.add(smtUser.getId());
                    }
                }else if (rightValue.substring(13, 14).equals("1")) {  //有部门数据权限
                    SmtUser smtUserParam = new SmtUser();
                    smtUserParam.setDeptid(currentUser.get("userdept").toString());
                    List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                    inData.add("");
                    for (SmtUser smtUser: smtUsers) {
                        inData.add(smtUser.getId());
                    }
                }else{  //无部门数据权限，则仅获取接单人为当前用户的数据
                    inData.add("");
                    inData.add(currentUser.get("id").toString());
                }
                Map<String, List<String>> in = new HashMap();
                in.put("Accepter",inData);
                ins.add(in);
            }
        }else if (currentUser.get("usercompanytype").toString().equals("1")){  //当前用户为生产厂家用户
            if (rightValue.substring(14).equals("0") && rightValue.substring(12, 13).equals("0")) {
                List<String> inData = new ArrayList<>();
                if (rightValue.substring(13, 14).equals("1")) {  //有部门数据权限
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
                in.put("OrderMan",inData);
                ins.add(in);
            }
        }
        List<Map<String, List<String>>> betweens = new ArrayList<>();
        if ((search.get("orderdateB") != null) && (search.get("orderdateE") != null)){
            List<String> betweenData = new ArrayList<>();
            betweenData.add(search.get("orderdateB").toString());
            betweenData.add((search.get("orderdateE").toString().trim().equals("")? "3000-12-31":search.get("orderdateE").toString()));
            Map<String, List<String>> between = new HashMap();
            between.put("OrderDate",betweenData);
            betweens.add(between);
        }
        try {
            List<HqtCheckapplyorder> hqtCheckapplyorders = this.hqtCheckapplyorderService.queryAllByLimit(hqtCheckapplyorderParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(), ins, betweens);
            List<HqtCheckApplyOrderForm> hqtCheckApplyOrderForms = new ArrayList<>();
            for (HqtCheckapplyorder hqtCheckapplyorder: hqtCheckapplyorders) {
                HqtCheckApplyOrderForm hqtCheckApplyOrderForm = new HqtCheckApplyOrderForm();
                CommonUtil.copyObjProperties(hqtCheckApplyOrderForm,hqtCheckapplyorder);
                if ((hqtCheckapplyorder.getCheckers() != null) && !hqtCheckapplyorder.getCheckers().equals("")) {
                    String[] checkers = hqtCheckapplyorder.getCheckers().split(",");
                    String checkersName = "";
                    for (String checker : checkers) {
                        checkersName = checkersName + "," + this.smtCompanyService.queryById(checker).getCompanyname();
                    }
                    hqtCheckApplyOrderForm.setCheckersname(checkersName.trim().substring(1));
                }
                hqtCheckApplyOrderForm.setEdtbyusername((this.smtUserService.queryById(hqtCheckapplyorder.getEdtbyuserid()) == null)? "未知" :
                        this.smtUserService.queryById(hqtCheckapplyorder.getEdtbyuserid()).getUsername());
                hqtCheckApplyOrderForm.setCustomername((this.smtCompanyService.queryById(hqtCheckapplyorder.getCustomerid()) == null)? "" :
                        this.smtCompanyService.queryById(hqtCheckapplyorder.getCustomerid()).getCompanyname());
                hqtCheckApplyOrderForm.setAcceptername((this.smtUserService.queryById(hqtCheckapplyorder.getAccepter()) == null)? "未知" :
                        this.smtUserService.queryById(hqtCheckapplyorder.getAccepter()).getUsername());
                hqtCheckApplyOrderForm.setOrdermanname((this.smtUserService.queryById(hqtCheckapplyorder.getOrderman()) == null)? "未知" :
                        this.smtUserService.queryById(hqtCheckapplyorder.getOrderman()).getUsername());
                hqtCheckApplyOrderForm.setTocompanyname((this.smtCompanyService.queryById(hqtCheckapplyorder.getTocompany()) == null)? "" :
                        this.smtCompanyService.queryById(hqtCheckapplyorder.getTocompany()).getCompanyname());
                hqtCheckApplyOrderForms.add(hqtCheckApplyOrderForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total",this.hqtCheckapplyorderService.getCount(hqtCheckapplyorderParam, ins, betweens));
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",hqtCheckApplyOrderForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取委托检测申请单出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     ** @param hqtCheckApplyOrderForm
     * @return 更新反馈
     */
    @ApiOperation(value = "更新委托检测申请单信息")
    @PostMapping("update")
    public Result update(@RequestBody HqtCheckApplyOrderForm hqtCheckApplyOrderForm, HttpServletRequest request) {
        HqtCheckapplyorder hqtCheckapplyorder = this.hqtCheckapplyorderService.queryById(hqtCheckApplyOrderForm.getId());
        try {
            CommonUtil.copyObjProperties(hqtCheckapplyorder,hqtCheckApplyOrderForm);
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCheckapplyorder.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            hqtCheckapplyorder.setEdttime(date);
            this.hqtCheckapplyorderService.update(hqtCheckapplyorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckApplyOrder",request.getRemoteAddr(),
                    "修改【"+hqtCheckApplyOrderForm.getOrderid()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("data",hqtCheckapplyorder);
            resData.put("edttime",date);
            resData.put("status",hqtCheckapplyorder.getStatus());
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            resData.put("edttime",null);
            resData.put("status",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage(),resData);
        }
    }

    /**
     * 通过主键新增单条数据
     ** @param hqtCheckApplyOrderForm
     * @return 更新反馈
     */
    @ApiOperation(value = "新增委托检测申请单信息")
    @PostMapping("create")
    public Result create(@RequestBody HqtCheckApplyOrderForm hqtCheckApplyOrderForm, HttpServletRequest request) {
        HqtCheckapplyorder hqtCheckapplyorder = new HqtCheckapplyorder();
        try {
            CommonUtil.copyObjProperties(hqtCheckapplyorder,hqtCheckApplyOrderForm);
            hqtCheckapplyorder.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCheckapplyorder.setEdtbyuserid(currentUser.get("id").toString());
            hqtCheckapplyorder.setOrderman(currentUser.get("id").toString());
            hqtCheckapplyorder.setAccepter("");
            Date date = new Date();
            hqtCheckapplyorder.setEdttime(date);
            HqtCheckapplyorder hqtCheckapplyorderParam = new HqtCheckapplyorder();
            hqtCheckapplyorderParam.setCustomerid(hqtCheckapplyorder.getCustomerid());
            hqtCheckapplyorder.setOrderid("A"+this.smtCompanyService.queryById(hqtCheckapplyorder.getCustomerid()).getCompanyid()+
                    new SimpleDateFormat("yyyyMMdd").format(date)+String.valueOf(this.hqtCheckapplyorderService.getCount(hqtCheckapplyorderParam,null,null)+1));
            this.hqtCheckapplyorderService.insert(hqtCheckapplyorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckApplyOrder",request.getRemoteAddr(),
                    "新增【"+hqtCheckApplyOrderForm.getOrderid()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("edttime",date);
            resData.put("status",hqtCheckapplyorder.getStatus());
            resData.put("id",hqtCheckapplyorder.getId());
            resData.put("orderid",hqtCheckapplyorder.getOrderid());
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("edttime",null);
            resData.put("status",null);
            resData.put("id",null);
            resData.put("orderid",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage(),resData);
        }
    }

    /**
     * 批量删除数据
     ** @param customerapplyordeIds
     * @return 更新反馈
     */
    @ApiOperation(value = "删除委托检测申请单信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> customerapplyordeIds, HttpServletRequest request) {
        try {
            for (String customerapplyordeId:customerapplyordeIds) {
                HqtCheckapplyorder hqtCheckapplyorder = this.hqtCheckapplyorderService.queryById(customerapplyordeId);
                this.hqtCheckapplyorderService.deleteById(customerapplyordeId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckApplyOrder",request.getRemoteAddr(),
                        "删除【"+hqtCheckapplyorder.getOrderid()+"】", request.getHeader("User-Agent"));
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
     * 下委托订单
     ** @param id
     * @return 更新反馈
     */
    @ApiOperation(value = "下委托订单")
    @PostMapping("makeOrder")
    public Result makeOrder(@RequestBody String id, HttpServletRequest request) {
        id = id.replace("\"","").trim();
        HqtCustomerorder hqtCustomerorder = new HqtCustomerorder();
        try {
            HqtCheckapplyorder hqtCheckapplyorder = this.hqtCheckapplyorderService.queryById(id);
            hqtCustomerorder.setId(UUID.randomUUID().toString());
            hqtCustomerorder.setApplyorderid(id);
            hqtCustomerorder.setOrdername(hqtCheckapplyorder.getOrdername());
            hqtCustomerorder.setHopeenddate(hqtCheckapplyorder.getHopeenddate());
            hqtCustomerorder.setCustomerid(hqtCheckapplyorder.getCustomerid());
            hqtCustomerorder.setChargeman(hqtCheckapplyorder.getAccepter());
            hqtCustomerorder.setSamplename(hqtCheckapplyorder.getSamplename());
            hqtCustomerorder.setContactor(hqtCheckapplyorder.getContactor());
            hqtCustomerorder.setTelno(hqtCheckapplyorder.getTelno());
            hqtCustomerorder.setCheckers(hqtCheckapplyorder.getCheckers());
            hqtCustomerorder.setOrderman(hqtCheckapplyorder.getOrderman());
            hqtCustomerorder.setCheckrequire(hqtCheckapplyorder.getCheckrequire());
            hqtCustomerorder.setAmt(hqtCheckapplyorder.getAmt());
            hqtCustomerorder.setRemark(hqtCheckapplyorder.getRemark());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCustomerorder.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            hqtCustomerorder.setEdttime(date);
            hqtCustomerorder.setOrderdate(date);
            hqtCustomerorder.setStatus("1");
            HqtCustomerorder hqtCustomerorderParam = new HqtCustomerorder();
            hqtCustomerorderParam.setCustomerid(hqtCustomerorder.getCustomerid());
            hqtCustomerorder.setOrderid("C"+this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()).getCompanyid()+
                    new SimpleDateFormat("yyyyMMdd").format(date)+String.valueOf(this.hqtCustomerorderService.getCount(hqtCustomerorderParam,null,null)+1));
            this.hqtCustomerorderService.insert(hqtCustomerorder);
            hqtCheckapplyorder.setStatus("5");
            this.hqtCheckapplyorderService.update(hqtCheckapplyorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckApplyOrder",request.getRemoteAddr(),
                    "下委托单【"+hqtCheckapplyorder.getOrderid()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("status",hqtCheckapplyorder.getStatus());
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("status",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage(),resData);
        }
    }

    /**
     * 委托订单撤回
     ** @param id
     * @return 更新反馈
     */
    @ApiOperation(value = "委托订单撤回")
    @PostMapping("makeOrderReturn")
    public Result makeOrderReturn(@RequestBody String id, HttpServletRequest request) {
        id = id.replace("\"","").trim();
        try {
            HqtCheckapplyorder hqtCheckapplyorder = this.hqtCheckapplyorderService.queryById(id);
            HqtCustomerorder hqtCustomerorderParam = new HqtCustomerorder();
            hqtCustomerorderParam.setApplyorderid(id);
            List<HqtCustomerorder> hqtCustomerorders = this.hqtCustomerorderService.queryAll(hqtCustomerorderParam);
            for (HqtCustomerorder hqtCustomerorder: hqtCustomerorders) {
                String statusName = "";
                if (!hqtCustomerorder.getStatus().equals("0")){
                    switch (hqtCustomerorder.getStatus()){
                        case "-1":
                            statusName = "已作废";
                            break;
                        case "1":
                            statusName = "已提交";
                            break;
                        case "2":
                            statusName = "已审核";
                            break;
                        case "3":
                            statusName = "已结案";
                            break;
                    }
                    return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"对应委托订单"+statusName+"，不可撤回！");
                }
                this.hqtCustomerorderService.deleteById(hqtCustomerorder.getId());
            }
            hqtCheckapplyorder.setStatus("4");
            this.hqtCheckapplyorderService.update(hqtCheckapplyorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckApplyOrder",request.getRemoteAddr(),
                    "撤回委托单【"+hqtCheckapplyorder.getOrderid()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("status",hqtCheckapplyorder.getStatus());
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("status",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage(), resData);
        }
    }

}
