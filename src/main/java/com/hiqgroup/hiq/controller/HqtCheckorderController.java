package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.*;
import com.hiqgroup.hiq.form.*;
import com.hiqgroup.hiq.service.*;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 检测方订单(HqtCheckorder)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-02 14:30:30
 */
@RestController
@RequestMapping("hqtCheckorder")
public class HqtCheckorderController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HqtCheckorderService hqtCheckorderService;
    @Resource
    private HqtCustomerorderService hqtCustomerorderService;
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
    public HqtCheckorder selectOne(String id) {
        return this.hqtCheckorderService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) throws ParseException {
        HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hqtCheckorderParam.setCheckerid(search.get("checkerid").toString());
        hqtCheckorderParam.setOrdername(search.get("ordername").toString());
        if ((search.get("orderid") != null) && !search.get("orderid").equals(""))
            hqtCheckorderParam.setOrderid(search.get("orderid").toString());
        if ((search.get("orderdate") != null) && !search.get("orderdate").equals(""))
            hqtCheckorderParam.setOrderdate(new SimpleDateFormat("yyyy-MM-dd").parse(search.get("orderdate").toString()));
        if (!search.get("status").equals(""))
            hqtCheckorderParam.setStatus(search.get("status").toString());
        if ((search.get("paystatus") != null) && !search.get("paystatus").equals(""))
            hqtCheckorderParam.setPaystatus(search.get("paystatus").toString());
        if ((search.get("invoicestatus") != null) && !search.get("invoicestatus").equals(""))
            hqtCheckorderParam.setInvoicestatus(search.get("invoicestatus").toString());
        if ((search.get("reportid") != null) && !search.get("reportid").equals(""))
            hqtCheckorderParam.setReportid(search.get("reportid").toString());
        if ((search.get("paydate") != null) && !search.get("paydate").equals(""))
            hqtCheckorderParam.setPaydate(new SimpleDateFormat("yyyy-MM-dd").parse(search.get("paydate").toString()));
        if ((search.get("payrecordername") != null) && !search.get("payrecordername").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("payrecordername").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCheckorderParam.setPayrecorder(smtUsers.get(0).getId());
            }else{
                hqtCheckorderParam.setPayrecorder("###");
            }
        }
        if ((search.get("payremark") != null) && !search.get("payremark").equals(""))
            hqtCheckorderParam.setPayremark(search.get("payremark").toString());
        if ((search.get("invoicedate") != null) && !search.get("invoicedate").equals(""))
            hqtCheckorderParam.setInvoicedate(new SimpleDateFormat("yyyy-MM-dd").parse(search.get("invoicedate").toString()));
        if ((search.get("invoicerecordername") != null) && !search.get("invoicerecordername").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("invoicerecordername").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCheckorderParam.setInvoicerecorder(smtUsers.get(0).getId());
            }else{
                hqtCheckorderParam.setInvoicerecorder("###");
            }
        }
        if ((search.get("invoiceremark") != null) && !search.get("invoiceremark").equals(""))
            hqtCheckorderParam.setInvoiceremark(search.get("invoiceremark").toString());
        if ((search.get("sendername") != null) && !search.get("sendername").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("sendername").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCheckorderParam.setSender(smtUsers.get(0).getId());
            }else{
                hqtCheckorderParam.setSender("###");
            }
        }
        List<Map<String, List<String>>> ins = new ArrayList<>();
        if ((search.get("rightvalue") != null) && !search.get("rightvalue").equals("")) {
            Map currentUser = jwtConfig.getCurrentUser();
            String rightValue = search.get("rightvalue").toString();
            if (currentUser.get("usercompanytype").toString().equals("2")) {  //当前用户为运营机构用户
                if (rightValue.substring(11).equals("0")) { //无全部数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(9, 10).equals("1")) {  //有单位数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else if (rightValue.substring(10, 11).equals("1")) {  //有部门数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setDeptid(currentUser.get("userdept").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else {  //无部门数据权限，则仅获取接单人为当前用户的数据
                        inData.add(currentUser.get("id").toString());
                    }
                    Map<String, List<String>> in = new HashMap();
                    in.put("Sender",inData);
                    ins.add(in);
                }
            }
            if (currentUser.get("usercompanytype").toString().equals("3")) {  //当前用户为检测机构用户
                if (rightValue.substring(11).equals("0") && rightValue.substring(9, 10).equals("0")) { //无全部数据和单位数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(10, 11).equals("1")) {  //有部门数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setDeptid(currentUser.get("userdept").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        inData.add("");
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else {  //无部门数据权限，则仅获取接单人为当前用户的数据
                        inData.add("");
                        inData.add(currentUser.get("id").toString());
                    }
                    Map<String, List<String>> in = new HashMap();
                    in.put("Accepter",inData);
                    ins.add(in);
                }
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
            List<HqtCheckorder> hqtCheckorders = this.hqtCheckorderService.queryAllByLimit(hqtCheckorderParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(), param.getSort(), param.getOrdertype(), ins, betweens);
            List<HqtCheckOrderForm> hqtCheckApplyOrderForms = new ArrayList<>();
            for (HqtCheckorder hqtCheckorder : hqtCheckorders) {
                HqtCheckOrderForm hqtCheckOrderForm = new HqtCheckOrderForm();
                CommonUtil.copyObjProperties(hqtCheckOrderForm, hqtCheckorder);
                hqtCheckOrderForm.setEdtbyusername((this.smtUserService.queryById(hqtCheckorder.getEdtbyuserid()) == null)? "未知" :
                        this.smtUserService.queryById(hqtCheckorder.getEdtbyuserid()).getUsername());
                hqtCheckOrderForm.setCheckername((this.smtCompanyService.queryById(hqtCheckorder.getCheckerid()) == null)? "未知" :
                        this.smtCompanyService.queryById(hqtCheckorder.getCheckerid()).getCompanyname());
                hqtCheckOrderForm.setCustorder((this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid()) == null)? "" :
                        this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid()).getOrderid());
                hqtCheckOrderForm.setSamplename((this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid()) == null)? "" :
                        this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid()).getSamplename());
                hqtCheckOrderForm.setSendername((this.smtUserService.queryById(hqtCheckorder.getSender()) == null)? "未知" :
                        this.smtUserService.queryById(hqtCheckorder.getSender()).getUsername());
                hqtCheckOrderForm.setAcceptername((this.smtUserService.queryById(hqtCheckorder.getAccepter()) == null)? "未知" :
                        this.smtUserService.queryById(hqtCheckorder.getAccepter()).getUsername());
                hqtCheckOrderForm.setPayrecordername((this.smtUserService.queryById(hqtCheckorder.getPayrecorder()) == null)? "" :
                        this.smtUserService.queryById(hqtCheckorder.getPayrecorder()).getUsername());
                hqtCheckOrderForm.setInvoicerecordername((this.smtUserService.queryById(hqtCheckorder.getInvoicerecorder()) == null)? "" :
                        this.smtUserService.queryById(hqtCheckorder.getInvoicerecorder()).getUsername());
                hqtCheckApplyOrderForms.add(hqtCheckOrderForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total", this.hqtCheckorderService.getCount(hqtCheckorderParam, ins, betweens));
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", hqtCheckApplyOrderForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取检测订单出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     * * @param hqtCheckOrderForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "更新检测订单信息")
    @PostMapping("update")
    public Result update(@RequestBody HqtCheckOrderForm hqtCheckOrderForm, HttpServletRequest request) {
        HqtCheckorder hqtCheckorder = this.hqtCheckorderService.queryById(hqtCheckOrderForm.getId());
        try {
            CommonUtil.copyObjProperties(hqtCheckorder, hqtCheckOrderForm);
            if (hqtCheckOrderForm.getStatus().equals("3")) {  //执行完工，已付款状态及已收票状态则直接为已结案
                if (hqtCheckorder.getPaystatus().equals("3") && hqtCheckorder.getInvoicestatus().equals("2"))
                    hqtCheckorder.setStatus("4");
            }
            Map currentUser = jwtConfig.getCurrentUser();
            if (hqtCheckOrderForm.getStatus().equals("2")) {  //审核
                hqtCheckorder.setAccepter(currentUser.get("id").toString());
            } else if (hqtCheckOrderForm.getStatus().equals("1")) {  //提交
                hqtCheckorder.setAccepter("");
            }
            hqtCheckorder.setEdtbyuserid(currentUser.get("id").toString());
            Date date = new Date();
            hqtCheckorder.setEdttime(date);
            this.hqtCheckorderService.update(hqtCheckorder);
            if (hqtCheckOrderForm.getStatus().equals("2")) {  //执行审核，则更新委托单为“检测中”
                HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid());
                hqtCustomerorder.setStatus("3");
                this.hqtCustomerorderService.update(hqtCustomerorder);
            } else if (hqtCheckOrderForm.getStatus().equals("1")) {  //执行审核退回
                if (hqtCheckorder.getCustorderid() != null && !hqtCheckorder.getCustorderid().equals("")) {
                    HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid());
                    hqtCustomerorder.setStatus("2");
                    this.hqtCustomerorderService.update(hqtCustomerorder);
                }
            } else if (hqtCheckOrderForm.getStatus().equals("3")) {  //执行完工
                HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid());
                hqtCustomerorder.setStatus("4");
                hqtCustomerorder.setFinisheddate(hqtCheckorder.getFinisheddate());
                this.hqtCustomerorderService.update(hqtCustomerorder);
            }
            //记录操作日志-----------
            String operate = "修改";
            switch (hqtCheckOrderForm.getStatus()) {
                case "-1":
                    operate = "执行作废";
                    break; //可选
                case "2":
                    operate = "执行审核";
                    break; //可选
                case "3":
                    operate = "执行完工";
                    break; //可选
                //你可以有任意数量的case语句
                default: //可选
                    operate = "修改";
            }
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(), "HqtCheckOrder", request.getRemoteAddr(),
                    operate+"【" + hqtCheckorder.getOrderid() + "】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")) {
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("edttime", date);
            resData.put("status", hqtCheckorder.getStatus());
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("edttime", null);
            resData.put("status", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键新增单条数据
     * * @param hqtCheckOrderForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "新增检测订单信息")
    @PostMapping("create")
    public Result create(@RequestBody HqtCheckOrderForm hqtCheckOrderForm, HttpServletRequest request) {
        HqtCheckorder hqtCheckorder = new HqtCheckorder();
        try {
            CommonUtil.copyObjProperties(hqtCheckorder, hqtCheckOrderForm);
            hqtCheckorder.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCheckorder.setEdtbyuserid(currentUser.get("id").toString());
            hqtCheckorder.setSender(currentUser.get("id").toString());
            hqtCheckorder.setPayamt((double) 0);
            Date date = new Date();
            hqtCheckorder.setEdttime(date);
            HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
            hqtCheckorderParam.setCheckerid(hqtCheckorder.getCheckerid());
            hqtCheckorder.setOrderid("J" + this.smtCompanyService.queryById(hqtCheckorder.getCheckerid()).getCompanyid() +
                    new SimpleDateFormat("yyyyMMdd").format(date) + String.valueOf(this.hqtCheckorderService.getCount(hqtCheckorderParam, null, null) + 1));
            this.hqtCheckorderService.insert(hqtCheckorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckOrder",request.getRemoteAddr(),
                    "新增【"+hqtCheckorder.getOrderid()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("id", hqtCheckorder.getId());
            resData.put("orderid", hqtCheckorder.getOrderid());
            resData.put("status", hqtCheckorder.getStatus());
            resData.put("edttime", date);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("id", null);
            resData.put("orderid", null);
            resData.put("status", null);
            resData.put("edttime", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 批量删除数据
     * * @param checkordeIds
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "删除检测订单信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> checkordeIds, HttpServletRequest request) {
        try {
            for (String checkordeId : checkordeIds) {
                HqtCheckorder hqtCheckorder = this.hqtCheckorderService.queryById(checkordeId);
                this.hqtCheckorderService.deleteById(checkordeId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckOrder",request.getRemoteAddr(),
                        "删除【"+hqtCheckorder.getOrderid()+"】", request.getHeader("User-Agent"));
                if (!syslogRes.equals("")){
                    return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
                }
                //---------------------
            }
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage());
        }
    }

    /**
     * 通过主键更新单条数据
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "更新检测订单过程记录信息")
    @PostMapping("updateProcrecord")
    public Result updateProcrecord(@RequestBody HqtCheckOrderForm hqtCheckOrderForm) {
        HqtCheckorder hqtCheckorder = this.hqtCheckorderService.queryById(hqtCheckOrderForm.getId());
        try {
            hqtCheckorder.setProcrecord(hqtCheckOrderForm.getProcrecord());
            this.hqtCheckorderService.update(hqtCheckorder);
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage());
        }
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("getCheckReports")
    public Result getCheckReports(PagedQueryForm<T> param) throws ParseException {
        HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hqtCheckorderParam.setCheckerid(search.get("checkerid").toString());
        hqtCheckorderParam.setOrdername(search.get("ordername").toString());
        if ((search.get("reportid") != null) && !search.get("reportid").equals(""))
            hqtCheckorderParam.setReportid(search.get("reportid").toString());
        hqtCheckorderParam.setStatus("3|4");
        if ((search.get("checkresult") != null) && !search.get("checkresult").equals(""))
            hqtCheckorderParam.setCheckresult(search.get("checkresult").toString());
        List<Map<String, List<String>>> ins = new ArrayList<>();
        if ((search.get("rightvalue") != null) && !search.get("rightvalue").equals("")) {
            Map currentUser = jwtConfig.getCurrentUser();
            String rightValue = search.get("rightvalue").toString();
            if (currentUser.get("usercompanytype").toString().equals("2")) {  //当前用户为运营机构用户
                if (rightValue.substring(3).equals("0")) { //无全部数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(1, 2).equals("1")) {   //有单位数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        inData.add("");
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else if (rightValue.substring(2, 3).equals("1")) {  //有部门数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setDeptid(currentUser.get("userdept").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else {  //无部门数据权限，则仅获取接单人为当前用户的数据
                        inData.add(currentUser.get("id").toString());
                    }
                    Map<String, List<String>> in = new HashMap();
                    in.put("Sender",inData);
                    ins.add(in);
                }
            }
            if (currentUser.get("usercompanytype").toString().equals("3")) {  //当前用户为检测机构用户
                if (rightValue.substring(3).equals("0") && rightValue.substring(1, 2).equals("0")) { //无全部数据和单位数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(2, 3).equals("1")) {  //有部门数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setDeptid(currentUser.get("userdept").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else {  //无部门数据权限，则仅获取接单人为当前用户的数据
                        inData.add(currentUser.get("id").toString());
                    }
                    Map<String, List<String>> in = new HashMap();
                    in.put("Accepter",inData);
                    ins.add(in);
                }
            }
            if (currentUser.get("usercompanytype").toString().equals("1")) {  //当前用户为生产工厂用户
                if (rightValue.substring(3).equals("0")) { //无全部数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(1, 2).equals("1")) {   //有单位数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        List<Map<String, List<String>>> userins = new ArrayList<>();
                        List<String> userlist = new ArrayList<>();
                        for (SmtUser smtUser : smtUsers) {
                            userlist.add(smtUser.getId());
                        }
                        Map<String, List<String>> userin = new HashMap();
                        userin.put("OrderMan",userlist);
                        userins.add(userin);
                        HqtCustomerorder hqtCustomerorderParam = new HqtCustomerorder();
                        hqtCustomerorderParam.setStatus("4|5");
                        List<HqtCustomerorder> hqtCustomerorders = this.hqtCustomerorderService.queryAllByLimit(hqtCustomerorderParam,
                                0, 1000000, "OrderDate", "", userins, null);
                        inData.add("");
                        for (HqtCustomerorder hqtCustomerorder : hqtCustomerorders) {
                            inData.add(hqtCustomerorder.getId());
                        }
                    } else if (rightValue.substring(2, 3).equals("1")) {  //有部门数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setDeptid(currentUser.get("userdept").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        List<Map<String, List<String>>> userins = new ArrayList<>();
                        List<String> userlist = new ArrayList<>();
                        for (SmtUser smtUser : smtUsers) {
                            userlist.add(smtUser.getId());
                        }
                        Map<String, List<String>> userin = new HashMap();
                        userin.put("OrderMan",userlist);
                        userins.add(userin);
                        HqtCustomerorder hqtCustomerorderParam = new HqtCustomerorder();
                        hqtCustomerorderParam.setStatus("4|5");
                        List<HqtCustomerorder> hqtCustomerorders = this.hqtCustomerorderService.queryAllByLimit(hqtCustomerorderParam,
                                0, 1000000, "OrderDate", "", userins, null);
                        inData.add("");
                        for (HqtCustomerorder hqtCustomerorder : hqtCustomerorders) {
                            inData.add(hqtCustomerorder.getId());
                        }
                    } else {  //无部门数据权限，则仅获取接单人为当前用户的数据
                        HqtCustomerorder hqtCustomerorderParam = new HqtCustomerorder();
                        hqtCustomerorderParam.setOrderman(currentUser.get("id").toString());
                        hqtCustomerorderParam.setStatus("4|5");
                        List<HqtCustomerorder> hqtCustomerorders = this.hqtCustomerorderService.queryAllByLimit(hqtCustomerorderParam,
                                0, 1000000, "OrderDate", "", null, null);
                        inData.add("");
                        for (HqtCustomerorder hqtCustomerorder : hqtCustomerorders) {
                            inData.add(hqtCustomerorder.getId());
                        }
                    }
                    Map<String, List<String>> in = new HashMap();
                    in.put("CustOrderId",inData);
                    ins.add(in);
                }
            }
        }
        List<Map<String, List<String>>> betweens = new ArrayList<>();
        if ((search.get("reportEndDateB") != null) && (search.get("reportEndDateE") != null)){
            List<String> betweenData = new ArrayList<>();
            betweenData.add(search.get("reportEndDateB").toString());
            betweenData.add((search.get("reportEndDateE").toString().trim().equals("")? "3000-12-31":search.get("reportEndDateE").toString()));
            Map<String, List<String>> between = new HashMap();
            between.put("ReportEndDate",betweenData);
            betweens.add(between);
        }
        try {
            List<HqtCheckorder> hqtCheckorders = this.hqtCheckorderService.queryAllByLimit(hqtCheckorderParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(), param.getSort(), param.getOrdertype(), ins, betweens);
            List<HqtCheckOrderForm> hqtCheckApplyOrderForms = new ArrayList<>();
            for (HqtCheckorder hqtCheckorder : hqtCheckorders) {
                HqtCheckOrderForm hqtCheckOrderForm = new HqtCheckOrderForm();
                CommonUtil.copyObjProperties(hqtCheckOrderForm, hqtCheckorder);
                hqtCheckOrderForm.setEdtbyusername(this.smtUserService.queryById(hqtCheckorder.getEdtbyuserid()).getUsername());
                if ((hqtCheckorder.getCheckerid() != null) && !hqtCheckorder.getCheckerid().equals(""))
                    hqtCheckOrderForm.setCheckername((this.smtCompanyService.queryById(hqtCheckorder.getCheckerid()) == null) ? "" :
                            this.smtCompanyService.queryById(hqtCheckorder.getCheckerid()).getCompanyname());
                hqtCheckOrderForm.setCustorder(this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid()).getOrderid());
                hqtCheckOrderForm.setSamplename(this.hqtCustomerorderService.queryById(hqtCheckorder.getCustorderid()).getSamplename());
                if ((hqtCheckorder.getSender() != null) && !hqtCheckorder.getSender().equals(""))
                    hqtCheckOrderForm.setSendername((this.smtUserService.queryById(hqtCheckorder.getSender()) == null) ? "" :
                            this.smtUserService.queryById(hqtCheckorder.getSender()).getUsername());
                if ((hqtCheckorder.getAccepter() != null) && !hqtCheckorder.getAccepter().equals(""))
                    hqtCheckOrderForm.setAcceptername((this.smtUserService.queryById(hqtCheckorder.getAccepter()) == null) ? "" :
                            this.smtUserService.queryById(hqtCheckorder.getAccepter()).getUsername());
                hqtCheckApplyOrderForms.add(hqtCheckOrderForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total", this.hqtCheckorderService.getCount(hqtCheckorderParam, ins, betweens));
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", hqtCheckApplyOrderForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取检测报告出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     * * @param hqtCheckOrderForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "更新检测订单财务账务信息")
    @PostMapping("updateAccountInfo")
    public Result updateAccountInfo(@RequestBody HqtCheckOrderForm hqtCheckOrderForm, HttpServletRequest request) {
        HqtCheckorder hqtCheckorder = this.hqtCheckorderService.queryById(hqtCheckOrderForm.getId());
        try {
            hqtCheckorder.setPayamt(hqtCheckOrderForm.getPayamt());
            if (hqtCheckorder.getAmt() <= hqtCheckorder.getPayamt()) {
                hqtCheckorder.setPaystatus("3");  //已付款
                if (hqtCheckorder.getInvoicestatus().equals("2"))  //已收票
                    hqtCheckorder.setStatus("4");  //设置为结案
            } else if ((hqtCheckorder.getAmt() > hqtCheckorder.getPayamt()) && (hqtCheckorder.getPayamt() > 0))
                hqtCheckorder.setPaystatus("2");  //部分付款
            else
                hqtCheckorder.setPaystatus("1");  //未付款
            hqtCheckorder.setPayremark(hqtCheckOrderForm.getPayremark());
            hqtCheckorder.setPaydate(hqtCheckorder.getPaystatus().equals("1")? null:hqtCheckOrderForm.getPaydate());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCheckorder.setEdtbyuserid(currentUser.get("id").toString());
            hqtCheckorder.setPayrecorder(hqtCheckorder.getPaystatus().equals("1")? null:currentUser.get("id").toString());
            Date date = new Date();
            hqtCheckorder.setEdttime(date);
            this.hqtCheckorderService.update(hqtCheckorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckOrder",request.getRemoteAddr(),
                    "更新财务账务信息【"+hqtCheckorder.getOrderid()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("edttime", date);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("edttime", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     * * @param hqtCheckOrderForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "更新检测订单财务发票信息")
    @PostMapping("updateInvoiceInfo")
    public Result updateInvoiceInfo(@RequestBody HqtCheckOrderForm hqtCheckOrderForm, HttpServletRequest request) {
        HqtCheckorder hqtCheckorder = this.hqtCheckorderService.queryById(hqtCheckOrderForm.getId());
        try {
            hqtCheckorder.setInvoicestatus(hqtCheckOrderForm.getInvoicestatus());
            hqtCheckorder.setInvoiceid(hqtCheckorder.getInvoicestatus().equals("1")? null:hqtCheckOrderForm.getInvoiceid());
            hqtCheckorder.setInvoiceremark(hqtCheckOrderForm.getInvoiceremark());
            hqtCheckorder.setInvoicedate(hqtCheckorder.getInvoicestatus().equals("1")? null:hqtCheckOrderForm.getInvoicedate());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCheckorder.setEdtbyuserid(currentUser.get("id").toString());
            hqtCheckorder.setInvoicerecorder(hqtCheckorder.getInvoicestatus().equals("1")? null:currentUser.get("id").toString());
            Date date = new Date();
            hqtCheckorder.setEdttime(date);
            this.hqtCheckorderService.update(hqtCheckorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCheckOrder",request.getRemoteAddr(),
                    "更新财务发票信息【"+hqtCheckorder.getOrderid()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("edttime", date);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("edttime", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param searchDate 统计日期
     * @param statisType 统计类型 1.本单位 2.本人
     * @return 单条数据
     */
    @GetMapping("getCheckerStatistic")
    public Result getCheckerStatistic(String searchDate, int statisType) {
        try {
            List<HqtCheckerStatisticForm> hqtCheckerStatisticForms = this.hqtStatisticService.getCheckerStatistic(jwtConfig.getCurrentUser().get("id").toString(), searchDate, statisType);
            JSONObject resData = new JSONObject();
            resData.put("data", hqtCheckerStatisticForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 委托方数据统计
     * @param rightValue    权限值
     *
     * @return 单条数据
     */
    @GetMapping("getCheckOrderStat")
    public Result getCheckOrderStat(String rightValue) throws ParseException {
        HqtCheckOrderStatForm hqtCheckOrderStatForm = new HqtCheckOrderStatForm();
        List<Map<String, List<String>>> ins = new ArrayList<>();
        if ((rightValue != null) && !rightValue.equals("")) {
            Map currentUser = jwtConfig.getCurrentUser();
            if (currentUser.get("usercompanytype").toString().equals("2")) {  //当前用户为运营机构用户
                if (rightValue.substring(11).equals("0")) { //无全部数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(9, 10).equals("1")) {  //有单位数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else if (rightValue.substring(10, 11).equals("1")) {  //有部门数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setDeptid(currentUser.get("userdept").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else {  //无部门数据权限，则仅获取接单人为当前用户的数据
                        inData.add(currentUser.get("id").toString());
                    }
                    Map<String, List<String>> in = new HashMap();
                    in.put("Sender",inData);
                    ins.add(in);
                }
            }
            if (currentUser.get("usercompanytype").toString().equals("3")) {  //当前用户为检测机构用户
                if (rightValue.substring(11).equals("0") && rightValue.substring(9, 10).equals("0")) { //无全部数据和单位数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(10, 11).equals("1")) {  //有部门数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setDeptid(currentUser.get("userdept").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        inData.add("");
                        for (SmtUser smtUser : smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    } else {  //无部门数据权限，则仅获取接单人为当前用户的数据
                        inData.add("");
                        inData.add(currentUser.get("id").toString());
                    }
                    Map<String, List<String>> in = new HashMap();
                    in.put("Accepter",inData);
                    ins.add(in);
                }
            }
        }
        try {
            //统计今日新增订单数------------------------------------
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            HqtCheckorder todayAppendTotalParam = new HqtCheckorder();
            todayAppendTotalParam.setOrderdate(formatter.parse(formatter.format(date)));
            todayAppendTotalParam.setStatus("2|3|4");
            hqtCheckOrderStatForm.setTodayAppendTotal(this.hqtCheckorderService.getCount(todayAppendTotalParam, ins, null));
            //---------------------------------------------------
            //统计待接单数------------------------------------
            HqtCheckorder willAcceptTotalParam = new HqtCheckorder();
            willAcceptTotalParam.setStatus("1");  //已提交
            hqtCheckOrderStatForm.setWillAcceptTotal(this.hqtCheckorderService.getCount(willAcceptTotalParam, ins, null));
            //---------------------------------------------------
            //统计今日已完成订单数------------------------------------
            HqtCheckorder todayFinishTotalParam = new HqtCheckorder();
            todayFinishTotalParam.setOrderdate(formatter.parse(formatter.format(date)));
            todayFinishTotalParam.setStatus("3|4");  //已完工或已结案
            hqtCheckOrderStatForm.setTodayFinishTotal(this.hqtCheckorderService.getCount(todayFinishTotalParam, ins, null));
            //---------------------------------------------------
            //统计总订单数------------------------------------
            HqtCheckorder orderTotalParam = new HqtCheckorder();
            orderTotalParam.setStatus("2|3|4");
            hqtCheckOrderStatForm.setOrderTotal(this.hqtCheckorderService.getCount(orderTotalParam, ins, null));
            //---------------------------------------------------
            //统计正在检测订单数------------------------------------
            HqtCheckorder checkingTotalParam = new HqtCheckorder();
            checkingTotalParam.setStatus("2");  //已审核
            hqtCheckOrderStatForm.setCheckingTotal(this.hqtCheckorderService.getCount(checkingTotalParam, ins, null));
            //---------------------------------------------------
            //统计正在检测订单数------------------------------------
            HqtCheckorder checkNopassTotalParam = new HqtCheckorder();
            checkNopassTotalParam.setStatus("3|4");  //已完工或已结案
            checkNopassTotalParam.setCheckresult("2");  //不合格
            hqtCheckOrderStatForm.setCheckNopassTotal(this.hqtCheckorderService.getCount(checkNopassTotalParam, ins, null));
            //---------------------------------------------------
            JSONObject resData = new JSONObject();
            resData.put("data", hqtCheckOrderStatForm);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取检测订单统计出错！" + e.getMessage(), resData);
        }
    }
}
