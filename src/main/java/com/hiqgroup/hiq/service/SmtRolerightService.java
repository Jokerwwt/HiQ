package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtRoleright;

import java.util.List;

/**
 * 角色权限表(SmtRoleright)表服务接口
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:38
 */
public interface SmtRolerightService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleid 主键
     * @return 实例对象
     */
    SmtRoleright queryById(String roleid);

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param orderby 排序字段
     * @param asc     顺序倒序
     * @return 对象列表
     */
    List<SmtRoleright> queryAllByLimit(int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRoleright 实例对象
     * @return 对象列表
     */
    List<SmtRoleright> queryAll(SmtRoleright smtRoleright);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRoleright 实例对象
     * @return 对象列表
     */
    List<SmtRoleright> queryAllLike(SmtRoleright smtRoleright);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRoleright 实例对象
     * @return 对象列表
     */
    List<SmtRoleright> queryAllWhereOr(SmtRoleright smtRoleright);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount();

    /**
     * 新增数据
     *
     * @param smtRoleright 实例对象
     * @return 实例对象
     */
    SmtRoleright insert(SmtRoleright smtRoleright);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtRolerights List<SmtRoleright> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtRoleright> smtRolerights);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtRolerights List<SmtRoleright> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtRoleright> smtRolerights);

    /**
     * 修改数据
     *
     * @param smtRoleright 实例对象
     * @return 实例对象
     */
    SmtRoleright update(SmtRoleright smtRoleright);

    /**
     * 通过主键删除数据
     *
     * @param roleid 主键
     * @return 是否成功
     */
    boolean deleteById(String roleid);

}
