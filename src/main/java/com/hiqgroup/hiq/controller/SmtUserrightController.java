package com.hiqgroup.hiq.controller;

import com.hiqgroup.hiq.entity.SmtUserright;
import com.hiqgroup.hiq.service.SmtUserrightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户权限表(SmtUserright)表控制层
 *
 * @author makejava
 * @since 2020-12-17 21:20:33
 */
@RestController
@RequestMapping("smtUserright")
public class SmtUserrightController {
    /**
     * 服务对象
     */
    @Resource
    private SmtUserrightService smtUserrightService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SmtUserright selectOne(String id) {
        return this.smtUserrightService.queryById(id);
    }

}
