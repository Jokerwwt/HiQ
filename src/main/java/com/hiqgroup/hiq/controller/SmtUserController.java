package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hiqgroup.hiq.entity.*;
import com.hiqgroup.hiq.form.*;
import com.hiqgroup.hiq.service.*;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.hiqgroup.hiq.utils.StringOper.MD5Encrypt;
import static com.hiqgroup.hiq.utils.StringOper.addZeroForNum;

/**
 * 用户资料表(SmtUser)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:20:29
 */
@Api(tags = "用户管理相关接口")
@RestController
@RequestMapping("user")
public class SmtUserController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private SmtUserService smtUserService;

    @Resource
    private SmtCompanyService smtCompanyService;

    @Resource
    private SmtDeptService smtDeptService;

    @Resource
    private SmtMenuService smtMenuService;

    @Resource
    private SmtRoleService smtRoleService;

    @Resource
    private SmtMenuRightitemService smtMenuRightitemService;

    @Resource
    private SmtUserrightService smtUserrightService;

    @Resource
    private SmtUserroleService smtUserroleService;

    @Resource
    private SmtRolerightService smtRolerightService;
    @Resource
    private HqtStatisticService hqtStatisticService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("根据id查询用户的接口")
    @GetMapping("selectOne")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    public SmtUser selectOne(String id) {
        return this.smtUserService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) {
        SmtUser smtUserParam = new SmtUser();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        if ((search.get("userid") != null) && !search.get("userid").equals(""))
            smtUserParam.setUserid(search.get("userid").toString());
        if ((search.get("username") != null) && !search.get("username").equals(""))
            smtUserParam.setUsername(search.get("username").toString());
        if ((search.get("companyid") != null) && !search.get("companyid").equals(""))
            smtUserParam.setCompanyid(search.get("companyid").toString());
        try {
            List<SmtUser> smtUsers = this.smtUserService.queryAllByLimit(smtUserParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(), null, null);
            List<SmtUserForm> smtUserForms = new ArrayList<>();
            for (SmtUser smtUser: smtUsers) {
                if (smtUser.getId().equals("ADMIN")) continue;
                SmtUserForm smtUserForm = new SmtUserForm();
                CommonUtil.copyObjProperties(smtUserForm,smtUser);
                smtUserForm.setCompanyname((this.smtCompanyService.queryById(smtUser.getCompanyid()) == null)? "" :
                        this.smtCompanyService.queryById(smtUser.getCompanyid()).getCompanyname());
                smtUserForm.setDeptname((this.smtDeptService.queryById(smtUser.getDeptid()) == null)? "" :
                        this.smtDeptService.queryById(smtUser.getDeptid()).getDeptname());
                smtUserForm.setEdtbyusername((this.smtUserService.queryById(smtUser.getEdtbyuserid()) == null)? "未知" :
                        this.smtUserService.queryById(smtUser.getEdtbyuserid()).getUsername());
                smtUserForm.setCreatorname((this.smtUserService.queryById(smtUser.getCreator()) == null)? "未知" :
                        this.smtUserService.queryById(smtUser.getCreator()).getUsername());
                smtUserForms.add(smtUserForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total",this.smtUserService.getCount(smtUserParam, null, null));
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",smtUserForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取用户出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 注册用户信息
     ** @param registeForm
     * @return 更新反馈
     */
    @ApiOperation(value = "注册用户信息")
    @PostMapping("regist")
    public Result regist(@RequestBody RegisteForm registeForm) {
        SmtUser smtUserParam = new SmtUser();
        try {
            smtUserParam.setUserid(registeForm.getUserid());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if (smtUsers.size() > 0) {
                return ResultUtil.error(ResultEnum.USER_IS_EXISTS.getCode(),ResultEnum.USER_IS_EXISTS.getMsg());
            }
            smtUserParam.setUserid(null); smtUserParam.setMobile(registeForm.getMobile());
            smtUsers = this.smtUserService.queryAll(smtUserParam);
            if (smtUsers.size() > 0) {
                return ResultUtil.error(ResultEnum.MOBILE_IS_EXISTS.getCode(),ResultEnum.MOBILE_IS_EXISTS.getMsg());
            }
            SmtUser smtUser = new SmtUser();
            smtUser.setId(UUID.randomUUID().toString());
            smtUser.setUserid(registeForm.getUserid());
            smtUser.setUsername(registeForm.getUsername());
            smtUser.setMobile(registeForm.getMobile());
            smtUser.setStatus("1"); smtUser.setPassword(MD5Encrypt(registeForm.getPassword()));
            smtUser.setCreator(smtUser.getId()); smtUser.setEdtbyuserid(smtUser.getId());
            Date date =new Date(); smtUser.setEdttime(date); smtUser.setIssysuser(true);
            this.smtUserService.insert(smtUser);
            SmtUserrole smtUserrole = new SmtUserrole();
            smtUserrole.setUserid(smtUser.getId());
            if (registeForm.getCompanytype().equals("1"))  //生产厂家
                smtUserrole.setRoleid("6FDEBC85-179F-4C4B-BB36-8E57C76F0843");
            else if (registeForm.getCompanytype().equals("2"))  //运营机构
                smtUserrole.setRoleid("f6cde3da-b72e-473f-9803-12f8bdbd0ca4");
            else if (registeForm.getCompanytype().equals("3"))  //检测机构
                smtUserrole.setRoleid("83b23079-bda9-4ffe-b317-5185064546e5");
            else  //监督机构
                smtUserrole.setRoleid("4e61b833-d26d-4aaa-88da-428126efdcd6");
            smtUserrole.setEdtbyuserid(smtUser.getId()); smtUserrole.setEdttime(date);
            this.smtUserroleService.insert(smtUserrole);
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"注册用户出错！"+e.getMessage());
        }
    }

    /**
     * 通过主键更新单条数据
     ** @param smtUserForm
     * @return 更新反馈
     */
    @ApiOperation(value = "更新用户信息")
    @PostMapping("update")
    public Result update(@RequestBody SmtUserForm smtUserForm, HttpServletRequest request) {
        try {
            SmtUser smtUser = this.smtUserService.queryById(smtUserForm.getId());
            CommonUtil.copyObjProperties(smtUser,smtUserForm);
            Date date =new Date(); smtUser.setEdttime(date);
            Map currentUser = jwtConfig.getCurrentUser();
            smtUser.setEdtbyuserid(currentUser.get("id").toString());
            this.smtUserService.update(smtUser);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtUser",request.getRemoteAddr(),
                    "修改【"+smtUserForm.getUserid()+"-"+smtUserForm.getUsername()+"】", request.getHeader("User-Agent"));
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
     ** @param smtUserForm
     * @return 更新反馈
     */
    @ApiOperation(value = "新增用户信息")
    @PostMapping("create")
    public Result create(@RequestBody SmtUserForm smtUserForm, HttpServletRequest request) {
        SmtUser smtUserParam = new SmtUser();
        try {
            smtUserParam.setUserid(smtUserForm.getUserid());
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            if (smtUsers.size() > 0) {
                return ResultUtil.error(ResultEnum.USER_IS_EXISTS.getCode(),ResultEnum.USER_IS_EXISTS.getMsg());
            }
            smtUserParam.setUserid(null); smtUserParam.setMobile(smtUserForm.getMobile());
            smtUsers = this.smtUserService.queryAll(smtUserParam);
            if (smtUsers.size() > 0) {
                return ResultUtil.error(ResultEnum.MOBILE_IS_EXISTS.getCode(),ResultEnum.MOBILE_IS_EXISTS.getMsg());
            }
            SmtUser smtUser = new SmtUser();
            CommonUtil.copyObjProperties(smtUser,smtUserForm);
            smtUser.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            smtUser.setCreator(currentUser.get("id").toString());
            smtUser.setEdtbyuserid(currentUser.get("id").toString());
            Date date =new Date(); smtUser.setEdttime(date);
            this.smtUserService.insert(smtUser);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtUser",request.getRemoteAddr(),
                    "新增【"+smtUserForm.getUserid()+"-"+smtUserForm.getUsername()+"】", request.getHeader("User-Agent"));
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
     ** @param userIds
     * @return 更新反馈
     */
    @ApiOperation(value = "删除用户信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String>  userIds, HttpServletRequest request) {
        try {
            for (String userId:userIds) {
                SmtUser smtUser = this.smtUserService.queryById(userId);
                this.smtUserService.deleteById(userId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtUser",request.getRemoteAddr(),
                        "删除【"+smtUser.getUserid()+"-"+smtUser.getUsername()+"】", request.getHeader("User-Agent"));
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
     * 用户登录接口
     *
     * @param loginForm
     * @return
     */
    @ApiOperation(value = "用户登录接口", notes = "用户登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request){
        SmtUser smtUserParam = new SmtUser();
        try {
            smtUserParam.setUserid(loginForm.getUsername());
            List<SmtUser> smtUserList = smtUserService.queryAll(smtUserParam);
            if (smtUserList.size() < 1){
                smtUserParam.setUserid(null);
                smtUserParam.setMobile(loginForm.getUsername());
                smtUserList = smtUserService.queryAll(smtUserParam);
                if (smtUserList.size() < 1) {
                    JSONObject resData = new JSONObject();
                    resData.put("token", "");
                    resData.put("usercompanytype", "");
                    resData.put("data", loginForm.getUsername());
                    return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMsg(), resData);
                }
            }
            SmtUser smtUser = smtUserList.get(0);
            if (!smtUser.getIssysuser()){
                JSONObject resData = new JSONObject();
                resData.put("token","");
                resData.put("usercompanytype","");
                resData.put("data",smtUser);
                return ResultUtil.error(ResultEnum.USER_NOT_SYSTEM.getCode(),ResultEnum.USER_NOT_SYSTEM.getMsg(),resData);
            }
            if (smtUser.getStatus().equals("1")){
                JSONObject resData = new JSONObject();
                resData.put("token","");
                resData.put("usercompanytype","");
                resData.put("data",smtUser);
                return ResultUtil.error(ResultEnum.USER_NOT_CONFIRM.getCode(),ResultEnum.USER_NOT_CONFIRM.getMsg(),resData);
            }
            if (smtUser.getStatus().equals("3")){
                JSONObject resData = new JSONObject();
                resData.put("token","");
                resData.put("usercompanytype","");
                resData.put("data",smtUser);
                return ResultUtil.error(ResultEnum.USER_IS_INVALID.getCode(),ResultEnum.USER_IS_INVALID.getMsg(),resData);
            }
            if (!MD5Encrypt(loginForm.getPassword()).equals(smtUser.getPassword())){
                JSONObject resData = new JSONObject();
                resData.put("token","");
                resData.put("usercompanytype","");
                resData.put("data",smtUser);
                return ResultUtil.error(ResultEnum.PASSWORD_IS_ERROR.getCode(),ResultEnum.PASSWORD_IS_ERROR.getMsg(),resData);
            }
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(smtUser.getId(),null,request.getRemoteAddr(),
                    "登录系统", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            String usercompanytype = ((smtUser.getCompanyid() == null) || smtUser.getCompanyid().equals(""))? "":this.smtCompanyService.queryById(smtUser.getCompanyid()).getCompanytype().toString();
            Map claims = new HashMap<>();
            claims.put("id", smtUser.getId()); claims.put("userid", smtUser.getUserid());
            claims.put("username", smtUser.getUsername());
            claims.put("usercompany", smtUser.getCompanyid());
            claims.put("usercompanytype", usercompanytype);
            claims.put("userdept", smtUser.getDeptid());
            String token = jwtConfig.createToken(claims);
            JSONObject resData = new JSONObject();
            resData.put("token",token);
            resData.put("usercompanytype",usercompanytype);
            resData.put("data",smtUser);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("token","");
            resData.put("usercompanytype","");
            resData.put("data",loginForm.getUsername());
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage(),resData);
        }
    }

    /**
     * 用户解锁接口
     *
     * @param loginForm
     * @return
     */
    @ApiOperation(value = "用户解锁接口", notes = "用户解锁")
    @PostMapping("unlock")
    public Result unlock(@RequestBody LoginForm loginForm){
        try {
            SmtUser smtUser = smtUserService.queryById(loginForm.getUsername());
            if (smtUser == null){
                return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg());
            }
            if (!MD5Encrypt(loginForm.getPassword()).equals(smtUser.getPassword())){
                return ResultUtil.error(ResultEnum.PASSWORD_IS_ERROR.getCode(),ResultEnum.PASSWORD_IS_ERROR.getMsg());
            }
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }


    /**
     * 用户信息接口
     *
     * @return
     */
    @ApiOperation(value = "用户信息接口", notes = "用户信息")
    @GetMapping("info")
    public Result info(){
        try {
            Map currentUser = jwtConfig.getCurrentUser();
            SmtUser smtUser = smtUserService.queryById(currentUser.get("id").toString());
            SmtUserrole smtUserroleParam = new SmtUserrole();
            smtUserroleParam.setUserid(smtUser.getId());
            List<SmtUserrole> smtUserroles = smtUserroleService.queryAll(smtUserroleParam);
            String userRoles = "";
            for (SmtUserrole smtUserrole: smtUserroles) {
                userRoles += this.smtRoleService.queryById(smtUserrole.getRoleid()).getRolename()+" | ";
            }
            if (!userRoles.equals("")) userRoles = userRoles.substring(0, userRoles.length() - 3);
            String smtUserStr = new Gson().toJson(smtUser);
            String userCompanyName = (smtUser.getCompanyid() != null && !smtUser.getCompanyid().equals(""))? this.smtCompanyService.queryById(smtUser.getCompanyid()).getCompanyname():"";
            String userDeptName = (smtUser.getDeptid() != null && !smtUser.getDeptid().equals(""))? this.smtDeptService.queryById(smtUser.getDeptid()).getDeptname():"";
            smtUserStr = smtUserStr.substring(0,smtUserStr.length()-1)+",\"roles\":\""+userRoles+"\",\"companyname\":\""+userCompanyName+"\",\"deptname\":\""+userDeptName+"\"}";
            JSONObject resData = new JSONObject();
            resData.put("data",smtUserStr);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取用户信息出错！"+e.getMessage());
        }
    }

    /**
     * 用户菜单接口
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户菜单接口", notes = "用户菜单")
    @GetMapping("getUserMenu")
    public Result getUserMenu(String id){
        List menuList = new ArrayList();
        try {
            SmtMenu smtMenuParam = new SmtMenu();
            smtMenuParam.setParentid("###");
            List<SmtMenu> smtMenus = smtMenuService.queryAll(smtMenuParam);
            smtMenus.sort(Comparator.comparing(SmtMenu::getOrderid));
            for (SmtMenu smtMenu:smtMenus) {  //遍历顶级菜单
                SmtMenu smtChildMenuParam = new SmtMenu();
                smtChildMenuParam.setParentid(smtMenu.getId());
                List<SmtMenu> smtChildMenus = smtMenuService.queryAll(smtChildMenuParam);
                smtChildMenus.sort(Comparator.comparing(SmtMenu::getOrderid));
                if (smtChildMenus.size() > 0){
                    List childList = new ArrayList();
                    for (SmtMenu smtChildMenu:smtChildMenus) {
                        //获取菜单权限值------------
                        String sRightValue = getUserMenuRightValue(id,smtChildMenu.getId());
                        //---------------------------
                        if (sRightValue.substring(0,1).equals("1")) {  //有使用权限
                            Map menuChildMap = new HashMap();
                            menuChildMap.put("href", smtChildMenu.getHref());
                            menuChildMap.put("title", smtChildMenu.getMenuuname());
                            menuChildMap.put("menuid", smtChildMenu.getId());
                            menuChildMap.put("icon", smtChildMenu.getIconname());
                            menuChildMap.put("target", "_self");
                            menuChildMap.put("rightvalue", sRightValue);
                            childList.add(menuChildMap);
                        }
                    }
                    if (childList.size() > 0) {
                        Map menuMap = new HashMap();
                        menuMap.put("href","");
                        menuMap.put("title",smtMenu.getMenuuname());
                        menuMap.put("icon",smtMenu.getIconname());
                        menuMap.put("target","_self");

                        menuMap.put("meta","{ title: '"+smtMenu.getMenuuname()+"', icon: 'el-icon-s-help', affix: true }");
                        menuMap.put("child", childList);
                        menuList.add(menuMap);
                    }
                }
            }
            return ResultUtil.success(menuList);
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取用户菜单出错！"+e.getMessage());
        }
    }

    private String getUserMenuRightValue(String userId, String menuId) {
        if (userId.toUpperCase().equals("ADMIN")){
            SmtMenu smtMenu = this.smtMenuService.queryById(menuId);
            if (smtMenu.getParentid().equals("SMT"))
                return "1111111111111111";
            else
                return "0000000000000000";
        }
        int rightLen = 0;
        //获取用户权限值-----------------
        SmtUserright smtUserrightParam = new SmtUserright();
        smtUserrightParam.setMenuid(menuId); smtUserrightParam.setUserid(userId);
        smtUserrightParam.setRightvalue("");
        List<SmtUserright> smtUserrights = smtUserrightService.queryAll(smtUserrightParam);
        int iRightValue = 0;
        if (smtUserrights.size() > 0) {
            SmtUserright smtUserright = smtUserrights.get(0);
            iRightValue = Integer.parseInt(smtUserright.getRightvalue(), 2);
            rightLen = smtUserright.getRightvalue().length();
        }
        //----------------------------
        //遍历用户角色权限值与用户私有权限值进行或运算，获取用户最终权限值-------------
        SmtUserrole smtUserroleParam = new SmtUserrole();
        smtUserroleParam.setUserid(userId);
        List<SmtUserrole> userRoles = smtUserroleService.queryAll(smtUserroleParam);
        for (SmtUserrole userRole:userRoles) {
            SmtRoleright smtRolerightParam = new SmtRoleright();
            smtRolerightParam.setRoleid(userRole.getRoleid());
            smtRolerightParam.setMenuid(menuId); smtRolerightParam.setRightvalue("");
            List<SmtRoleright> smtRolerights = smtRolerightService.queryAll(smtRolerightParam);
            if (smtRolerights.size() == 0) continue;
            SmtRoleright roleRight = smtRolerights.get(0);
            int iRoleRight =Integer.parseInt(roleRight.getRightvalue(), 2);
            if (rightLen < roleRight.getRightvalue().length()) rightLen = roleRight.getRightvalue().length();
            iRightValue = iRightValue | iRoleRight;
        }
        //------------------------------------------------------------------
        return addZeroForNum(Integer.toBinaryString(iRightValue),rightLen);  //十进制转二进制并缺位补0
    }

    /**
     * 获取用户菜单权限
     ** @param userId
     * @return 数据列表
     */
    @GetMapping("getRight")
    public Result getRight(String userId, boolean isAllright) {
        SmtMenu smtMenuParam = new SmtMenu();
        smtMenuParam.setParentid("###");
        try {
            List<SmtMenu> smtMenus = this.smtMenuService.queryAll(smtMenuParam);
            smtMenus.sort(Comparator.comparing(SmtMenu::getOrderid));
            List<SmtUserrole> smtUserroles = null;
            if (isAllright){
                SmtUserrole smtUserroleParam = new SmtUserrole();
                smtUserroleParam.setUserid(userId);
                smtUserroles = smtUserroleService.queryAll(smtUserroleParam);
            }
            List<SmtMenuRightForm> smtMenuRightForms = new ArrayList<>();
            for (SmtMenu smtMenu: smtMenus) {
                SmtMenuRightForm smtMenuRightForm = new SmtMenuRightForm();
                smtMenuRightForm.setParentid(smtMenu.getParentid());
                smtMenuRightForm.setId(smtMenu.getId()); smtMenuRightForm.setMenuname(smtMenu.getMenuuname());
                smtMenuRightForm.setRightvalue(getMenuRightByUser(userId,smtMenu.getId()));
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
                    String rightValue = getMenuRightByUser(userId,smtChildMenu.getId());
                    if (isAllright && (smtUserroles != null) && (smtUserroles.size()>0)){
                        int rightLen = (rightValue == "")? 0 : rightValue.length();
                        int iRightValue = (rightValue == "") ? 0: Integer.parseInt(rightValue, 2);
                        for (SmtUserrole smtUserrole: smtUserroles){
                            String roleRightValue = getMenuRightByRole(smtUserrole.getRoleid(),smtChildMenu.getId());
                            if (roleRightValue.trim().equals("")) continue;
                            int iRoleRight =Integer.parseInt(roleRightValue, 2);
                            if (rightLen < roleRightValue.length()) rightLen = roleRightValue.length();
                            iRightValue = iRightValue | iRoleRight;
                        }
                        rightValue = Integer.toBinaryString(iRightValue);
                        rightValue = addZeroForNum(Integer.toBinaryString(iRightValue),rightLen);
                    }
                    smtMenuChildRightForm.setRightvalue(rightValue);
                    smtMenuChildRightForm.setSmtMenuRightitems(getMenuRightItem(smtChildMenu.getId(),smtMenuChildRightForm.getRightvalue()));
                    smtMenuRightForms.add(smtMenuChildRightForm);
                }
            }
            JSONObject resData = new JSONObject();
            resData.put("is_allright",isAllright);
            resData.put("data",smtMenuRightForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("is_allright",isAllright);
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取用户私有权限出错！"+e.getMessage(), resData);
        }
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
     * 获取用户角色
     ** @param userId
     * @return 数据列表
     */
    @GetMapping("getRoles")
    public Result getRoles(String userId) {
        try {
            List<SmtRole> smtRoles = this.smtRoleService.queryAll(new SmtRole());
            smtRoles.sort(Comparator.comparing(SmtRole::getRolename));
            List<SmtUserRoleForm> smtUserRoleForms = new ArrayList<>();
            for (SmtRole smtRole : smtRoles) {
                SmtUserRoleForm smtUserRoleForm = new SmtUserRoleForm();
                smtUserRoleForm.setId(smtRole.getId());
                smtUserRoleForm.setRolename(smtRole.getRolename());
                SmtUserrole smtUserroleParam = new SmtUserrole();
                smtUserroleParam.setUserid(userId); smtUserroleParam.setRoleid(smtRole.getId());
                List<SmtUserrole> userRoles = smtUserroleService.queryAll(smtUserroleParam);
                smtUserRoleForm.setIscheck((userRoles.size() > 0));
                smtUserRoleForms.add(smtUserRoleForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("data",smtUserRoleForms);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage(),resData);
        }
    }

    /**
     * 设置用户角色
     * @param smtUserroles
     * @return 数据列表
     */
    @PostMapping("setRoles")
    public Result setRoles(@RequestBody List<SmtUserrole> smtUserroles, HttpServletRequest request) {
        try {
            Map currentUser = jwtConfig.getCurrentUser();
            Date date =new Date();
            for (SmtUserrole smtUserrole : smtUserroles) {
                smtUserrole.setEdtbyuserid(currentUser.get("id").toString());
                smtUserrole.setEdttime(date);
            }
            smtUserroleService.deleteById(smtUserroles.get(0).getUserid());
            smtUserroleService.insertBatch(smtUserroles);
            //记录操作日志-----------
            SmtUser smtUser = this.smtUserService.queryById(smtUserroles.get(0).getUserid());
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtUser",request.getRemoteAddr(),
                    "设置角色【"+smtUser.getUsername()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"保存用户角色出错！"+e.getMessage());
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
            if (rightValue.equals("") || rightValue.equals("0")) smtMenuRightitemForm.setIscheck(false);
            else {
                boolean isCheck = rightValue.substring(smtMenuRightitem.getItemid() - 1, smtMenuRightitem.getItemid()).equals("1");
                smtMenuRightitemForm.setIscheck(isCheck);
            }
            smtMenuRightitemForms.add(smtMenuRightitemForm);
        }
        return smtMenuRightitemForms;
    }

    private String getMenuRightByUser(String userId, String menuId) {
        SmtUserright smtUserrightParam = new SmtUserright();
        smtUserrightParam.setUserid(userId);
        smtUserrightParam.setMenuid(menuId); //smtUserrightParam.setRightvalue("");
        List<SmtUserright> smtUserrights = smtUserrightService.queryAll(smtUserrightParam);
        if (smtUserrights.size() == 0) return "";
        SmtUserright smtUserright = smtUserrights.get(0);
        return smtUserright.getRightvalue();
    }

    /**
     * 获取用户菜单权限
     * @param smtUserrights
     * @return 数据列表
     */
    @PostMapping("setRight")
    public Result setRight(@RequestBody List<SmtUserright> smtUserrights, HttpServletRequest request) {
        try {
            smtUserrightService.insertOrUpdateBatch(smtUserrights);
            //记录操作日志-----------
            SmtUser smtUser = this.smtUserService.queryById(smtUserrights.get(0).getUserid());
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtUser",request.getRemoteAddr(),
                    "设置权限【"+smtUser.getUsername()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"保存用户私有权限出错！"+e.getMessage());
        }
    }

    /**
     * 用户密码重置接口
     *
     * @param updatePasswordForm
     * @return
     */
    @ApiOperation(value = "用户密码重置接口", notes = "用户密码重置")
    @PostMapping("resetPwd")
    public Result resetPwd(@RequestBody UpdatePasswordForm updatePasswordForm, HttpServletRequest request){
        try {
            SmtUser smtUser = smtUserService.queryById(updatePasswordForm.getUserid());
            smtUser.setPassword(MD5Encrypt(updatePasswordForm.getNewpassword()));
            smtUserService.update(smtUser);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtUser",request.getRemoteAddr(),
                    "重置密码【"+smtUser.getUserid()+"-"+smtUser.getUsername()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"重置密码出错！"+e.getMessage());
        }
    }

    /**
     * 用户密码修改接口
     *
     * @param updatePasswordForm
     * @return
     */
    @ApiOperation(value = "用户密码修改接口", notes = "用户密码修改")
    @PostMapping("updatePwd")
    public Result updatePwd(@RequestBody UpdatePasswordForm updatePasswordForm){
        try {
            Map currentUser = jwtConfig.getCurrentUser();
            SmtUser smtUser = smtUserService.queryById(currentUser.get("id").toString());
            if (!MD5Encrypt(updatePasswordForm.getOldpassword()).equals(smtUser.getPassword())){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"旧密码输入错误！");
            }
            smtUser.setPassword(MD5Encrypt(updatePasswordForm.getNewpassword()));
            smtUserService.update(smtUser);
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"修改密码出错！"+e.getMessage());
        }
    }

    /**
     * 获取注册用户
     * @return 数据列表
     */
    @GetMapping("getRegistUsers")
    public Result getRegistUsers() {
        SmtUser smtUserParam = new SmtUser();
        try {
            smtUserParam.setStatus("1");
            List<SmtUser> smtUsers = this.smtUserService.queryAll(smtUserParam);
            ListSortUtil<SmtUser> sortList = new ListSortUtil<>();
            sortList.sort(smtUsers, "edttime", "desc");
            JSONObject resData = new JSONObject();
            resData.put("data",smtUsers);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取注册用户出错！"+e.getMessage(),resData);
        }
    }

    /**
     * 获取用户
     ** @param userId
     * @return 数据列表
     */
    @GetMapping("getUser")
    public Result getUser(String userId) {
        try {
            SmtUser smtUser = this.smtUserService.queryById(userId);
            SmtUserForm smtUserForm = new SmtUserForm();
            CommonUtil.copyObjProperties(smtUserForm,smtUser);
            if (smtUser.getDeptid() != null && !smtUser.getDeptid().equals(""))
                smtUserForm.setDeptname(this.smtDeptService.queryById(smtUser.getDeptid()).getDeptname());
            JSONObject resData = new JSONObject();
            resData.put("data",smtUserForm);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取用户出错！"+e.getMessage(),resData);
        }
    }

    /**
     * 审核注册用户接口
     *
     * @param id
     * @param isconfirm
     * @return
     */
    @ApiOperation(value = "审核注册用户接口", notes = "审核注册用户")
    @PostMapping("confirmRegUser")
    public Result confirmRegUser(String id, boolean isconfirm){
        try {
            SmtUser smtUser = smtUserService.queryById(id);
            smtUser.setStatus(((isconfirm == true)? "2" : "3"));
            smtUserService.update(smtUser);
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"审核注册用户出错！"+e.getMessage());
        }
    }
}
