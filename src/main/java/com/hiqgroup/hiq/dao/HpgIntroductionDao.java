package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.HpgIntroduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台介绍(HpgIntroduction)表数据库访问层
 *
 * @author liugaqiong
 * @since 2021-11-16 22:12:01
 */
public interface HpgIntroductionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HpgIntroduction queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param hpgIntroduction 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    List<HpgIntroduction> queryAllByLimit(@Param("hpgIntroduction") HpgIntroduction hpgIntroduction, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);


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
     * @return 影响行数
     */
    int insert(HpgIntroduction hpgIntroduction);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HpgIntroduction> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HpgIntroduction> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HpgIntroduction> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HpgIntroduction> entities);

    /**
     * 修改数据
     *
     * @param hpgIntroduction 实例对象
     * @return 影响行数
     */
    int update(HpgIntroduction hpgIntroduction);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
