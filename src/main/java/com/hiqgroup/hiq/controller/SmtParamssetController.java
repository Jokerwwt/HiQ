package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.SmtParamsset;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtParamssetService;
import com.hiqgroup.hiq.utils.JwtConfig;
import com.hiqgroup.hiq.utils.Result;
import com.hiqgroup.hiq.utils.ResultEnum;
import com.hiqgroup.hiq.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统设置表(SmtParamsset)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:20:03
 */
@Api(tags = "系统参数相关接口")
@RestController
@RequestMapping("smtParamsset")
public class SmtParamssetController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private SmtParamssetService smtParamssetService;
    @Resource
    private HqtStatisticService hqtStatisticService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtParamsset selectOne(String id) {
        return this.smtParamssetService.queryById(id);
    }

    /**
     * 获取系统参数
     *
     * @return 数据列表
     */
    @GetMapping("getParams")
    public Result getParams() {
        try {
            List<SmtParamsset> smtParamssets = this.smtParamssetService.queryAll(new SmtParamsset());
            JSONObject resData = new JSONObject();
            resData.put("data",smtParamssets.get(0));
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取系统参数出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 设置系统参数
     ** @param smtParamsset
     * @return 设置反馈
     */
    @ApiOperation(value = "设置系统参数")
    @PostMapping("setParams")
    public Result setParams(@RequestBody SmtParamsset smtParamsset, HttpServletRequest request) {
        try {
            this.smtParamssetService.update(smtParamsset);
            //记录操作日志-----------
            String syslogRes = this.hqtStatisticService.setSyslog(jwtConfig.getCurrentUser().get("id").toString(),"SmtParamsSet",request.getRemoteAddr(),
                    "修改", request.getHeader("User-Agent"));
            if (!syslogRes.equals("")){
                return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),syslogRes);
            }
            //---------------------
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"设置系统参数出错！"+e.getMessage());
        }
    }

    /**
     * 获取系统色调参数
     *
     * @return 数据列表
     */
    @GetMapping("getIsBlackWhite")
    public Result getIsBlackWhite() {
        try {
            List<SmtParamsset> smtParamssets = this.smtParamssetService.queryAll(new SmtParamsset());
            JSONObject resData = new JSONObject();
            resData.put("data",smtParamssets.get(0).getIsblackwhite());
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取系统色调参数出错！"+e.getMessage(), resData);
        }
    }
}
