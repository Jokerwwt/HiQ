package com.hiqgroup.hiq.controller;

import com.hiqgroup.hiq.entity.SmtMenuRightitem;
import com.hiqgroup.hiq.service.SmtMenuRightitemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统菜单功能项目表(SmtMenuRightitem)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:19:56
 */
@RestController
@RequestMapping("smtMenuRightitem")
public class SmtMenuRightitemController {
    /**
     * 服务对象
     */
    @Resource
    private SmtMenuRightitemService smtMenuRightitemService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtMenuRightitem selectOne(String id) {
        return this.smtMenuRightitemService.queryById(id);
    }

}
