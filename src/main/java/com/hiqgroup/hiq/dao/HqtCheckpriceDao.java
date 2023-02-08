package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.HqtCheckprice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 检测报价表(HqtCheckprice)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-06 11:08:12
 */
public interface HqtCheckpriceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtCheckprice queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param hqtCheckprice 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    List<HqtCheckprice> queryAllByLimit(@Param("hqtCheckprice") HqtCheckprice hqtCheckprice, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param hqtCheckprice 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    List<HqtCheckprice> queryNotEqualAllByLimit(@Param("hqtCheckprice") HqtCheckprice hqtCheckprice, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckprice 实例对象
     * @return 对象列表
     */
    List<HqtCheckprice> queryAll(HqtCheckprice hqtCheckprice);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckprice 实例对象
     * @return 对象列表
     */
    List<HqtCheckprice> queryAllLike(HqtCheckprice hqtCheckprice);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckprice 实例对象
     * @return 对象列表
     */
    List<HqtCheckprice> queryAllWhereOr(HqtCheckprice hqtCheckprice);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HqtCheckprice hqtCheckprice, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(HqtCheckprice hqtCheckprice);

    /**
     * 新增数据
     *
     * @param hqtCheckprice 实例对象
     * @return 影响行数
     */
    int insert(HqtCheckprice hqtCheckprice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtCheckprice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqtCheckprice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtCheckprice> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HqtCheckprice> entities);

    /**
     * 修改数据
     *
     * @param hqtCheckprice 实例对象
     * @return 影响行数
     */
    int update(HqtCheckprice hqtCheckprice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
