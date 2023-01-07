package com.hiqgroup.hiq.controller;

import com.hiqgroup.hiq.entity.SmtUserrole;
import com.hiqgroup.hiq.service.SmtUserroleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户角色表(SmtUserrole)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:20:39
 */
@RestController
@RequestMapping("smtUserrole")
public class SmtUserroleController {
    /**
     * 服务对象
     */
    @Resource
    private SmtUserroleService smtUserroleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtUserrole selectOne(String id) {
        return this.smtUserroleService.queryById(id);
    }

}
