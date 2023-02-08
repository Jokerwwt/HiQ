package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.HpgAbout;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 关于我们(HpgAbout)表数据库访问层
 *
 * @author liugaqiong
 * @since 2021-12-14 16:10:26
 */
public interface HpgAboutDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HpgAbout queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param hpgAbout 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<HpgAbout> queryAllByLimit(@Param("hpgAbout") HpgAbout hpgAbout, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgAbout 实例对象
     * @return 对象列表
     */
    List<HpgAbout> queryAll(HpgAbout hpgAbout);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgAbout 实例对象
     * @return 对象列表
     */
    List<HpgAbout> queryAllLike(HpgAbout hpgAbout);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgAbout 实例对象
     * @return 对象列表
     */
    List<HpgAbout> queryAllWhereOr(HpgAbout hpgAbout);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HpgAbout hpgAbout);

    /**
     * 新增数据
     *
     * @param hpgAbout 实例对象
     * @return 影响行数
     */
    int insert(HpgAbout hpgAbout);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HpgAbout> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HpgAbout> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HpgAbout> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HpgAbout> entities);

    /**
     * 修改数据
     *
     * @param hpgAbout 实例对象
     * @return 影响行数
     */
    int update(HpgAbout hpgAbout);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
