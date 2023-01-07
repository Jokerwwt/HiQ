package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.SmtUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户资料表(SmtUser)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-06 09:58:00
 */
public interface SmtUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtUser queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param smtUser 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<SmtUser> queryAllByLimit(@Param("smtUser") SmtUser smtUser, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param smtUser 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<SmtUser> queryNotEqualAllByLimit(@Param("smtUser") SmtUser smtUser, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

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
    int getCount(SmtUser smtUser, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

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
     * @return 影响行数
     */
    int insert(SmtUser smtUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SmtUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SmtUser> entities);

    /**
     * 修改数据
     *
     * @param smtUser 实例对象
     * @return 影响行数
     */
    int update(SmtUser smtUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
