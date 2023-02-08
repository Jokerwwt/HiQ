package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HpgDaily;
import com.hiqgroup.hiq.form.*;
import com.hiqgroup.hiq.service.HpgDailyService;
import com.hiqgroup.hiq.service.HpgDailytypeService;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtUserService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.hiqgroup.hiq.utils.CommonUtil.getImgSrc;

/**
 * 日常动态(HpgDaily)表控制层
 *
 * @author liugaqiong
 * @since 2021-12-20 14:46:42
 */
@RestController
@RequestMapping("hpgDaily")
public class HpgDailyController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private HpgDailyService hpgDailyService;
    @Resource
    private SmtUserService smtUserService;
    @Resource
    private HpgDailytypeService hpgDailytypeService;
    @Resource
    private HqtStatisticService hqtStatisticService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HpgDaily selectOne(String id) {
        return this.hpgDailyService.queryById(id);
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param) {
        HpgDaily hpgDailyParam = new HpgDaily();
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hpgDailyParam.setTitle(search.get("title").toString());
        hpgDailyParam.setDailytype(search.get("dailytype").toString());
        hpgDailyParam.setStatus(search.get("status").toString());
        try {
            List<HpgDaily> hpgDailies = this.hpgDailyService.queryAllByLimit(hpgDailyParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(),null,null);
            List<HpgDailyForm> hpgDailyForms = new ArrayList<>();
            for (HpgDaily hpgDaily : hpgDailies) {
                HpgDailyForm hpgDailyForm = new HpgDailyForm();
                CommonUtil.copyObjProperties(hpgDailyForm, hpgDaily);
                hpgDailyForm.setEdtbyusername((this.smtUserService.queryById(hpgDaily.getEdtbyuserid()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getEdtbyuserid()).getUsername());
                hpgDailyForm.setWritername((this.smtUserService.queryById(hpgDaily.getWriter()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getWriter()).getUsername());
                hpgDailyForm.setPublishername((this.smtUserService.queryById(hpgDaily.getPublisher()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getPublisher()).getUsername());
                hpgDailyForm.setDailytypename((this.hpgDailytypeService.queryById(hpgDaily.getDailytype()) == null)? "" :
                        this.hpgDailytypeService.queryById(hpgDaily.getDailytype()).getTypename());
                hpgDailyForms.add(hpgDailyForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total", this.hpgDailyService.getCount(hpgDailyParam,null,null));
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", hpgDailyForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("newtoken", jwtConfig.getNewToken());
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取日常动态出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键更新单条数据
     * * @param hpgDailyForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "更新日常动态信息")
    @PostMapping("update")
    public Result update(@RequestBody HpgDailyForm hpgDailyForm, HttpServletRequest request) {
        try {
            HpgDaily hpgDaily = this.hpgDailyService.queryById(hpgDailyForm.getId());
            CommonUtil.copyObjProperties(hpgDaily, hpgDailyForm);
            Date date = new Date();
            hpgDaily.setEdttime(date);
            Map currentUser = jwtConfig.getCurrentUser();
            hpgDaily.setEdtbyuserid(currentUser.get("id").toString());
            JSONObject resData = new JSONObject();
            if (hpgDailyForm.getStatus().equals("2")){
                hpgDaily.setPublishdate(date);
                resData.put("publishername", (this.smtUserService.queryById(hpgDailyForm.getPublisher()) == null)? "" : this.smtUserService.queryById(hpgDailyForm.getPublisher()).getUsername());
                resData.put("publishdate", hpgDaily.getPublishdate());
            } else {
                hpgDaily.setPublishdate(null);
                resData.put("publishername", "");
                resData.put("publishdate", null);
            }
            this.hpgDailyService.update(hpgDaily);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgDaily",request.getRemoteAddr(),
                    "修改【"+hpgDailyForm.getTitle()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            resData.put("writer", hpgDailyForm.getWriter());
            resData.put("writername", hpgDailyForm.getWritername());
            resData.put("status", hpgDaily.getStatus());
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("writer", hpgDailyForm.getWriter());
            resData.put("writername", hpgDailyForm.getWritername());
            resData.put("publishername", hpgDailyForm.getPublishername());
            resData.put("publishdate", null);
            resData.put("status", "1");
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 通过主键新增单条数据
     * * @param hpgDailyForm
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "新增日常动态信息")
    @PostMapping("create")
    public Result create(@RequestBody HpgDailyForm hpgDailyForm, HttpServletRequest request) {
        try {
            HpgDaily hpgDaily = new HpgDaily();
            CommonUtil.copyObjProperties(hpgDaily, hpgDailyForm);
            hpgDaily.setId(UUID.randomUUID().toString());
            Map currentUser = jwtConfig.getCurrentUser();
            hpgDaily.setEdtbyuserid(currentUser.get("id").toString());
            hpgDaily.setWriter(currentUser.get("id").toString());
            hpgDaily.setViewtotal(0);
            Date date = new Date();
            hpgDaily.setEdttime(date);
            JSONObject resData = new JSONObject();
            if (hpgDailyForm.getStatus().equals("2")){
                hpgDaily.setPublishdate(date);
                resData.put("publishername", this.smtUserService.queryById(hpgDailyForm.getPublisher()).getUsername());
                resData.put("publishdate", hpgDaily.getPublishdate());
            } else {
                hpgDaily.setPublishdate(null);
                resData.put("publishername", "");
                resData.put("publishdate", null);
            }
            this.hpgDailyService.insert(hpgDaily);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgDaily",request.getRemoteAddr(),
                    "新增【"+hpgDailyForm.getTitle()+"】", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            resData.put("writer", currentUser.get("id").toString());
            resData.put("writername", currentUser.get("username").toString());
            resData.put("id",hpgDaily.getId());
            resData.put("status", hpgDaily.getStatus());
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("writer", hpgDailyForm.getWriter());
            resData.put("writername", hpgDailyForm.getWritername());
            resData.put("publishername", hpgDailyForm.getPublishername());
            resData.put("publishdate", null);
            resData.put("id",null);
            resData.put("status", "1");
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg() + e.getMessage(), resData);
        }
    }

    /**
     * 删除日常动态信息
     * * @param dailyIds
     *
     * @return 更新反馈
     */
    @ApiOperation(value = "删除日常动态信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> dailyIds, HttpServletRequest request) {
        try {
            for (String dailyId : dailyIds) {
                HpgDaily hpgDaily = this.hpgDailyService.queryById(dailyId);
                this.hpgDailyService.deleteById(dailyId);
                //记录操作日志-----------
                String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"HpgDaily",request.getRemoteAddr(),
                        "删除【"+hpgDaily.getTitle()+"】", request.getHeader("User-Agent"));
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
     * 获取数据条数
     *
     * @return 数据列表
     */
    @GetMapping("getDailysCount")
    public Result getDailysCount(PagedQueryForm<T> param) {
        HpgDaily hpgDailyParam = new HpgDaily();
        hpgDailyParam.setStatus("2");
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hpgDailyParam.setDailytype(search.get("dailytype").toString());
        try {
            JSONObject resData = new JSONObject();
            resData.put("total", this.hpgDailyService.getCount(hpgDailyParam,null,null));
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取日常动态条数出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 获取数据条数
     *
     * @return 数据列表
     */
    @GetMapping("getKeyWordDailysCount")
    public Result getKeyWordDailysCount(PagedQueryForm<T> param) {
        HpgDaily hpgDailyParam = new HpgDaily();
        hpgDailyParam.setStatus("2");
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hpgDailyParam.setContent(search.get("keyword").toString());
        try {
            JSONObject resData = new JSONObject();
            resData.put("total", this.hpgDailyService.getCount(hpgDailyParam,null,null));
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "关键字获取日常动态条数出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("getDailyList")
    public Result getDailyList(PagedQueryForm<T> param) {
        HpgDaily hpgDailyParam = new HpgDaily();
        hpgDailyParam.setStatus("2");  //已发布
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hpgDailyParam.setDailytype(search.get("dailytype").toString());
        if ((search.get("isnotify") != null) && !search.get("isnotify").equals(""))
            hpgDailyParam.setIsnotify(search.get("isnotify").toString().equals("1"));
        try {
            List<HpgDaily> hpgDailies = this.hpgDailyService.queryAllByLimit(hpgDailyParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(),null,null);
            List<HpgDailyForm> hpgDailyForms = new ArrayList<>();
            for (HpgDaily hpgDaily : hpgDailies) {
                HpgDailyForm hpgDailyForm = new HpgDailyForm();
                CommonUtil.copyObjProperties(hpgDailyForm, hpgDaily);
                hpgDailyForm.setContent(CommonUtil.formatHTMLTag(hpgDailyForm.getContent()));
                hpgDailyForm.setEdtbyusername((this.smtUserService.queryById(hpgDaily.getEdtbyuserid()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getEdtbyuserid()).getUsername());
                hpgDailyForm.setPublishername((this.smtUserService.queryById(hpgDaily.getPublisher()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getPublisher()).getUsername());
                hpgDailyForm.setWritername((this.smtUserService.queryById(hpgDaily.getWriter()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getWriter()).getUsername());
                hpgDailyForm.setDailytypename((this.hpgDailytypeService.queryById(hpgDaily.getDailytype()) == null)? "" :
                        this.hpgDailytypeService.queryById(hpgDaily.getDailytype()).getTypename());
                hpgDailyForms.add(hpgDailyForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total", this.hpgDailyService.getCount(hpgDailyParam,null,null));
            resData.put("data", hpgDailyForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取日常动态出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("getKeyWordDailyList")
    public Result getKeyWordDailyList(PagedQueryForm<T> param) {
        HpgDaily hpgDailyParam = new HpgDaily();
        hpgDailyParam.setStatus("2");  //已发布
        JSONObject search = JSONObject.parseObject(String.valueOf(param.getSearchList()));
        hpgDailyParam.setContent(search.get("keyword").toString());
        try {
            List<HpgDaily> hpgDailies = this.hpgDailyService.queryAllByLimit(hpgDailyParam,
                    (param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(),null,null);
            List<HpgDailyForm> hpgDailyForms = new ArrayList<>();
            for (HpgDaily hpgDaily : hpgDailies) {
                HpgDailyForm hpgDailyForm = new HpgDailyForm();
                CommonUtil.copyObjProperties(hpgDailyForm, hpgDaily);
                hpgDailyForm.setContent(CommonUtil.formatHTMLTag(hpgDailyForm.getContent()));
                hpgDailyForm.setEdtbyusername((this.smtUserService.queryById(hpgDaily.getEdtbyuserid()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getEdtbyuserid()).getUsername());
                hpgDailyForm.setPublishername((this.smtUserService.queryById(hpgDaily.getPublisher()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getPublisher()).getUsername());
                hpgDailyForm.setWritername((this.smtUserService.queryById(hpgDaily.getWriter()) == null)? "" :
                        this.smtUserService.queryById(hpgDaily.getWriter()).getUsername());
                hpgDailyForm.setDailytypename((this.hpgDailytypeService.queryById(hpgDaily.getDailytype()) == null)? "" :
                        this.hpgDailytypeService.queryById(hpgDaily.getDailytype()).getTypename());
                hpgDailyForms.add(hpgDailyForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("total", this.hpgDailyService.getCount(hpgDailyParam,null,null));
            resData.put("data", hpgDailyForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total", 0);
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "关键字获取日常动态出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 获取日常动态详情显示到前端
     *
     * @return 数据列表
     */
    @ApiOperation(value = "获取日常动态详情显示到前端")
    @GetMapping("showDaily")
    public Result showDaily(String dailyId) {
        try {
            HpgDaily hpgDaily = this.hpgDailyService.queryById(dailyId);
            HpgDailyForm hpgDailyForm = null;
            if (!hpgDaily.getPublisher().equals("")) {
                hpgDaily.setViewtotal(((hpgDaily.getViewtotal() == null)? 0 : hpgDaily.getViewtotal()) + 1);
                this.hpgDailyService.update(hpgDaily);
                hpgDailyForm = new HpgDailyForm();
                CommonUtil.copyObjProperties(hpgDailyForm, hpgDaily);
                hpgDailyForm.setEdtbyusername((this.smtUserService.queryById(hpgDaily.getEdtbyuserid()) == null) ? "" :
                        this.smtUserService.queryById(hpgDaily.getEdtbyuserid()).getUsername());
                hpgDailyForm.setPublishername((this.smtUserService.queryById(hpgDaily.getPublisher()) == null) ? "" :
                        this.smtUserService.queryById(hpgDaily.getPublisher()).getUsername());
                hpgDailyForm.setWritername((this.smtUserService.queryById(hpgDaily.getWriter()) == null) ? "" :
                        this.smtUserService.queryById(hpgDaily.getWriter()).getUsername());
            }
            JSONObject resData = new JSONObject();
            resData.put("data", hpgDailyForm);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取日常动态详情出错！" + e.getMessage(), resData);
        }
    }

    /**
     * 通过分页获取数据
     *
     * @return 数据列表
     */
    @GetMapping("getReshowDailies")
    public Result getReshowDailies() {
        HpgDaily hpgDailyParam = new HpgDaily();
        hpgDailyParam.setIsreshow(true);
        hpgDailyParam.setStatus("2");
        try {
            List<HpgDaily> hpgDailies = this.hpgDailyService.queryAllByLimit(hpgDailyParam,
                    0, 5, "EdtTime", "desc",null,null);
            List<HpgReshowDailyForm> hpgReshowDailyForms = new ArrayList<>();
            for (HpgDaily hpgDaily : hpgDailies) {
                HpgReshowDailyForm hpgReshowDailyForm = new HpgReshowDailyForm();
                CommonUtil.copyObjProperties(hpgReshowDailyForm, hpgDaily);
                List<String> imagelist = getImgSrc(hpgDaily.getContent());
                hpgReshowDailyForm.setImageurl((imagelist.size()>0)? imagelist.get(0):"");
                hpgReshowDailyForms.add(hpgReshowDailyForm);
            }
            JSONObject resData = new JSONObject();
            resData.put("data", hpgReshowDailyForms);
            return ResultUtil.success(resData);
        } catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data", null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), "获取轮播日常动态出错！" + e.getMessage(), resData);
        }
    }
}
