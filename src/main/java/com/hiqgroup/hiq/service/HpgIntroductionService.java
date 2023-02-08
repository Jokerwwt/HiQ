package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HpgIntroduction;

import java.util.List;

/**
 * 平台介绍(HpgIntroduction)表服务接口
 *
 * @author liugaqiong
 * @since 2021-11-16 22:12:01
 */
public interface HpgIntroductionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HpgIntroduction queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hpgIntroduction 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    List<HpgIntroduction> queryAllByLimit(HpgIntroduction hpgIntroduction, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgIntroduction 实例对象
     * @return 对象列表
     */
    List<HpgIntroduction> queryAll(HpgIntroduction hpgIntroduction);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgIntroduction 实例对象
     * @return 对象列表
     */
    List<HpgIntroduction> queryAllLike(HpgIntroduction hpgIntroduction);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgIntroduction 实例对象
     * @return 对象列表
     */
    List<HpgIntroduction> queryAllWhereOr(HpgIntroduction hpgIntroduction);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount();

    /**
     * 新增数据
     *
     * @param hpgIntroduction 实例对象
     * @return 实例对象
     */
    HpgIntroduction insert(HpgIntroduction hpgIntroduction);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgIntroductions List<HpgIntroduction> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HpgIntroduction> hpgIntroductions);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgIntroductions List<HpgIntroduction> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HpgIntroduction> hpgIntroductions);

    /**
     * 修改数据
     *
     * @param hpgIntroduction 实例对象
     * @return 实例对象
     */
    HpgIntroduction update(HpgIntroduction hpgIntroduction);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
