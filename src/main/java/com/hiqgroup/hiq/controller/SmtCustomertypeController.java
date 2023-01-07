package com.hiqgroup.hiq.controller;

import com.alibaba.fastjson.JSONObject;
import com.hiqgroup.hiq.entity.SmtCustomertype;
import com.hiqgroup.hiq.form.PagedQueryForm;
import com.hiqgroup.hiq.service.SmtCustomertypeService;
import com.hiqgroup.hiq.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * A类，业务员自己独立获得的
 * B类，公司推送，但业务员重点推进的
 * C类，公司推送的成熟客户，业务员(SmtCustomertype)表控制层
 *
 * @author liugaqiong
 * @since 2021-11-16 22:10:54
 */
@RestController
@RequestMapping("smtCustomertype")
public class SmtCustomertypeController {
    /**
     * 服务对象
     */
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private SmtCustomertypeService smtCustomertypeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtCustomertype selectOne(String id) {
        return this.smtCustomertypeService.queryById(id);
    }

    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(PagedQueryForm<T> param){
        SmtCustomertype smtCustomertype=new SmtCustomertype();
        try{
            List<SmtCustomertype> smtCustomertypes=this.smtCustomertypeService.queryAllByLimit(
                    smtCustomertype,(param.getPage() - 1) * param.getLimit(), param.getLimit(),
                    param.getSort(), param.getOrdertype(), null, null
            );
            JSONObject resdata=new JSONObject();
            resdata.put("total",this.smtCustomertypeService.getCount(smtCustomertype, null, null));
            resdata.put("newtoken",jwtConfig.getNewToken());
            resdata.put("data",smtCustomertypes);
            return ResultUtil.success(resdata);
        }catch (Exception e){
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("newtoken",jwtConfig.getNewToken());
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取委托单位类别出错！"+e.getMessage(), resData);
        }
    }


    @ApiOperation(value = "删除委托单位类别信息")
    @PostMapping("delete")
    public Result delete(@RequestBody List<String> smtCustomertypeIds) {
        try {
            for (String smtCustomertypeId:smtCustomertypeIds) {
                this.smtCustomertypeService.deleteById(smtCustomertypeId);
            }
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }

    @ApiOperation(value = "新增委托单位类别信息")
    @PostMapping("create")
    public Result create(@RequestBody SmtCustomertype smtCustomertype) {
        try {
            smtCustomertype.setId(UUID.randomUUID().toString());
            this.smtCustomertypeService.insert(smtCustomertype);
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }

    /**
     * 通过主键更新单条数据
     ** @param
     * @return 更新反馈
     */
    @ApiOperation(value = "更新委托单位类别信息")
    @PostMapping("update")
    public Result update(@RequestBody SmtCustomertype smtCustomertype) {
        try {
            this.smtCustomertypeService.update(smtCustomertype);
            return ResultUtil.success();
        }catch (Exception e) {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),ResultEnum.SYSTEM_ERROR.getMsg()+e.getMessage());
        }
    }

    /**
     * 获取全部数据
     *
     * @return 数据列表
     */
    @GetMapping("queryAll")
    public Result queryAll() {
        try {
            List<SmtCustomertype> smtCustomertypes = this.smtCustomertypeService.queryAll(new SmtCustomertype());
            JSONObject resData = new JSONObject();
            resData.put("total",smtCustomertypes.size());
            resData.put("data",smtCustomertypes);
            return ResultUtil.success(resData);
        }catch (Exception e) {
            JSONObject resData = new JSONObject();
            resData.put("total",0);
            resData.put("data",null);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(),"获取委托单位类别出错！"+e.getMessage(), resData);
        }
    }
}
