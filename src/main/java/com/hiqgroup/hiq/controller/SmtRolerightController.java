package com.hiqgroup.hiq.controller;

import com.hiqgroup.hiq.entity.SmtRoleright;
import com.hiqgroup.hiq.service.SmtRolerightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限表(SmtRoleright)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:20:17
 */
@RestController
@RequestMapping("smtRoleright")
public class SmtRolerightController {
    /**
     * 服务对象
     */
    @Resource
    private SmtRolerightService smtRolerightService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtRoleright selectOne(String id) {
        return this.smtRolerightService.queryById(id);
    }

}
