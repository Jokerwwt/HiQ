package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.*;
import com.hiqgroup.hiq.form.PagedQueryForm;
import com.hiqgroup.hiq.form.SmtCompanyForm;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtCompanyService;
import com.hiqgroup.hiq.service.SmtUserService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 单位资料表(SmtCompany)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-08 17:59:48
 */
@RestController
@RequestMapping("smtCompany")
public class SmtCompanyController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private SmtCompanyService smtCompanyService;
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
    public Result selectOne(String id) throws InvocationTargetException, IllegalAccessException{
        try {
            SmtCompany smtCompany = this.smtCompanyService.queryById(id);
            JSONObject resData = new JSONObject();
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",smtCompany);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取单位信息出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) {
        SmtCompany smtCompanyParam = new SmtCompany();
        if (param.getSort() == null) param.setSort("CompanyName");
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        smtCompanyParam.setCompanyname(search.get("companyname").toString());
        if (!search.get("companytype").equals(""))
            smtCompanyParam.setCompanytype(Integer.parseInt(search.get("companytype").toString()));
        try {
            List<SmtCompany> smtCompanies = this.smtCompanyService.queryAllByLimit(smtCompanyParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(),null,null);
            List<SmtCompanyForm> smtCompanyForms = new ArrayList<>();
            for (SmtCompany smtCompany: smtCompanies) {
                SmtCompanyForm smtCompanyForm = new SmtCompanyForm();
                CommonUtil.copyObjProperties(smtCompanyForm,smtCompany);
                smtCompanyForm.setEdtbyusername((this.smtUserService.queryById(smtCompany.getEdtbyuserid()) == null)? "未知" :
                        this.smtUserService.queryById(smtCompany.getEdtbyuserid()).getUsername());
                smtCompanyForms.add(smtCompanyForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total",this.smtCompanyService.getCount(smtCompanyParam,null,null));
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",smtCompanyForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取单位出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     ** @param smtCompanyForm
     * @return 更新反馈
     */
    @ApiOperation(value = "更新单位信息")
    @PostMapping("update")
    public Result update(@RequestBody SmtCompanyForm smtCompanyForm, HttpServletRequest request) {
        SmtCompany smtCompany = this.smtCompanyService.queryById(smtCompanyForm.getId());
        try {
            CommonUtil.copyObjProperties(smtCompany,smtCompanyForm);
            Map currentUser = jwtConfig.getCurrentUser();
            smtCompany.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            smtCompany.setEdttime(date);
            this.smtCompanyService.update(smtCompany);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtCompany",request.getRemoteAddr(),
                    "修改【"+smtCompany.getCompanyname()+"】", request.getHeader("User-Agent"));
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
     ** @param smtCompanyForm
     * @return 更新反馈
     */
    @ApiOperation(value = "新增单位信息")
    @PostMapping("create")
    public Result create(@RequestBody SmtCompanyForm smtCompanyForm, HttpServletRequest request) {
        SmtCompany smtCompany = new SmtCompany();
        try {
            SmtCompany smtCompanyParam = new SmtCompany();
            smtCompanyParam.setCompanyname(smtCompanyForm.getCompanyname());
            if (this.smtCompanyService.queryAll(smtCompanyParam).size() > 0){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"该单位名称已存在！");
            }
            smtCompanyParam.setCompanyname("");
            smtCompanyParam.setCompanyid(smtCompanyForm.getCompanyid());
            if (this.smtCompanyService.queryAll(smtCompanyParam).size() > 0){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"该单位代码已存在！");
            }
            CommonUtil.copyObjProperties(smtCompany,smtCompanyForm);
            smtCompany.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            smtCompany.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            smtCompany.setEdttime(date);
            this.smtCompanyService.insert(smtCompany);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtCompany",request.getRemoteAddr(),
                    "新增【"+smtCompanyForm.getCompanyname()+"】", request.getHeader("User-Agent"));
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
     ** @param companyIds
     * @return 更新反馈
     */
    @ApiOperation(value = "删除单位信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> companyIds, HttpServletRequest request) {
        try {
            for (String companyId:companyIds) {
                SmtCompany smtCompany = this.smtCompanyService.queryById(companyId);
                this.smtCompanyService.deleteById(companyId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtCompany",request.getRemoteAddr(),
                        "删除【"+smtCompany.getCompanyname()+"】", request.getHeader("User-Agent"));
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

    @ApiOperation(value = "导入单位信息")
    @PostMapping("importData")
    @ResponseBody
    public Result importData(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            if(file.isEmpty()){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"文件不存在！");
            }
            InputStream in =null;
            try {
                in = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<List<Object>> listob = null;
            try {
                listob = new ExcelUtils().getBankListByExcel(in,file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
            }

            //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
            List<SmtCompany> smtCompanies = new ArrayList<>();
            int j = 0;
            for (int i = 0; i < listob.size(); i++) {
                List<Object> obj = listob.get(i);
                if ((obj.get(0) == null) || obj.get(0).toString().equals("")) continue;
                SmtCompany smtCompany = new SmtCompany();
                SmtCompany smtCompanyParam = new SmtCompany();
                smtCompanyParam.setCompanyname(obj.get(0).toString());
                if (this.smtCompanyService.queryAll(smtCompanyParam).size() > 0){
                    continue;
                }
                smtCompany.setId(UUID.randomUUID().toString());
                smtCompany.setCompanyname(obj.get(0).toString());
                if ((obj.get(1) == null) || obj.get(1).toString().equals("")){
                    return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"单位类别不能为空！");
                }
                smtCompany.setCompanytype((obj.get(1).toString().equals("生产工厂"))?1:((obj.get(1).toString().equals("运营机构"))?2:((obj.get(1).toString().equals("检测机构"))?3:((obj.get(1).toString().equals("监督机构"))?4:0))));
                smtCompany.setJuridical((obj.size() < 3)? "":obj.get(2).toString());
                smtCompany.setContactman((obj.size() < 4)? "":obj.get(3).toString());
                smtCompany.setTelno((obj.size() < 5)? "":obj.get(4).toString());
                smtCompany.setFaxno((obj.size() < 6)? "":obj.get(5).toString());
                smtCompany.setAddress((obj.size() < 7)? "":obj.get(6).toString());
                Map currentUser = jwtConfig.getCurrentUser();
                smtCompany.setEdtbyuserid(currentUser.get("id").toString());
                Date date =new Date();
                smtCompany.setEdttime(date);
                smtCompanies.add(smtCompany);
                j += 1;
            }
            if (smtCompanies.size() > 0) this.smtCompanyService.insertOrUpdateBatch(smtCompanies);
            return ResultUtil.success(j);
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }

    /**
     * 获取全部数据
     ** @param companyType
     *
     * @return 数据列表
     */
    @GetMapping("queryAll")
    public Result queryAll(int companyType) {
        SmtCompany smtCompanyParam = new SmtCompany();
        if (companyType != 0) smtCompanyParam.setCompanytype(companyType);
        try {
            List<SmtCompany> smtCompanies = this.smtCompanyService.queryAll(smtCompanyParam);
            smtCompanies.sort(Comparator.comparing(SmtCompany::getCompanyname));
            JSONObject resData = new JSONObject();
            resData.put("total",smtCompanies.size());
            resData.put("data",smtCompanies);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取单位出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 获取全部数据
     *
     * @return 数据列表
     */
    @GetMapping("getRegCompanys")
    public Result getRegCompanys() {
        try {
            List<SmtCompany> smtCompanies = this.smtCompanyService.queryAll(new SmtCompany());
            JSONObject resData = new JSONObject();
            resData.put("total",smtCompanies.size());
            resData.put("data",smtCompanies);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取单位出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 分类获取单位数量
     *
     * @return 数据列表
     */
    @GetMapping("getCompanyTotal")
    public Result getCompanyTotal() {
        try {
            SmtCompany smtCompanyParam = new SmtCompany();
            smtCompanyParam.setCompanytype(1);
            int customerTotal = this.smtCompanyService.getCount(smtCompanyParam,null,null);
            smtCompanyParam.setCompanytype(3);
            int checkerTotal = this.smtCompanyService.getCount(smtCompanyParam,null,null);
            JSONObject resData = new JSONObject();
            resData.put("customerTotal",customerTotal);
            resData.put("checkerTotal",checkerTotal);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("customerTotal",0);
            resData.put("checkerTotal",0);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取合作单位数量出错！"+e.getMessage(), resData);
        }
    }

}
