package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtParamsset;

import java.util.List;

/**
 * 系统设置表(SmtParamsset)表服务接口
 *
 * @author liugaqiong
 * @since 2021-12-10 16:07:13
 */
public interface SmtParamssetService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtParamsset queryById(String id);

    /**
     * 查询多条数据
     *
     * @param smtParamsset 实例对象
     * @param offset       查询起始位置
     * @param limit        查询条数
     * @return 对象列表
     */
    List<SmtParamsset> queryAllByLimit(SmtParamsset smtParamsset, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtParamsset 实例对象
     * @return 对象列表
     */
    List<SmtParamsset> queryAll(SmtParamsset smtParamsset);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtParamsset 实例对象
     * @return 对象列表
     */
    List<SmtParamsset> queryAllLike(SmtParamsset smtParamsset);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtParamsset 实例对象
     * @return 对象列表
     */
    List<SmtParamsset> queryAllWhereOr(SmtParamsset smtParamsset);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(SmtParamsset smtParamsset);

    /**
     * 新增数据
     *
     * @param smtParamsset 实例对象
     * @return 实例对象
     */
    SmtParamsset insert(SmtParamsset smtParamsset);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtParamssets List<SmtParamsset> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtParamsset> smtParamssets);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtParamssets List<SmtParamsset> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtParamsset> smtParamssets);

    /**
     * 修改数据
     *
     * @param smtParamsset 实例对象
     * @return 实例对象
     */
    SmtParamsset update(SmtParamsset smtParamsset);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
