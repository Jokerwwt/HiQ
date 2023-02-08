package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.SmtMenuRightitem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单功能项目表(SmtMenuRightitem)表数据库访问层
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:23
 */
public interface SmtMenuRightitemDao {

    /**
     * 通过ID查询单条数据
     *
     * @param menuid 主键
     * @return 实例对象
     */
    SmtMenuRightitem queryById(String menuid);

    /**
     * 查询指定行数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param orderby 排序字段
     * @param asc     顺序降序
     * @return 对象列表
     */
    List<SmtMenuRightitem> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    List<SmtMenuRightitem> queryAll(SmtMenuRightitem smtMenuRightitem);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    List<SmtMenuRightitem> queryAllLike(SmtMenuRightitem smtMenuRightitem);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    List<SmtMenuRightitem> queryAllWhereOr(SmtMenuRightitem smtMenuRightitem);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount();

    /**
     * 新增数据
     *
     * @param smtMenuRightitem 实例对象
     * @return 影响行数
     */
    int insert(SmtMenuRightitem smtMenuRightitem);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtMenuRightitem> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SmtMenuRightitem> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtMenuRightitem> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SmtMenuRightitem> entities);

    /**
     * 修改数据
     *
     * @param smtMenuRightitem 实例对象
     * @return 影响行数
     */
    int update(SmtMenuRightitem smtMenuRightitem);

    /**
     * 通过主键删除数据
     *
     * @param menuid 主键
     * @return 影响行数
     */
    int deleteById(String menuid);

}
