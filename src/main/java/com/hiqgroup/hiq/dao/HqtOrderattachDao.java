package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.HqtOrderattach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单据附件表(HqtOrderattach)表数据库访问层
 *
 * @author liugaqiong
 * @since 2021-12-22 21:46:15
 */
public interface HqtOrderattachDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HqtOrderattach queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param hqtOrderattach 实例对象
     * @param offset         查询起始位置
     * @param limit          查询条数
     * @return 对象列表
     */
    List<HqtOrderattach> queryAllByLimit(@Param("hqtOrderattach") HqtOrderattach hqtOrderattach, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 查询指定行数据
     *
     * @param hqtOrderattach 实例对象
     * @param offset         查询起始位置
     * @param limit          查询条数
     * @return 对象列表
     */
    List<HqtOrderattach> queryNotEqualAllByLimit(@Param("hqtOrderattach") HqtOrderattach hqtOrderattach, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

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
     * @return 影响行数
     */
    int insert(HqtOrderattach hqtOrderattach);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtOrderattach> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HqtOrderattach> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HqtOrderattach> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HqtOrderattach> entities);

    /**
     * 修改数据
     *
     * @param hqtOrderattach 实例对象
     * @return 影响行数
     */
    int update(HqtOrderattach hqtOrderattach);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
