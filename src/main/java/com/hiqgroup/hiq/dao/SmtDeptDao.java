package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.SmtDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 单位部门资料表(SmtDept)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-06 09:53:48
 */
public interface SmtDeptDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtDept queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param smtDept 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<SmtDept> queryAllByLimit(@Param("smtDept") SmtDept smtDept, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param smtDept 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<SmtDept> queryNotEqualAllByLimit(@Param("smtDept") SmtDept smtDept, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtDept 实例对象
     * @return 对象列表
     */
    List<SmtDept> queryAll(SmtDept smtDept);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtDept 实例对象
     * @return 对象列表
     */
    List<SmtDept> queryAllLike(SmtDept smtDept);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtDept 实例对象
     * @return 对象列表
     */
    List<SmtDept> queryAllWhereOr(SmtDept smtDept);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(SmtDept smtDept, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(SmtDept smtDept);

    /**
     * 新增数据
     *
     * @param smtDept 实例对象
     * @return 影响行数
     */
    int insert(SmtDept smtDept);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtDept> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SmtDept> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtDept> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SmtDept> entities);

    /**
     * 修改数据
     *
     * @param smtDept 实例对象
     * @return 影响行数
     */
    int update(SmtDept smtDept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
