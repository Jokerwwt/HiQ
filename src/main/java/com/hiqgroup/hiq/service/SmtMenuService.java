package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtMenu;

import java.util.List;

/**
 * 系统菜单资料表(SmtMenu)表服务接口
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:20
 */
public interface SmtMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtMenu queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param orderby 排序字段
     * @param asc     顺序倒序
     * @return 对象列表
     */
    List<SmtMenu> queryAllByLimit(int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenu 实例对象
     * @return 对象列表
     */
    List<SmtMenu> queryAll(SmtMenu smtMenu);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenu 实例对象
     * @return 对象列表
     */
    List<SmtMenu> queryAllLike(SmtMenu smtMenu);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenu 实例对象
     * @return 对象列表
     */
    List<SmtMenu> queryAllWhereOr(SmtMenu smtMenu);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount();

    /**
     * 新增数据
     *
     * @param smtMenu 实例对象
     * @return 实例对象
     */
    SmtMenu insert(SmtMenu smtMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtMenus List<SmtMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtMenu> smtMenus);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtMenus List<SmtMenu> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtMenu> smtMenus);

    /**
     * 修改数据
     *
     * @param smtMenu 实例对象
     * @return 实例对象
     */
    SmtMenu update(SmtMenu smtMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
