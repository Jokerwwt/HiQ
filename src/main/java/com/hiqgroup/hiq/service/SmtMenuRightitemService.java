package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtMenuRightitem;

import java.util.List;

/**
 * 系统菜单功能项目表(SmtMenuRightitem)表服务接口
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:24
 */
public interface SmtMenuRightitemService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuid 主键
     * @return 实例对象
     */
    SmtMenuRightitem queryById(String menuid);

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param orderby 排序字段
     * @param asc     顺序倒序
     * @return 对象列表
     */
    List<SmtMenuRightitem> queryAllByLimit(int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    List<SmtMenuRightitem> queryAll(SmtMenuRightitem smtMenuRightitem);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    List<SmtMenuRightitem> queryAllLike(SmtMenuRightitem smtMenuRightitem);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    List<SmtMenuRightitem> queryAllWhereOr(SmtMenuRightitem smtMenuRightitem);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount();

    /**
     * 新增数据
     *
     * @param smtMenuRightitem 实例对象
     * @return 实例对象
     */
    SmtMenuRightitem insert(SmtMenuRightitem smtMenuRightitem);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtMenuRightitems List<SmtMenuRightitem> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtMenuRightitem> smtMenuRightitems);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtMenuRightitems List<SmtMenuRightitem> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtMenuRightitem> smtMenuRightitems);

    /**
     * 修改数据
     *
     * @param smtMenuRightitem 实例对象
     * @return 实例对象
     */
    SmtMenuRightitem update(SmtMenuRightitem smtMenuRightitem);

    /**
     * 通过主键删除数据
     *
     * @param menuid 主键
     * @return 是否成功
     */
    boolean deleteById(String menuid);

}
