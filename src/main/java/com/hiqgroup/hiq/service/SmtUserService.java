package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtUser;

import java.util.List;
import java.util.Map;

/**
 * 用户资料表(SmtUser)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 09:58:01
 */
public interface SmtUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtUser queryById(String id);

    /**
     * 查询多条数据
     *
     * @param smtUser 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<SmtUser> queryAllByLimit(SmtUser smtUser, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param smtUser 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<SmtUser> queryNotEqualAllByLimit(SmtUser smtUser, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUser 实例对象
     * @return 对象列表
     */
    List<SmtUser> queryAll(SmtUser smtUser);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUser 实例对象
     * @return 对象列表
     */
    List<SmtUser> queryAllLike(SmtUser smtUser);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUser 实例对象
     * @return 对象列表
     */
    List<SmtUser> queryAllWhereOr(SmtUser smtUser);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(SmtUser smtUser, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(SmtUser smtUser);

    /**
     * 新增数据
     *
     * @param smtUser 实例对象
     * @return 实例对象
     */
    SmtUser insert(SmtUser smtUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtUsers List<SmtUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtUser> smtUsers);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtUsers List<SmtUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtUser> smtUsers);

    /**
     * 修改数据
     *
     * @param smtUser 实例对象
     * @return 实例对象
     */
    SmtUser update(SmtUser smtUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
