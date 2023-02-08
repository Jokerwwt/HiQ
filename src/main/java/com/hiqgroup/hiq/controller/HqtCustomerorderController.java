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
 * 委托方订单(HqtCustomerorder)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-02 14:30:30
 */
@RestController
@RequestMapping("hqtCustomerorder")
public class HqtCustomerorderController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HqtCustomerorderService hqtCustomerorderService;
    @Resource
    private HqtCheckorderService hqtCheckorderService;
    @Resource
    private SmtUserService smtUserService;
    @Resource
    private SmtCompanyService smtCompanyService;
    @Resource
    private SmtCustomertypeService smtCustomertypeService;
    @Resource
    private HqtStatisticService hqtStatisticService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HqtCustomerorder selectOne(String id) {
        return this.hqtCustomerorderService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) throws ParseException {
        HqtCustomerorder hqtCustomerorderParam = new HqtCustomerorder();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        if ((search.get("customerid") != null) && !search.get("customerid").equals(""))
            hqtCustomerorderParam.setCustomerid(search.get("customerid").toString());
        if (search.get("orderid") != null)
            hqtCustomerorderParam.setOrderid(search.get("orderid").toString());
        if ((search.get("ordername") != null) && !search.get("ordername").equals(""))
            hqtCustomerorderParam.setOrdername(search.get("ordername").toString());
        if ((search.get("orderdate") != null) && !search.get("orderdate").equals(""))
            hqtCustomerorderParam.setOrderdate(new SimpleDateFormat("yyyy-MM-dd").parse(search.get("orderdate").toString()));
        if ((search.get("status") != null) && !search.get("status").equals(""))
            hqtCustomerorderParam.setStatus(search.get("status").toString());
        if ((search.get("instatus") != null) && !search.get("instatus").equals(""))
            hqtCustomerorderParam.setInstatus(search.get("instatus").toString());
        if ((search.get("invoicestatus") != null) && !search.get("invoicestatus").equals(""))
            hqtCustomerorderParam.setInvoicestatus(search.get("invoicestatus").toString());
        if ((search.get("indate") != null) && !search.get("indate").equals(""))
            hqtCustomerorderParam.setIndate(new SimpleDateFormat("yyyy-MM-dd").parse(search.get("indate").toString()));
        if ((search.get("inrecordername") != null) && !search.get("inrecordername").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("inrecordername").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCustomerorderParam.setInrecorder(smtUsers.get(0).getId());
            }else{
                hqtCustomerorderParam.setInrecorder("###");
            }
        }
        if ((search.get("inremark") != null) && !search.get("inremark").equals(""))
            hqtCustomerorderParam.setInremark(search.get("inremark").toString());
        if ((search.get("invoicedate") != null) && !search.get("invoicedate").equals(""))
            hqtCustomerorderParam.setInvoicedate(new SimpleDateFormat("yyyy-MM-dd").parse(search.get("invoicedate").toString()));
        if ((search.get("invoicerecordername") != null) && !search.get("invoicerecordername").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("invoicerecordername").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCustomerorderParam.setInvoicerecorder(smtUsers.get(0).getId());
            }else{
                hqtCustomerorderParam.setInvoicerecorder("###");
            }
        }
        if ((search.get("invoiceremark") != null) && !search.get("invoiceremark").equals(""))
            hqtCustomerorderParam.setInvoiceremark(search.get("invoiceremark").toString());
        if ((search.get("chargemanname") != null) && !search.get("chargemanname").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("chargemanname").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCustomerorderParam.setChargeman(smtUsers.get(0).getId());
            }else{
                hqtCustomerorderParam.setChargeman("###");
            }
        }
        if (param.getSort() == null) param.setSort("EdtTime");
        if (param.getOrdertype() == null) param.setOrdertype("desc");
        List<Map<String, List<String>>> ins = new ArrayList<>();
        if ((search.get("rightvalue") != null) && !search.get("rightvalue").equals("")) {
            Map currentUser = jwtConfig.getCurrentUser();
            String rightValue = search.get("rightvalue").toString();
            if (currentUser.get("usercompanytype").toString().equals("2")) {  //当前用户为运营机构用户
                if (rightValue.substring(10).equals("0")) { //无全部数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(8, 9).equals("1")){  //有单位数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        for (SmtUser smtUser: smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    }else if (rightValue.substring(9, 10).equals("1")) {  //有部门数据权限
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
                    in.put("ChargeMan",inData);
                    ins.add(in);
                }
            } else if (currentUser.get("usercompanytype").toString().equals("1")) {  //当前用户为生产厂家用户
                if (rightValue.substring(10).equals("0") && rightValue.substring(8, 9).equals("0")) {
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(9, 10).equals("1")) {  //有部门数据权限
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
                    in.put("OrderMan",inData);
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
            List<HqtCustomerorder> hqtCustomerorders = this.hqtCustomerorderService.queryAllByLimit(hqtCustomerorderParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(), ins, betweens);
            List<HqtCustomerorderForm> hqtCustomerorderForms = new ArrayList<>();
            for (HqtCustomerorder hqtCustomerorder : hqtCustomerorders) {
                HqtCustomerorderForm hqtCustomerorderForm = new HqtCustomerorderForm();
                CommonUtil.copyObjProperties(hqtCustomerorderForm, hqtCustomerorder);
                if ((hqtCustomerorder.getCheckers() != null) && !hqtCustomerorder.getCheckers().equals("")) {
                    String[] checkers = hqtCustomerorder.getCheckers().split(",");
                    String checkersName = "";
                    for (String checker : checkers) {
                        checkersName = checkersName + "," + this.smtCompanyService.queryById(checker).getCompanyname();
                    }
                    hqtCustomerorderForm.setCheckersname(checkersName.trim().substring(1));
                }
                hqtCustomerorderForm.setEdtbyusername((this.smtUserService.queryById(hqtCustomerorder.getEdtbyuserid()) == null)? "" :
                        this.smtUserService.queryById(hqtCustomerorder.getEdtbyuserid()).getUsername());
                hqtCustomerorderForm.setCustomername((this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()) == null)? "" :
                        this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()).getCompanyname());
                hqtCustomerorderForm.setChargemanname((this.smtUserService.queryById(hqtCustomerorder.getChargeman()) == null)? "" :
                        this.smtUserService.queryById(hqtCustomerorder.getChargeman()).getUsername());
                hqtCustomerorderForm.setOrdermanname((this.smtUserService.queryById(hqtCustomerorder.getOrderman()) == null)? "" :
                        this.smtUserService.queryById(hqtCustomerorder.getOrderman()).getUsername());
                hqtCustomerorderForm.setInrecordername((this.smtUserService.queryById(hqtCustomerorder.getInrecorder()) == null)? "" :
                        this.smtUserService.queryById(hqtCustomerorder.getInrecorder()).getUsername());
                hqtCustomerorderForm.setInvoicerecordername((this.smtUserService.queryById(hqtCustomerorder.getInvoicerecorder()) == null)? "" :
                        this.smtUserService.queryById(hqtCustomerorder.getInvoicerecorder()).getUsername());
                hqtCustomerorderForms.add(hqtCustomerorderForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total", this.hqtCustomerorderService.getCount(hqtCustomerorderParam, ins, betweens));
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", hqtCustomerorderForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取委托订单出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     * * @param hqtCustomerorderForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "更新委托订单信息")
    @PostMapping("update")
    public Result update(@RequestBody HqtCustomerorderForm hqtCustomerorderForm, HttpServletRequest request) {
        HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(hqtCustomerorderForm.getId());
        try {
            if (hqtCustomerorder.getStatus().equals("2")) {  //单据为审核状态
                if (hqtCustomerorderForm.getStatus().equals("1")) {   //执行取消审核操作
                    HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
                    hqtCheckorderParam.setCustorderid(hqtCustomerorder.getId());
                    List<HqtCheckorder> hqtCheckorders = this.hqtCheckorderService.queryAll(hqtCheckorderParam);
                    if (hqtCheckorders.size() > 0)
                        return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "此应委托单已下检测订单，不可取消审核！");
                }
            }
            if (hqtCustomerorderForm.getStatus().equals("-1")) {   //执行作废操作
                HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
                hqtCheckorderParam.setCustorderid(hqtCustomerorder.getId());
                List<HqtCheckorder> hqtCheckorders = this.hqtCheckorderService.queryAll(hqtCheckorderParam);
                if (hqtCheckorders.size() > 0)
                    return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "此应委托单已下检测订单，不可作废！");
            }
            CommonUtil.copyObjProperties(hqtCustomerorder, hqtCustomerorderForm);
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCustomerorder.setEdtbyuserid(currentUser.get("id").toString());
            Date date = new Date();
            hqtCustomerorder.setEdttime(date);
            this.hqtCustomerorderService.update(hqtCustomerorder);
            //记录操作日志-----------
            String operate = "修改";
            switch (hqtCustomerorderForm.getStatus()) {
                case "-1":
                    operate = "执行作废";
                    break; //可选
                case "2":
                    operate = "执行审核";
                    break; //可选
                default: //可选
                    operate = "修改";
            }
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(), "HqtCustomerOrder", request.getRemoteAddr(),
                    operate+"【" + hqtCustomerorder.getOrderid() + "】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")) {
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("edttime", date);
            resData.put("status", hqtCustomerorder.getStatus());
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
     * * @param hqtCustomerorderForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "新增委托订单信息")
    @PostMapping("create")
    public Result create(@RequestBody HqtCustomerorderForm hqtCustomerorderForm, HttpServletRequest request) {
        HqtCustomerorder hqtCustomerorder = new HqtCustomerorder();
        try {
            CommonUtil.copyObjProperties(hqtCustomerorder, hqtCustomerorderForm);
            hqtCustomerorder.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCustomerorder.setEdtbyuserid(currentUser.get("id").toString());
            hqtCustomerorder.setChargeman(currentUser.get("id").toString());
            hqtCustomerorder.setInamt((double) 0);
            Date date = new Date();
            hqtCustomerorder.setEdttime(date);
            HqtCustomerorder hqtCustomerorderParam = new HqtCustomerorder();
            hqtCustomerorderParam.setCustomerid(hqtCustomerorder.getCustomerid());
            hqtCustomerorder.setOrderid("C" + this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()).getCompanyid() +
                    new SimpleDateFormat("yyyyMMdd").format(date) + String.valueOf(this.hqtCustomerorderService.getCount(hqtCustomerorderParam, null, null) + 1));
            this.hqtCustomerorderService.insert(hqtCustomerorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCustomerOrder",request.getRemoteAddr(),
                    "新增【"+hqtCustomerorder.getOrderid()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            JSONObject resData = new JSONObject();
            resData.put("id", hqtCustomerorder.getId());
            resData.put("orderid", hqtCustomerorder.getOrderid());
            resData.put("customername", this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()).getCompanyname());
            resData.put("edttime", date);
            resData.put("status", hqtCustomerorder.getStatus());
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("id", null);
            resData.put("orderid", null);
            resData.put("customername", null);
            resData.put("edttime", null);
            resData.put("status", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 批量删除数据
     * * @param customerordeIds
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "删除委托订单信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> customerordeIds, HttpServletRequest request) {
        try {
            for (String customerordeId : customerordeIds) {
                HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(customerordeId);
                this.hqtCustomerorderService.deleteById(customerordeId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCustomerOrder",request.getRemoteAddr(),
                        "删除【"+hqtCustomerorder.getOrderid()+"】", request.getHeader("User-Agent"));
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
    @ApiOperation(value = "更新委托订单过程记录信息")
    @PostMapping("updateProcrecord")
    public Result updateProcrecord(@RequestBody HqtCustomerorderForm hqtCustomerorderForm) {
        HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(hqtCustomerorderForm.getId());
        try {
            hqtCustomerorder.setProcrecord(hqtCustomerorderForm.getProcrecord());
            this.hqtCustomerorderService.update(hqtCustomerorder);
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage());
        }
    }

    /**
     * 通过主键更新单条数据
     * * @param hqtCustomerorderForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "更新委托订单财务账务信息")
    @PostMapping("updateAccountInfo")
    public Result updateAccountInfo(@RequestBody HqtCustomerorderForm hqtCustomerorderForm, HttpServletRequest request) {
        HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(hqtCustomerorderForm.getId());
        try {
            hqtCustomerorder.setInamt(hqtCustomerorderForm.getInamt());
            if (hqtCustomerorder.getAmt() <= hqtCustomerorder.getInamt()) {  //全部收款
                hqtCustomerorder.setInstatus("3");
                if (hqtCustomerorder.getInvoicestatus().equals("4"))  //委托方已收票
                    hqtCustomerorder.setStatus("5");  //设置为结案
                else
                    hqtCustomerorder.setStatus(getCustomerorderStatus(hqtCustomerorder.getId()));
            } else if ((hqtCustomerorder.getAmt() > hqtCustomerorder.getInamt()) && (hqtCustomerorder.getInamt() > 0)) {  //部分收款
                hqtCustomerorder.setInstatus("2");
                hqtCustomerorder.setStatus(getCustomerorderStatus(hqtCustomerorder.getId()));
            } else {  //未收款
                hqtCustomerorder.setInstatus("1");
                hqtCustomerorder.setStatus(getCustomerorderStatus(hqtCustomerorder.getId()));
            }
            hqtCustomerorder.setInremark(hqtCustomerorderForm.getInremark());
            hqtCustomerorder.setIndate(hqtCustomerorder.getInstatus().equals("1")? null:hqtCustomerorderForm.getIndate());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCustomerorder.setEdtbyuserid(currentUser.get("id").toString());
            hqtCustomerorder.setInrecorder(hqtCustomerorder.getInstatus().equals("1")? null:currentUser.get("id").toString());
            Date date = new Date();
            hqtCustomerorder.setEdttime(date);
            this.hqtCustomerorderService.update(hqtCustomerorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCustomerOrder",request.getRemoteAddr(),
                    "更新财务账务信息【"+hqtCustomerorderForm.getOrderid()+"】", request.getHeader("User-Agent"));
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
     * * @param hqtCustomerorderForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "更新委托订单财务发票信息")
    @PostMapping("updateInvoiceInfo")
    public Result updateInvoiceInfo(@RequestBody HqtCustomerorderForm hqtCustomerorderForm, HttpServletRequest request) {
        HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(hqtCustomerorderForm.getId());
        try {
            hqtCustomerorder.setInvoicestatus(hqtCustomerorderForm.getInvoicestatus());
            hqtCustomerorder.setInvoiceid(hqtCustomerorder.getInvoicestatus().equals("1")? "":hqtCustomerorderForm.getInvoiceid());
            hqtCustomerorder.setInvoiceremark(hqtCustomerorderForm.getInvoiceremark());
            hqtCustomerorder.setInvoicedate(hqtCustomerorder.getInvoicestatus().equals("1")? null:hqtCustomerorderForm.getInvoicedate());
            Map currentUser = jwtConfig.getCurrentUser();
            hqtCustomerorder.setEdtbyuserid(currentUser.get("id").toString());
            hqtCustomerorder.setInvoicerecorder(hqtCustomerorder.getInvoicestatus().equals("1")? null:currentUser.get("id").toString());
            Date date = new Date();
            hqtCustomerorder.setEdttime(date);
            this.hqtCustomerorderService.update(hqtCustomerorder);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HqtCustomerOrder",request.getRemoteAddr(),
                    "更新财务发票信息【"+hqtCustomerorderForm.getOrderid()+"】", request.getHeader("User-Agent"));
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

    private String getCustomerorderStatus(String customerorderId) {
        HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
        hqtCheckorderParam.setCustorderid(customerorderId);
        List<HqtCheckorder> hqtCheckorders = this.hqtCheckorderService.queryAll(hqtCheckorderParam);
        if (hqtCheckorders.size() > 0){
            HqtCheckorder hqtCheckorder = this.hqtCheckorderService.queryAll(hqtCheckorderParam).get(0);
            if (hqtCheckorder.getStatus().equals("2")) return "3";
            else if (hqtCheckorder.getStatus().equals("3")) return "4";
            else return "2";
        }else return "2";
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("getOrderView")
    public Result getOrderView(PagedQueryForm<T> param) throws ParseException {
        HqtCustomerorder hqtCustomerorderParam = new HqtCustomerorder();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        if ((search.get("customerid") != null) && !search.get("customerid").equals(""))
            hqtCustomerorderParam.setCustomerid(search.get("customerid").toString());
        if (search.get("orderid") != null)
            hqtCustomerorderParam.setOrderid(search.get("orderid").toString());
        if (search.get("ordername") != null)
            hqtCustomerorderParam.setOrdername(search.get("ordername").toString());
        if ((search.get("orderdate") != null) && !search.get("orderdate").equals(""))
            hqtCustomerorderParam.setOrderdate(new SimpleDateFormat("yyyy-MM-dd").parse(search.get("orderdate").toString()));
        if ((search.get("status") != null) && !search.get("status").equals(""))
            hqtCustomerorderParam.setStatus(search.get("status").toString());
        if ((search.get("chargemanname") != null) && !search.get("chargemanname").equals("")) {
            SmtUser smtUserParam = new SmtUser();
            smtUserParam.setUsername(search.get("chargemanname").toString());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if(smtUsers.size() > 0){
                hqtCustomerorderParam.setChargeman(smtUsers.get(0).getId());
            }else{
                hqtCustomerorderParam.setChargeman("###");
            }
        }
        if (param.getSort() == null) param.setSort("EdtTime");
        if (param.getOrdertype() == null) param.setOrdertype("desc");
        //--------处理In查询---------------------------------------------------
        List<Map<String, List<String>>> ins = new ArrayList<>();
        if ((search.get("rightvalue") != null) && !search.get("rightvalue").equals("")) {
            Map currentUser = jwtConfig.getCurrentUser();
            String rightValue = search.get("rightvalue").toString();
            if (rightValue.substring(3,4).equals("0")) { //无全部数据权限
                List<String> inData = new ArrayList<>();
                if (rightValue.substring(1, 2).equals("1")){  //有单位数据权限
                    SmtUser smtUserParam = new SmtUser();
                    smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                    List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                    for (SmtUser smtUser: smtUsers) {
                        inData.add(smtUser.getId());
                    }
                }else if (rightValue.substring(2, 3).equals("1")) {  //有部门数据权限
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
                in.put("ChargeMan",inData);
                ins.add(in);
            }
        }
        if (((search.get("checkerid") != null) && !search.get("checkerid").equals("")) || ((search.get("checkid") != null) && !search.get("checkid").equals(""))){  //检测单位和检测单号查询
            HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
            if ((search.get("checkerid") != null) && !search.get("checkerid").equals("")){
                hqtCheckorderParam.setCheckerid(search.get("checkerid").toString());
            }
            if ((search.get("checkid") != null) && !search.get("checkid").equals("")){
                hqtCheckorderParam.setOrderid(search.get("checkid").toString());
            }
            List<HqtCheckorder> hqtCheckorders = this.hqtCheckorderService.queryAll(hqtCheckorderParam);
            if (hqtCheckorders.size() > 0 ){
                List<String> inData = new ArrayList<>();
                for (HqtCheckorder hqtCheckorder : hqtCheckorders) {
                    inData.add(hqtCheckorder.getCustorderid());
                }
                Map<String, List<String>> in = new HashMap();
                in.put("Id",inData);
                ins.add(in);
            }
        }
        //---------------处理范围查询---------------------------
        List<Map<String, List<String>>> betweens = new ArrayList<>();
        if ((search.get("orderdateB") != null) && (search.get("orderdateE") != null)){
            List<String> betweenData = new ArrayList<>();
            betweenData.add(search.get("orderdateB").toString());
            betweenData.add((search.get("orderdateE").toString().trim().equals("")? "3000-12-31":search.get("orderdateE").toString()));
            Map<String, List<String>> between = new HashMap();
            between.put("OrderDate",betweenData);
            betweens.add(between);
        }
        if ((search.get("indateB") != null) && (search.get("indateE") != null)){
            List<String> betweenData = new ArrayList<>();
            betweenData.add(search.get("indateB").toString());
            betweenData.add((search.get("indateE").toString().trim().equals("")? "3000-12-31":search.get("indateE").toString()));
            Map<String, List<String>> between = new HashMap();
            between.put("InDate",betweenData);
            betweens.add(between);
        }
        //---------------------------------------------------------------
        try {
            List<HqtCustomerorder> hqtCustomerorders = this.hqtCustomerorderService.queryAllByLimit(hqtCustomerorderParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(), ins, betweens);
            List<HqtOrderViewForm> hqtOrderViewForms = new ArrayList<>();
            for (HqtCustomerorder hqtCustomerorder : hqtCustomerorders) {
                HqtOrderViewForm hqtOrderViewForm = new HqtOrderViewForm();
                CommonUtil.copyObjProperties(hqtOrderViewForm, hqtCustomerorder);
                if ((hqtCustomerorder.getCustomerid() != null) && !hqtCustomerorder.getCustomerid().equals("")) {
                    hqtOrderViewForm.setCustomername((this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()) == null) ? "" :
                            this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()).getCompanyname());
                    hqtOrderViewForm.setCustomertypename((this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()) == null) ? "" :
                            ((this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()).getCustomertype() == null)? "" :
                                    this.smtCustomertypeService.queryById(this.smtCompanyService.queryById(hqtCustomerorder.getCustomerid()).getCustomertype()).getTypename()));
                }
                if ((hqtCustomerorder.getChargeman() != null) && !hqtCustomerorder.getChargeman().equals(""))
                    hqtOrderViewForm.setChargemanname((this.smtUserService.queryById(hqtCustomerorder.getChargeman()) == null) ? "" :
                            this.smtUserService.queryById(hqtCustomerorder.getChargeman()).getUsername());
                if ((hqtCustomerorder.getOrderman() != null) && !hqtCustomerorder.getOrderman().equals(""))
                    hqtOrderViewForm.setOrdermanname((this.smtUserService.queryById(hqtCustomerorder.getOrderman()) == null) ? "" :
                            this.smtUserService.queryById(hqtCustomerorder.getOrderman()).getUsername());
                HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
                hqtCheckorderParam.setCustorderid(hqtCustomerorder.getId());
                List<HqtCheckorder> hqtCheckorders = this.hqtCheckorderService.queryAll(hqtCheckorderParam);
                if (hqtCheckorders.size() > 0){
                    hqtOrderViewForm.setCheckid(hqtCheckorders.get(0).getId());
                    hqtOrderViewForm.setCheckorderid(hqtCheckorders.get(0).getOrderid());
                    hqtOrderViewForm.setCheckordername(hqtCheckorders.get(0).getOrdername());
                    hqtOrderViewForm.setCheckorderdate(hqtCheckorders.get(0).getOrderdate());
                    hqtOrderViewForm.setCheckstatus(hqtCheckorders.get(0).getStatus());
                    hqtOrderViewForm.setCheckhopeenddate(hqtCheckorders.get(0).getHopeenddate());
                    hqtOrderViewForm.setCheckfinisheddate(hqtCheckorders.get(0).getFinisheddate());
                    hqtOrderViewForm.setCheckid(hqtCheckorders.get(0).getCheckerid());
                    hqtOrderViewForm.setCheckcontactor(hqtCheckorders.get(0).getContactor());
                    hqtOrderViewForm.setReportexptime(hqtCheckorders.get(0).getReportexptime());
                    hqtOrderViewForm.setCheckamt(hqtCheckorders.get(0).getAmt());
                    hqtOrderViewForm.setCheckpayamt(hqtCheckorders.get(0).getPayamt());
                    hqtOrderViewForm.setCheckpaystatus(hqtCheckorders.get(0).getPaystatus());
                    hqtOrderViewForm.setCheckinvoiceid(hqtCheckorders.get(0).getInvoiceid());
                    hqtOrderViewForm.setCheckinvoicestatus(hqtCheckorders.get(0).getInvoicestatus());
                    hqtOrderViewForm.setReportid(hqtCheckorders.get(0).getReportid());
                    hqtOrderViewForm.setReportenddate(hqtCheckorders.get(0).getReportenddate());
                    hqtOrderViewForm.setCheckprocrecord(hqtCheckorders.get(0).getProcrecord());
                    hqtOrderViewForm.setCheckremark(hqtCheckorders.get(0).getRemark());
                    hqtOrderViewForm.setSender(hqtCheckorders.get(0).getSender());
                    hqtOrderViewForm.setCheckresult(hqtCheckorders.get(0).getCheckresult());
                    hqtOrderViewForm.setChecktelno(hqtCheckorders.get(0).getTelno());
                    if (hqtCheckorders.get(0).getAmt() != null)
                        hqtOrderViewForm.setGrossprofit(hqtCustomerorder.getAmt() - hqtCheckorders.get(0).getAmt());
                    if ((hqtCheckorders.get(0).getCheckerid() != null) && !hqtCheckorders.get(0).getCheckerid().equals(""))
                        hqtOrderViewForm.setCheckername((this.smtCompanyService.queryById(hqtCheckorders.get(0).getCheckerid()) == null)? "":
                                this.smtCompanyService.queryById(hqtCheckorders.get(0).getCheckerid()).getCompanyname());
                    if ((hqtCheckorders.get(0).getSender() != null) && !hqtCheckorders.get(0).getSender().equals(""))
                        hqtOrderViewForm.setSendername((this.smtUserService.queryById(hqtCheckorders.get(0).getSender()) == null)? "":
                                this.smtUserService.queryById(hqtCheckorders.get(0).getSender()).getUsername());
                }
                hqtOrderViewForms.add(hqtOrderViewForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total", this.hqtCustomerorderService.getCount(hqtCustomerorderParam, ins, betweens));
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", hqtOrderViewForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取订单出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getRecommCheckers")
    public Result getRecommCheckers(String id) {
        try {
            HqtCustomerorder hqtCustomerorder = this.hqtCustomerorderService.queryById(id);
            if (hqtCustomerorder == null) {
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "对应的委托订单未找到！");
            }
            List<SmtCompany> smtCompanies = new ArrayList<>();
            if ((hqtCustomerorder.getCheckers() != null) && !hqtCustomerorder.getCheckers().equals("")) {
                String[] checkers = hqtCustomerorder.getCheckers().split(",");
                for (String checker : checkers) {
                    smtCompanies.add(this.smtCompanyService.queryById(checker));
                }
            }
            JSONObject resData = new JSONObject();
            resData.put("data", smtCompanies);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param searchDate    统计日期
     * @param statisType  统计类型 1.本单位 2.本人
     * @return 单条数据
     */
    @GetMapping("getCustStatistic")
    public Result getCustStatistic(String searchDate, int statisType) {
        try {
//            Date inDate = (new SimpleDateFormat("yyyy-MM-dd")).parse(searchDate);
            List<HqtCustStatisticForm> hqtCustStatisticForms = this.hqtStatisticService.getCustStatistic(jwtConfig.getCurrentUser().get("id").toString(),searchDate,statisType);
            JSONObject resData = new JSONObject();
            resData.put("data", hqtCustStatisticForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 通过查询单体检统计运营方数据
     *
     * @param searchDate    统计日期
     * @param statisType  统计类型 1.本单位 2.本人
     * @return 单条数据
     */
    @GetMapping("getPlatformStatistic")
    public Result getPlatformStatistic(String searchDate, int statisType) {
        try {
            List<HqtPlatformStatisticForm> hqtPlatformStatisticForms = this.hqtStatisticService.getPlatformStatistic(jwtConfig.getCurrentUser().get("id").toString(),searchDate,statisType);
            JSONObject resData = new JSONObject();
            resData.put("data", hqtPlatformStatisticForms);
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
    @GetMapping("getCustOrderStat")
    public Result getCustOrderStat(String rightValue) throws ParseException {
        HqtCustOrderStatForm hqtCustOrderStatForm = new HqtCustOrderStatForm();
        List<Map<String, List<String>>> ins = new ArrayList<>();
        if ((rightValue != null) && !rightValue.equals("")) {
            Map currentUser = jwtConfig.getCurrentUser();
            if (currentUser.get("usercompanytype").toString().equals("2")) {  //当前用户为运营机构用户
                if (rightValue.substring(10).equals("0")) { //无全部数据权限
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(8, 9).equals("1")){  //有单位数据权限
                        SmtUser smtUserParam = new SmtUser();
                        smtUserParam.setCompanyid(currentUser.get("usercompany").toString());
                        List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
                        for (SmtUser smtUser: smtUsers) {
                            inData.add(smtUser.getId());
                        }
                    }else if (rightValue.substring(9, 10).equals("1")) {  //有部门数据权限
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
                    in.put("ChargeMan",inData);
                    ins.add(in);
                }
            } else if (currentUser.get("usercompanytype").toString().equals("1")) {  //当前用户为生产厂家用户
                if (rightValue.substring(10).equals("0") && rightValue.substring(8, 9).equals("0")) {
                    List<String> inData = new ArrayList<>();
                    if (rightValue.substring(9, 10).equals("1")) {  //有部门数据权限
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
                    in.put("OrderMan",inData);
                    ins.add(in);
                }
            }
        }
        try {
            //统计今日新增订单数------------------------------------
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            HqtCustomerorder todayAppendTotalParam = new HqtCustomerorder();
            todayAppendTotalParam.setOrderdate(formatter.parse(formatter.format(date)));
            todayAppendTotalParam.setStatus("2|3|4|5");
            hqtCustOrderStatForm.setTodayAppendTotal(this.hqtCustomerorderService.getCount(todayAppendTotalParam, ins, null));
            //---------------------------------------------------
            //统计待接单数------------------------------------
            HqtCustomerorder willAcceptTotalParam = new HqtCustomerorder();
            willAcceptTotalParam.setStatus("1");
            hqtCustOrderStatForm.setWillAcceptTotal(this.hqtCustomerorderService.getCount(willAcceptTotalParam, ins, null));
            //---------------------------------------------------
            //统计今日已完成订单数------------------------------------
            HqtCustomerorder todayFinishTotalParam = new HqtCustomerorder();
            todayFinishTotalParam.setOrderdate(formatter.parse(formatter.format(date)));
            todayFinishTotalParam.setStatus("4|5");  //检测完成或已结案
            hqtCustOrderStatForm.setTodayFinishTotal(this.hqtCustomerorderService.getCount(todayFinishTotalParam, ins, null));
            //---------------------------------------------------
            //统计总订单数------------------------------------
            HqtCustomerorder orderTotalParam = new HqtCustomerorder();
            orderTotalParam.setStatus("2|3|4|5");
            hqtCustOrderStatForm.setOrderTotal(this.hqtCustomerorderService.getCount(orderTotalParam, ins, null));
            //---------------------------------------------------
            //统计正在检测订单数------------------------------------
            HqtCustomerorder checkingTotalParam = new HqtCustomerorder();
            checkingTotalParam.setStatus("3");  //检测中
            hqtCustOrderStatForm.setCheckingTotal(this.hqtCustomerorderService.getCount(checkingTotalParam, ins, null));
            //---------------------------------------------------
            //统计未下检测订单数------------------------------------
            HqtCustomerorder noToCheckTotalParam = new HqtCustomerorder();
            noToCheckTotalParam.setStatus("2");  //已审核
            hqtCustOrderStatForm.setNoToCheckTotal(this.hqtCustomerorderService.getCount(noToCheckTotalParam, ins, null));
            //---------------------------------------------------
            JSONObject resData = new JSONObject();
            resData.put("data", hqtCustOrderStatForm);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取委托订单统计出错！" + e.getMessage(), resData);
        }
    }

}
