package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HqtCheckprice;

import java.util.List;
import java.util.Map;

/**
 * 检测报价表(HqtCheckprice)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 11:08:13
 */
public interface HqtCheckpriceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtCheckprice queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hqtCheckprice 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    List<HqtCheckprice> queryAllByLimit(HqtCheckprice hqtCheckprice, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param hqtCheckprice 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    List<HqtCheckprice> queryNotEqualAllByLimit(HqtCheckprice hqtCheckprice, int offset, int limit, String orderby, String asc);

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
    int getCount(HqtCheckprice hqtCheckprice, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

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
     * @return 实例对象
     */
    HqtCheckprice insert(HqtCheckprice hqtCheckprice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckprices List<HqtCheckprice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HqtCheckprice> hqtCheckprices);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckprices List<HqtCheckprice> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HqtCheckprice> hqtCheckprices);

    /**
     * 修改数据
     *
     * @param hqtCheckprice 实例对象
     * @return 实例对象
     */
    HqtCheckprice update(HqtCheckprice hqtCheckprice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
