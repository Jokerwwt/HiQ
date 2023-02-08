package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HqtCustomerorder;

import java.util.List;
import java.util.Map;

/**
 * 委托方订单(HqtCustomerorder)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-05 16:24:29
 */
public interface HqtCustomerorderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtCustomerorder queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hqtCustomerorder 实例对象
     * @param offset           查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    List<HqtCustomerorder> queryAllByLimit(HqtCustomerorder hqtCustomerorder, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param hqtCustomerorder 实例对象
     * @param offset           查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    List<HqtCustomerorder> queryNotEqualAllByLimit(HqtCustomerorder hqtCustomerorder, int offset, int limit, String orderby, String asc);

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
    int getCount(HqtCustomerorder hqtCustomerorder, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

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
     * @return 实例对象
     */
    HqtCustomerorder insert(HqtCustomerorder hqtCustomerorder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtCustomerorders List<HqtCustomerorder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HqtCustomerorder> hqtCustomerorders);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtCustomerorders List<HqtCustomerorder> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HqtCustomerorder> hqtCustomerorders);

    /**
     * 修改数据
     *
     * @param hqtCustomerorder 实例对象
     * @return 实例对象
     */
    HqtCustomerorder update(HqtCustomerorder hqtCustomerorder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
