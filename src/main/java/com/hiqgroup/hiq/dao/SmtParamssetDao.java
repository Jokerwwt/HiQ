package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.SmtParamsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统设置表(SmtParamsset)表数据库访问层
 *
 * @author liugaqiong
 * @since 2021-12-10 16:07:12
 */
public interface SmtParamssetDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtParamsset queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param smtParamsset 实例对象
     * @param offset       查询起始位置
     * @param limit        查询条数
     * @return 对象列表
     */
    List<SmtParamsset> queryAllByLimit(@Param("smtParamsset") SmtParamsset smtParamsset, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);


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
     * @return 影响行数
     */
    int insert(SmtParamsset smtParamsset);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtParamsset> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SmtParamsset> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtParamsset> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SmtParamsset> entities);

    /**
     * 修改数据
     *
     * @param smtParamsset 实例对象
     * @return 影响行数
     */
    int update(SmtParamsset smtParamsset);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
