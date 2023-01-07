package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HpgProductService;

import java.util.List;

/**
 * 产品与服务(HpgProductService)表服务接口
 *
 * @author liugaqiong
 * @since 2021-11-16 22:12:02
 */
public interface HpgProductServiceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HpgProductService queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hpgProductService 实例对象
     * @param offset            查询起始位置
     * @param limit             查询条数
     * @return 对象列表
     */
    List<HpgProductService> queryAllByLimit(HpgProductService hpgProductService, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgProductService 实例对象
     * @return 对象列表
     */
    List<HpgProductService> queryAll(HpgProductService hpgProductService);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgProductService 实例对象
     * @return 对象列表
     */
    List<HpgProductService> queryAllLike(HpgProductService hpgProductService);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgProductService 实例对象
     * @return 对象列表
     */
    List<HpgProductService> queryAllWhereOr(HpgProductService hpgProductService);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount();

    /**
     * 新增数据
     *
     * @param hpgProductService 实例对象
     * @return 实例对象
     */
    HpgProductService insert(HpgProductService hpgProductService);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgProductServices List<HpgProductService> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HpgProductService> hpgProductServices);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgProductServices List<HpgProductService> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HpgProductService> hpgProductServices);

    /**
     * 修改数据
     *
     * @param hpgProductService 实例对象
     * @return 实例对象
     */
    HpgProductService update(HpgProductService hpgProductService);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
