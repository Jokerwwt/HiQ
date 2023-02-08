package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.HqtCheckorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 检测方订单(HqtCheckorder)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-06 10:27:10
 */
public interface HqtCheckorderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtCheckorder queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param hqtCheckorder 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    List<HqtCheckorder> queryAllByLimit(@Param("hqtCheckorder") HqtCheckorder hqtCheckorder, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param hqtCheckorder 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    List<HqtCheckorder> queryNotEqualAllByLimit(@Param("hqtCheckorder") HqtCheckorder hqtCheckorder, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckorder 实例对象
     * @return 对象列表
     */
    List<HqtCheckorder> queryAll(HqtCheckorder hqtCheckorder);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckorder 实例对象
     * @return 对象列表
     */
    List<HqtCheckorder> queryAllLike(HqtCheckorder hqtCheckorder);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckorder 实例对象
     * @return 对象列表
     */
    List<HqtCheckorder> queryAllWhereOr(HqtCheckorder hqtCheckorder);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HqtCheckorder hqtCheckorder, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(HqtCheckorder hqtCheckorder);

    /**
     * 新增数据
     *
     * @param hqtCheckorder 实例对象
     * @return 影响行数
     */
    int insert(HqtCheckorder hqtCheckorder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtCheckorder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqtCheckorder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtCheckorder> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HqtCheckorder> entities);

    /**
     * 修改数据
     *
     * @param hqtCheckorder 实例对象
     * @return 影响行数
     */
    int update(HqtCheckorder hqtCheckorder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
