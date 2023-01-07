package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.HqtCheckorder;
import com.hiqgroup.hiq.entity.HqtOrderattach;
import com.hiqgroup.hiq.service.HqtCheckorderService;
import com.hiqgroup.hiq.service.HqtOrderattachService;
import com.hiqgroup.hiq.utils.Result;
import com.hiqgroup.hiq.utils.ResultEnum;
import com.hiqgroup.hiq.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单据附件表(HqtOrderattach)表控制层
 *
 * @author liugaqiong
 * @since 2021-12-23 10:56:24
 */
@RestController
@RequestMapping("hqtOrderattach")
public class HqtOrderattachController {
    /**
     * 服务对象
     */
    @Resource
    private HqtOrderattachService hqtOrderattachService;
    @Resource
    private HqtCheckorderService hqtCheckorderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HqtOrderattach selectOne(String id) {
        return this.hqtOrderattachService.queryById(id);
    }

    /**
     * 获取订单附件列表
     *
     * @return 数据列表
     */
    @GetMapping("getAttachs")
    public Result getAttachs(String orderId) {
        HqtOrderattach hqtOrderattachParam = new HqtOrderattach();
        hqtOrderattachParam.setOrderid(orderId);
        hqtOrderattachParam.setFiletype("1");
        try{
            List<HqtOrderattach> hqtOrderattachs = this.hqtOrderattachService.queryAll(hqtOrderattachParam);
            JSONObject resData = new JSONObject();
            resData.put("data",hqtOrderattachs);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取订单附件出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 获取订单报告
     *
     * @return 数据列表
     */
    @GetMapping("getReport")
    public Result getReport(String orderId) {
        HqtOrderattach hqtOrderattachParam = new HqtOrderattach();
        hqtOrderattachParam.setOrderid(orderId);
        hqtOrderattachParam.setFiletype("2");
        try{
            List<HqtOrderattach> hqtOrderattachs = this.hqtOrderattachService.queryAll(hqtOrderattachParam);
            JSONObject resData = new JSONObject();
            resData.put("data",hqtOrderattachs);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取订单报告出错！"+e.getMessage(), resData);
        }
    }

    /**
     * 获取委托订单报告
     *
     * @return 数据列表
     */
    @GetMapping("getCustomerOrderReport")
    public Result getCustomerOrderReport(String orderId) {
        HqtCheckorder hqtCheckorderParam = new HqtCheckorder();
        hqtCheckorderParam.setCustorderid(orderId);
        List<HqtCheckorder> hqtCheckorders = this.hqtCheckorderService.queryAll(hqtCheckorderParam);
        if (hqtCheckorders.size() < 1){  //无对应检测订单
            return ResultUtil.success();
        }
        HqtOrderattach hqtOrderattachParam = new HqtOrderattach();
        hqtOrderattachParam.setOrderid(hqtCheckorders.get(0).getId());
        hqtOrderattachParam.setFiletype("2");
        try{
            List<HqtOrderattach> hqtOrderattachs = this.hqtOrderattachService.queryAll(hqtOrderattachParam);
            JSONObject resData = new JSONObject();
            resData.put("data",hqtOrderattachs);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取订单报告出错！"+e.getMessage(), resData);
        }
    }
}
