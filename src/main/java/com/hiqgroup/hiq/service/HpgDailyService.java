package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HpgDaily;

import java.util.List;
import java.util.Map;

/**
 * 日常动态(HpgDaily)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 09:32:17
 */
public interface HpgDailyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HpgDaily queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hpgDaily 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<HpgDaily> queryAllByLimit(HpgDaily hpgDaily, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param hpgDaily 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<HpgDaily> queryNotEqualAllByLimit(HpgDaily hpgDaily, int offset, int limit, String orderby, String asc);

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
    int getCount(HpgDaily hpgDaily, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

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
     * @return 实例对象
     */
    HpgDaily insert(HpgDaily hpgDaily);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgDailys List<HpgDaily> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HpgDaily> hpgDailys);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgDailys List<HpgDaily> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HpgDaily> hpgDailys);

    /**
     * 修改数据
     *
     * @param hpgDaily 实例对象
     * @return 实例对象
     */
    HpgDaily update(HpgDaily hpgDaily);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
