package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.HqtCustomerorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 委托方订单(HqtCustomerorder)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-05 16:24:27
 */
public interface HqtCustomerorderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtCustomerorder queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param hqtCustomerorder 实例对象
     * @param offset           查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    List<HqtCustomerorder> queryAllByLimit(@Param("hqtCustomerorder") HqtCustomerorder hqtCustomerorder, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param hqtCustomerorder 实例对象
     * @param offset           查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    List<HqtCustomerorder> queryNotEqualAllByLimit(@Param("hqtCustomerorder") HqtCustomerorder hqtCustomerorder, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCustomerorder 实例对象
     * @return 对象列表
     */
    List<HqtCustomerorder> queryAll(HqtCustomerorder hqtCustomerorder);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCustomerorder 实例对象
     * @return 对象列表
     */
    List<HqtCustomerorder> queryAllLike(HqtCustomerorder hqtCustomerorder);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCustomerorder 实例对象
     * @return 对象列表
     */
    List<HqtCustomerorder> queryAllWhereOr(HqtCustomerorder hqtCustomerorder);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HqtCustomerorder hqtCustomerorder, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(HqtCustomerorder hqtCustomerorder);

    /**
     * 新增数据
     *
     * @param hqtCustomerorder 实例对象
     * @return 影响行数
     */
    int insert(HqtCustomerorder hqtCustomerorder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtCustomerorder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqtCustomerorder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtCustomerorder> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HqtCustomerorder> entities);

    /**
     * 修改数据
     *
     * @param hqtCustomerorder 实例对象
     * @return 影响行数
     */
    int update(HqtCustomerorder hqtCustomerorder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
