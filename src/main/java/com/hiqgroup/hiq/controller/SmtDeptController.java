package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.SmtDept;
import com.hiqgroup.hiq.form.PagedQueryForm;
import com.hiqgroup.hiq.form.SmtDeptForm;
import com.hiqgroup.hiq.form.SmtDeptTreeForm;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtCompanyService;
import com.hiqgroup.hiq.service.SmtDeptService;
import com.hiqgroup.hiq.service.SmtUserService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门资料表(SmtDept)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:19:43
 */
@RestController
@RequestMapping("smtDept")
public class SmtDeptController {
    @Resource
    private JwtConfig jwtConfig;
    /**
     * 服务对象
     */
    @Resource
    private SmtDeptService smtDeptService;
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
    public SmtDept selectOne(String id) {
        return this.smtDeptService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) {
        SmtDept smtDeptParam = new SmtDept();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        smtDeptParam.setCompanyid(search.get("companyid").toString());
        try {
            List<SmtDept> smtDepts = this.smtDeptService.queryAllByLimit(smtDeptParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(), param.getSort(), param.getOrdertype(), null, null);
            List<SmtDeptForm> smtDeptForms = new ArrayList<>();
            for (SmtDept smtDept: smtDepts) {
                SmtDeptForm smtDeptForm = new SmtDeptForm();
                CommonUtil.copyObjProperties(smtDeptForm,smtDept);
                smtDeptForm.setEdtbyusername(this.smtUserService.queryById(smtDept.getEdtbyuserid()).getUsername());
                smtDeptForms.add(smtDeptForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total",this.smtDeptService.getCount(new SmtDept(), null, null));
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",smtDeptForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取部门出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 获取部门数据
     ** @param companyId
     * @return 数据列表
     */
    @GetMapping("getDepts")
    public Result getDepts(String companyId) {
        SmtDept smtDeptParam = new SmtDept();
        smtDeptParam.setCompanyid(companyId);
        try {
            List<SmtDept> smtDepts = this.smtDeptService.queryAll(smtDeptParam);
            List<SmtDeptForm> smtDeptForms = new ArrayList<>();
            for (SmtDept smtDept: smtDepts) {
                SmtDeptForm smtDeptForm = new SmtDeptForm();
                CommonUtil.copyObjProperties(smtDeptForm,smtDept);
                smtDeptForm.setEdtbyusername((this.smtUserService.queryById(smtDept.getEdtbyuserid()) == null)? "未知" :
                        this.smtUserService.queryById(smtDept.getEdtbyuserid()).getUsername());
                smtDeptForms.add(smtDeptForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("data",smtDeptForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取部门出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 获取部门数据
     ** @param companyId
     * @return 数据列表
     */
    @GetMapping("getDeptsTree")
    public Result getDeptsTree(String companyId) {
        SmtDept smtDeptParam = new SmtDept();
        smtDeptParam.setCompanyid(companyId);
        try {
            List<SmtDept> smtDepts = this.smtDeptService.queryAll(smtDeptParam);
            List<SmtDeptTreeForm> smtDeptTreeForms = getTreeChildren(smtDepts, "###");
            JSONObject resData = new JSONObject();
            resData.put("data",smtDeptTreeForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取部门出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 获取部门数据
     ** @param companyId
     * @return 数据列表
     */
    @GetMapping("getRegDeptsTree")
    public Result getRegDeptsTree(String companyId) {
        if (companyId.equals("")){
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.success(resData);
        }
        SmtDept smtDeptParam = new SmtDept();
        smtDeptParam.setCompanyid(companyId);
        try {
            List<SmtDept> smtDepts = this.smtDeptService.queryAll(smtDeptParam);
            List<SmtDeptTreeForm> smtDeptTreeForms = getTreeChildren(smtDepts, "###");
            JSONObject resData = new JSONObject();
            resData.put("data",smtDeptTreeForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取部门出错！"+e.getMessage(), resData);
        }
    }

    private List<SmtDeptTreeForm> getTreeChildren(List<SmtDept> smtDepts, String parentId){
        List<SmtDeptTreeForm> smtDeptTreeForms = new ArrayList<>();
        List<SmtDept> smtChildDepts = smtDepts.stream()
                .filter(item -> item.getParentid().equals(parentId))
                .collect(Collectors.toList());
        for (SmtDept smtChildDept: smtChildDepts) {
            SmtDeptTreeForm smtDeptTreeForm = new SmtDeptTreeForm();
            smtDeptTreeForm.setId(smtChildDept.getId());
            smtDeptTreeForm.setName(smtChildDept.getDeptname());
            smtDeptTreeForm.setSpread(true);
            smtDeptTreeForm.setChildren(getTreeChildren(smtDepts,smtChildDept.getId()));
            smtDeptTreeForms.add(smtDeptTreeForm);
        }
        return smtDeptTreeForms;
    }

    /**
     * 通过主键更新单条数据
     ** @param smtDeptForm
     * @return 更新反馈
     */
    @ApiOperation(value = "更新部门信息")
    @PostMapping("update")
    public Result update(@RequestBody SmtDeptForm smtDeptForm, HttpServletRequest request) {
        try {
            SmtDept smtDept = this.smtDeptService.queryById(smtDeptForm.getId());
            CommonUtil.copyObjProperties(smtDept,smtDeptForm);
            Map currentUser = jwtConfig.getCurrentUser();
            smtDept.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            smtDept.setEdttime(date);
            this.smtDeptService.update(smtDept);
            //记录操作日志-----------
            String companyname = this.smtCompanyService.queryById(smtDept.getCompanyid()).getCompanyname();
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtDept",request.getRemoteAddr(),
                    "修改【"+companyname+"-"+ smtDeptForm.getDeptname()+"】", request.getHeader("User-Agent"));
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
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"更新部门出错！"+e.getMessage(),resData);
        }
    }

    /**
     * 通过主键新增单条数据
     ** @param smtDeptForm
     * @return 更新反馈
     */
    @ApiOperation(value = "新增部门信息")
    @PostMapping("create")
    public Result create(@RequestBody SmtDeptForm smtDeptForm, HttpServletRequest request) {
        try {
            SmtDept smtDept = new SmtDept();
            CommonUtil.copyObjProperties(smtDept,smtDeptForm);
            smtDept.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            smtDept.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            smtDept.setEdttime(date);
            this.smtDeptService.insert(smtDept);
            //记录操作日志-----------
            String companyname = this.smtCompanyService.queryById(smtDeptForm.getCompanyid()).getCompanyname();
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtDept",request.getRemoteAddr(),
                    "新增【"+companyname+"-"+ smtDeptForm.getDeptname()+"】", request.getHeader("User-Agent"));
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
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"新增部门出错！"+e.getMessage(),resData);
        }
    }

    /**
     * 批量删除数据
     ** @param deptIds
     * @return 更新反馈
     */
    @ApiOperation(value = "删除部门信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String>  deptIds, HttpServletRequest request) {
        try {
            for (String deptId:deptIds) {
                delete(deptId);
                SmtDept smtDept = this.smtDeptService.queryById(deptId);
                String companyname = this.smtCompanyService.queryById(smtDept.getCompanyid()).getCompanyname();
                this.smtDeptService.deleteById(deptId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtDept",request.getRemoteAddr(),
                        "删除【"+companyname+"-"+smtDept.getDeptname()+"】", request.getHeader("User-Agent"));
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

    //递归删除
    private void delete(String id){
        SmtDept smtDeptParam = new SmtDept();
        smtDeptParam.setParentid(id);
        List<SmtDept> smtDeptChilds = this.smtDeptService.queryAll(smtDeptParam);
        if(smtDeptChilds!=null && !smtDeptChilds.isEmpty()) {
            for (SmtDept smtDeptChild : smtDeptChilds) {
                String deptId = smtDeptChild.getId();
                delete(deptId);
                this.smtDeptService.deleteById(deptId);
            }
        }else {
            this.smtDeptService.deleteById(id);;
        }
    }

}
