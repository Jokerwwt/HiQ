package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HqtOrderattach;

import java.util.List;

/**
 * 单据附件表(HqtOrderattach)表服务接口
 *
 * @author liugaqiong
 * @since 2021-12-22 21:46:16
 */
public interface HqtOrderattachService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtOrderattach queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hqtOrderattach 实例对象
     * @param offset         查询起始位置
     * @param limit          查询条数
     * @return 对象列表
     */
    List<HqtOrderattach> queryAllByLimit(HqtOrderattach hqtOrderattach, int offset, int limit, String orderby, String asc);

    /**
     * 查询不等于多条数据
     *
     * @param hqtOrderattach 实例对象
     * @param offset         查询起始位置
     * @param limit          查询条数
     * @return 对象列表
     */
    List<HqtOrderattach> queryNotEqualAllByLimit(HqtOrderattach hqtOrderattach, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtOrderattach 实例对象
     * @return 对象列表
     */
    List<HqtOrderattach> queryAll(HqtOrderattach hqtOrderattach);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtOrderattach 实例对象
     * @return 对象列表
     */
    List<HqtOrderattach> queryAllLike(HqtOrderattach hqtOrderattach);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtOrderattach 实例对象
     * @return 对象列表
     */
    List<HqtOrderattach> queryAllWhereOr(HqtOrderattach hqtOrderattach);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HqtOrderattach hqtOrderattach);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(HqtOrderattach hqtOrderattach);

    /**
     * 新增数据
     *
     * @param hqtOrderattach 实例对象
     * @return 实例对象
     */
    HqtOrderattach insert(HqtOrderattach hqtOrderattach);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtOrderattachs List<HqtOrderattach> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HqtOrderattach> hqtOrderattachs);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtOrderattachs List<HqtOrderattach> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HqtOrderattach> hqtOrderattachs);

    /**
     * 修改数据
     *
     * @param hqtOrderattach 实例对象
     * @return 实例对象
     */
    HqtOrderattach update(HqtOrderattach hqtOrderattach);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
