package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HpgDailytype;

import java.util.List;
import java.util.Map;

/**
 * 日常动态类别(HpgDailytype)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 09:38:56
 */
public interface HpgDailytypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HpgDailytype queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hpgDailytype 实例对象
     * @param offset       查询起始位置
     * @param limit        查询条数
     * @return 对象列表
     */
    List<HpgDailytype> queryAllByLimit(HpgDailytype hpgDailytype, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param hpgDailytype 实例对象
     * @param offset       查询起始位置
     * @param limit        查询条数
     * @return 对象列表
     */
    List<HpgDailytype> queryNotEqualAllByLimit(HpgDailytype hpgDailytype, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDailytype 实例对象
     * @return 对象列表
     */
    List<HpgDailytype> queryAll(HpgDailytype hpgDailytype);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDailytype 实例对象
     * @return 对象列表
     */
    List<HpgDailytype> queryAllLike(HpgDailytype hpgDailytype);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDailytype 实例对象
     * @return 对象列表
     */
    List<HpgDailytype> queryAllWhereOr(HpgDailytype hpgDailytype);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HpgDailytype hpgDailytype, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(HpgDailytype hpgDailytype);

    /**
     * 新增数据
     *
     * @param hpgDailytype 实例对象
     * @return 实例对象
     */
    HpgDailytype insert(HpgDailytype hpgDailytype);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgDailytypes List<HpgDailytype> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HpgDailytype> hpgDailytypes);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgDailytypes List<HpgDailytype> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HpgDailytype> hpgDailytypes);

    /**
     * 修改数据
     *
     * @param hpgDailytype 实例对象
     * @return 实例对象
     */
    HpgDailytype update(HpgDailytype hpgDailytype);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
