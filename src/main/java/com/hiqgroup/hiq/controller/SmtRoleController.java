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
import java.util.*;

/**
 * 系统角色表(SmtRole)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:20:11
 */
@RestController
@RequestMapping("smtRole")
public class SmtRoleController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig ;
    @Resource
    private SmtRoleService smtRoleService;
    @Resource
    private SmtUserService smtUserService;
    @Resource
    private SmtMenuService smtMenuService;
    @Resource
    private SmtRolerightService smtRolerightService;
    @Resource
    private SmtMenuRightitemService smtMenuRightitemService;
    @Resource
    private HqtStatisticService hqtStatisticService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtRole selectOne(String id) {
        return this.smtRoleService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) {
        SmtRole smtRoleParam = new SmtRole();
        try {
            List<SmtRole> smtRoles = this.smtRoleService.queryAllByLimit(smtRoleParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(), null, null);
            List<SmtRoleForm> smtRoleForms = new ArrayList<>();
            for (SmtRole smtRole: smtRoles) {
                SmtRoleForm smtRoleForm = new SmtRoleForm();
                CommonUtil.copyObjProperties(smtRoleForm,smtRole);
                smtRoleForm.setEdtbyusername((this.smtUserService.queryById(smtRole.getEdtbyuserid()) == null)? "未知" :
                        this.smtUserService.queryById(smtRole.getEdtbyuserid()).getUsername());
                smtRoleForms.add(smtRoleForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total",this.smtRoleService.getCount(smtRoleParam, null, null));
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",smtRoleForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取角色出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     ** @param smtRoleForm
     * @return 更新反馈
     */
    @ApiOperation(value = "更新角色信息")
    @PostMapping("update")
    public Result update(@RequestBody SmtRoleForm smtRoleForm, HttpServletRequest request) {
        SmtRole smtRole = this.smtRoleService.queryById(smtRoleForm.getId());
        try {
            CommonUtil.copyObjProperties(smtRole,smtRoleForm);
            Map currentUser = jwtConfig.getCurrentUser();
            smtRole.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            smtRole.setEdttime(date);
            this.smtRoleService.update(smtRole);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtRole",request.getRemoteAddr(),
                    "修改【"+smtRoleForm.getRolename()+"】", request.getHeader("User-Agent"));
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
     ** @param smtRoleForm
     * @return 更新反馈
     */
    @ApiOperation(value = "新增角色信息")
    @PostMapping("create")
    public Result create(@RequestBody SmtRoleForm smtRoleForm, HttpServletRequest request) {
        SmtRole smtRole = new SmtRole();
        try {
            CommonUtil.copyObjProperties(smtRole,smtRoleForm);
            smtRole.setId(UUID.randomUUID().toString());
            smtRole.setRoletype(2);
            Map currentUser = jwtConfig.getCurrentUser();
            smtRole.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date();
            smtRole.setEdttime(date);
            this.smtRoleService.insert(smtRole);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtRole",request.getRemoteAddr(),
                    "新增【"+smtRoleForm.getRolename()+"】", request.getHeader("User-Agent"));
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
     ** @param roleIds
     * @return 更新反馈
     */
    @ApiOperation(value = "删除角色信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String>  roleIds, HttpServletRequest request) {
        try {
            for (String roleId:roleIds) {
                SmtRole smtRole = this.smtRoleService.queryById(roleId);
                this.smtRoleService.deleteById(roleId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtRole",request.getRemoteAddr(),
                        "删除【"+smtRole.getRolename()+"】", request.getHeader("User-Agent"));
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
     * 获取角色菜单权限
     ** @param roleId
     * @return 数据列表
     */
    @GetMapping("getRight")
    public Result getRight(String roleId) {
        SmtMenu smtMenuParam = new SmtMenu();
        smtMenuParam.setParentid("###");
        try {
            List<SmtMenu> smtMenus = this.smtMenuService.queryAll(smtMenuParam);
            smtMenus.sort(Comparator.comparing(SmtMenu::getOrderid));
            List<SmtMenuRightForm> smtMenuRightForms = new ArrayList<>();
            for (SmtMenu smtMenu: smtMenus) {
                SmtMenuRightForm smtMenuRightForm = new SmtMenuRightForm();
                smtMenuRightForm.setParentid(smtMenu.getParentid());
                smtMenuRightForm.setId(smtMenu.getId()); smtMenuRightForm.setMenuname(smtMenu.getMenuuname());
                smtMenuRightForm.setRightvalue(getMenuRightByRole(roleId,smtMenu.getId()));
                smtMenuRightForm.setSmtMenuRightitems(getMenuRightItem(smtMenu.getId(),smtMenuRightForm.getRightvalue()));
                smtMenuRightForms.add(smtMenuRightForm);
                SmtMenu smtChildMenuParam = new SmtMenu();
                smtChildMenuParam.setParentid(smtMenu.getId());
                List<SmtMenu> smtChildMenus = this.smtMenuService.queryAll(smtChildMenuParam);
                smtChildMenus.sort(Comparator.comparing(SmtMenu::getOrderid));
                for (SmtMenu smtChildMenu: smtChildMenus) {
                    SmtMenuRightForm smtMenuChildRightForm = new SmtMenuRightForm();
                    smtMenuChildRightForm.setParentid(smtChildMenu.getParentid());
                    smtMenuChildRightForm.setId(smtChildMenu.getId()); smtMenuChildRightForm.setMenuname(smtChildMenu.getMenuuname());
                    smtMenuChildRightForm.setRightvalue(getMenuRightByRole(roleId,smtChildMenu.getId()));
                    smtMenuChildRightForm.setSmtMenuRightitems(getMenuRightItem(smtChildMenu.getId(),smtMenuChildRightForm.getRightvalue()));
                    smtMenuRightForms.add(smtMenuChildRightForm);
                }
            }
            JSONObject resData = new JSONObject();
            resData.put("is_allright",false);
            resData.put("data",smtMenuRightForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("is_allright",false);
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取角色权限出错！"+e.getMessage(), resData);
        }
    }

    private List<SmtMenuRightitemForm> getMenuRightItem(String menuId, String rightValue) {
        SmtMenuRightitem Rightitem = new SmtMenuRightitem();
        Rightitem.setMenuid(menuId);
        List<SmtMenuRightitem> smtMenuRightitems = smtMenuRightitemService.queryAll(Rightitem);
        smtMenuRightitems.sort(Comparator.comparing(SmtMenuRightitem::getItemid));
        List<SmtMenuRightitemForm> smtMenuRightitemForms = new ArrayList<>();
        for (SmtMenuRightitem smtMenuRightitem:smtMenuRightitems) {
            SmtMenuRightitemForm smtMenuRightitemForm = new SmtMenuRightitemForm();
            smtMenuRightitemForm.setMenuid(smtMenuRightitem.getMenuid());
            smtMenuRightitemForm.setItemid(smtMenuRightitem.getItemid());
            smtMenuRightitemForm.setItemname(smtMenuRightitem.getItemname());
            if (rightValue.equals("")) smtMenuRightitemForm.setIscheck(false);
            else {
                boolean isCheck = rightValue.substring(smtMenuRightitem.getItemid() - 1, smtMenuRightitem.getItemid()).equals("1");
                smtMenuRightitemForm.setIscheck(isCheck);
            }
            smtMenuRightitemForms.add(smtMenuRightitemForm);
        }
        return smtMenuRightitemForms;
    }

    private String getMenuRightByRole(String roleId, String menuId) {
        SmtRoleright smtRolerightParam = new SmtRoleright();
        smtRolerightParam.setRoleid(roleId);
        smtRolerightParam.setMenuid(menuId); //smtRolerightParam.setRightvalue("");
        List<SmtRoleright> smtRolerights = smtRolerightService.queryAll(smtRolerightParam);
        if (smtRolerights.size() == 0) return "";
        SmtRoleright smtRoleright = smtRolerights.get(0);
        return smtRoleright.getRightvalue();
    }

    /**
     * 获取角色菜单权限
     * @param smtRolerights
     * @return 数据列表
     */
    @PostMapping("setRight")
    public Result setRight(@RequestBody List<SmtRoleright> smtRolerights, HttpServletRequest request) {
        try {
            smtRolerightService.insertOrUpdateBatch(smtRolerights);
            //记录操作日志-----------
            SmtRole smtRole = this.smtRoleService.queryById(smtRolerights.get(0).getRoleid());
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtRole",request.getRemoteAddr(),
                    "设置权限【"+smtRole.getRolename()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"保存角色权限出错！"+e.getMessage());
        }
    }
}
