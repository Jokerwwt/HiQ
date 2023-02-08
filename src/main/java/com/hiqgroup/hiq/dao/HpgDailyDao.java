package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.HpgDaily;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 日常动态(HpgDaily)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-06 09:32:15
 */
public interface HpgDailyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HpgDaily queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param hpgDaily 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<HpgDaily> queryAllByLimit(@Param("hpgDaily") HpgDaily hpgDaily, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param hpgDaily 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<HpgDaily> queryNotEqualAllByLimit(@Param("hpgDaily") HpgDaily hpgDaily, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDaily 实例对象
     * @return 对象列表
     */
    List<HpgDaily> queryAll(HpgDaily hpgDaily);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDaily 实例对象
     * @return 对象列表
     */
    List<HpgDaily> queryAllLike(HpgDaily hpgDaily);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDaily 实例对象
     * @return 对象列表
     */
    List<HpgDaily> queryAllWhereOr(HpgDaily hpgDaily);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HpgDaily hpgDaily, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(HpgDaily hpgDaily);

    /**
     * 新增数据
     *
     * @param hpgDaily 实例对象
     * @return 影响行数
     */
    int insert(HpgDaily hpgDaily);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HpgDaily> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HpgDaily> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HpgDaily> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HpgDaily> entities);

    /**
     * 修改数据
     *
     * @param hpgDaily 实例对象
     * @return 影响行数
     */
    int update(HpgDaily hpgDaily);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
