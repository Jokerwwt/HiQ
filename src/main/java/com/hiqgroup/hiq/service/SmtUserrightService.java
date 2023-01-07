package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtUserright;

import java.util.List;

/**
 * 用户权限表(SmtUserright)表服务接口
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:48
 */
public interface SmtUserrightService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    SmtUserright queryById(String userid);

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param orderby 排序字段
     * @param asc     顺序倒序
     * @return 对象列表
     */
    List<SmtUserright> queryAllByLimit(int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserright 实例对象
     * @return 对象列表
     */
    List<SmtUserright> queryAll(SmtUserright smtUserright);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserright 实例对象
     * @return 对象列表
     */
    List<SmtUserright> queryAllLike(SmtUserright smtUserright);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserright 实例对象
     * @return 对象列表
     */
    List<SmtUserright> queryAllWhereOr(SmtUserright smtUserright);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount();

    /**
     * 新增数据
     *
     * @param smtUserright 实例对象
     * @return 实例对象
     */
    SmtUserright insert(SmtUserright smtUserright);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtUserrights List<SmtUserright> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtUserright> smtUserrights);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtUserrights List<SmtUserright> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtUserright> smtUserrights);

    /**
     * 修改数据
     *
     * @param smtUserright 实例对象
     * @return 实例对象
     */
    SmtUserright update(SmtUserright smtUserright);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(String userid);

}
