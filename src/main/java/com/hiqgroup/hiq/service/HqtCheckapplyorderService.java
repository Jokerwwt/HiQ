package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HqtCheckapplyorder;

import java.util.List;
import java.util.Map;

/**
 * 委托检测申请单(HqtCheckapplyorder)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 10:20:16
 */
public interface HqtCheckapplyorderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtCheckapplyorder queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @param offset             查询起始位置
     * @param limit              查询条数
     * @return 对象列表
     */
    List<HqtCheckapplyorder> queryAllByLimit(HqtCheckapplyorder hqtCheckapplyorder, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @param offset             查询起始位置
     * @param limit              查询条数
     * @return 对象列表
     */
    List<HqtCheckapplyorder> queryNotEqualAllByLimit(HqtCheckapplyorder hqtCheckapplyorder, int offset, int limit, String orderby, String asc);

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
    int getCount(HqtCheckapplyorder hqtCheckapplyorder, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

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
     * @return 实例对象
     */
    HqtCheckapplyorder insert(HqtCheckapplyorder hqtCheckapplyorder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckapplyorders List<HqtCheckapplyorder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HqtCheckapplyorder> hqtCheckapplyorders);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckapplyorders List<HqtCheckapplyorder> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HqtCheckapplyorder> hqtCheckapplyorders);

    /**
     * 修改数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 实例对象
     */
    HqtCheckapplyorder update(HqtCheckapplyorder hqtCheckapplyorder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
