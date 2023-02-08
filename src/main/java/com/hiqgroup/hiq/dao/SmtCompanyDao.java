package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.SmtCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 单位资料表(SmtCompany)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-06 09:50:33
 */
public interface SmtCompanyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtCompany queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param smtCompany 实例对象
     * @param offset     查询起始位置
     * @param limit      查询条数
     * @return 对象列表
     */
    List<SmtCompany> queryAllByLimit(@Param("smtCompany") SmtCompany smtCompany, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param smtCompany 实例对象
     * @param offset     查询起始位置
     * @param limit      查询条数
     * @return 对象列表
     */
    List<SmtCompany> queryNotEqualAllByLimit(@Param("smtCompany") SmtCompany smtCompany, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    List<SmtCompany> queryAll(SmtCompany smtCompany);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    List<SmtCompany> queryAllLike(SmtCompany smtCompany);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    List<SmtCompany> queryAllWhereOr(SmtCompany smtCompany);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(SmtCompany smtCompany, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(SmtCompany smtCompany);

    /**
     * 新增数据
     *
     * @param smtCompany 实例对象
     * @return 影响行数
     */
    int insert(SmtCompany smtCompany);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtCompany> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SmtCompany> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtCompany> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SmtCompany> entities);

    /**
     * 修改数据
     *
     * @param smtCompany 实例对象
     * @return 影响行数
     */
    int update(SmtCompany smtCompany);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
