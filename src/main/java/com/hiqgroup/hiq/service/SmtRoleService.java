package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtRole;

import java.util.List;
import java.util.Map;

/**
 * 系统角色表(SmtRole)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 09:55:09
 */
public interface SmtRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtRole queryById(String id);

    /**
     * 查询多条数据
     *
     * @param smtRole 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<SmtRole> queryAllByLimit(SmtRole smtRole, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param smtRole 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<SmtRole> queryNotEqualAllByLimit(SmtRole smtRole, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRole 实例对象
     * @return 对象列表
     */
    List<SmtRole> queryAll(SmtRole smtRole);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRole 实例对象
     * @return 对象列表
     */
    List<SmtRole> queryAllLike(SmtRole smtRole);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRole 实例对象
     * @return 对象列表
     */
    List<SmtRole> queryAllWhereOr(SmtRole smtRole);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(SmtRole smtRole, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(SmtRole smtRole);

    /**
     * 新增数据
     *
     * @param smtRole 实例对象
     * @return 实例对象
     */
    SmtRole insert(SmtRole smtRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtRoles List<SmtRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtRole> smtRoles);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtRoles List<SmtRole> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtRole> smtRoles);

    /**
     * 修改数据
     *
     * @param smtRole 实例对象
     * @return 实例对象
     */
    SmtRole update(SmtRole smtRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
