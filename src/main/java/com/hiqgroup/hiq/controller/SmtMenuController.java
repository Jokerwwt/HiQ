package com.hiqgroup.hiq.controller;

import com.hiqgroup.hiq.entity.SmtMenu;
import com.hiqgroup.hiq.service.SmtMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统菜单资料表(SmtMenu)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:19:50
 */
@RestController
@RequestMapping("smtMenu")
public class SmtMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SmtMenuService smtMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtMenu selectOne(String id) {
        return this.smtMenuService.queryById(id);
    }

}
