package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HqtCheckorder;

import java.util.List;
import java.util.Map;

/**
 * 检测方订单(HqtCheckorder)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 10:27:12
 */
public interface HqtCheckorderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtCheckorder queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hqtCheckorder 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    List<HqtCheckorder> queryAllByLimit(HqtCheckorder hqtCheckorder, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param hqtCheckorder 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    List<HqtCheckorder> queryNotEqualAllByLimit(HqtCheckorder hqtCheckorder, int offset, int limit, String orderby, String asc);

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
    int getCount(HqtCheckorder hqtCheckorder, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

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
     * @return 实例对象
     */
    HqtCheckorder insert(HqtCheckorder hqtCheckorder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckorders List<HqtCheckorder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HqtCheckorder> hqtCheckorders);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckorders List<HqtCheckorder> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HqtCheckorder> hqtCheckorders);

    /**
     * 修改数据
     *
     * @param hqtCheckorder 实例对象
     * @return 实例对象
     */
    HqtCheckorder update(HqtCheckorder hqtCheckorder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
