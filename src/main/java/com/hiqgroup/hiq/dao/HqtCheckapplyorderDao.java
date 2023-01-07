package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.HqtCheckapplyorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 委托检测申请单(HqtCheckapplyorder)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-06 10:20:15
 */
public interface HqtCheckapplyorderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtCheckapplyorder queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @param offset             查询起始位置
     * @param limit              查询条数
     * @return 对象列表
     */
    List<HqtCheckapplyorder> queryAllByLimit(@Param("hqtCheckapplyorder") HqtCheckapplyorder hqtCheckapplyorder, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @param offset             查询起始位置
     * @param limit              查询条数
     * @return 对象列表
     */
    List<HqtCheckapplyorder> queryNotEqualAllByLimit(@Param("hqtCheckapplyorder") HqtCheckapplyorder hqtCheckapplyorder, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 对象列表
     */
    List<HqtCheckapplyorder> queryAll(HqtCheckapplyorder hqtCheckapplyorder);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 对象列表
     */
    List<HqtCheckapplyorder> queryAllLike(HqtCheckapplyorder hqtCheckapplyorder);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 对象列表
     */
    List<HqtCheckapplyorder> queryAllWhereOr(HqtCheckapplyorder hqtCheckapplyorder);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HqtCheckapplyorder hqtCheckapplyorder, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(HqtCheckapplyorder hqtCheckapplyorder);

    /**
     * 新增数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 影响行数
     */
    int insert(HqtCheckapplyorder hqtCheckapplyorder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtCheckapplyorder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqtCheckapplyorder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtCheckapplyorder> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HqtCheckapplyorder> entities);

    /**
     * 修改数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 影响行数
     */
    int update(HqtCheckapplyorder hqtCheckapplyorder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
