package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.entity.SmtCustomertype;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * A类，业务员自己独立获得的
 * B类，公司推送，但业务员重点推进的
 * C类，公司推送的成熟客户，业务员(SmtCustomertype)表数据库访问层
 *
 * @author liugaqiong
 * @since 2023-01-06 09:52:39
 */
public interface SmtCustomertypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtCustomertype queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param smtCustomertype 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    List<SmtCustomertype> queryAllByLimit(@Param("smtCustomertype") SmtCustomertype smtCustomertype, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 查询指定行数据
     *
     * @param smtCustomertype 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    List<SmtCustomertype> queryNotEqualAllByLimit(@Param("smtCustomertype") SmtCustomertype smtCustomertype, @Param("offset") int offset, @Param("limit") int limit, @Param("orderby") String orderby, @Param("asc") String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCustomertype 实例对象
     * @return 对象列表
     */
    List<SmtCustomertype> queryAll(SmtCustomertype smtCustomertype);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCustomertype 实例对象
     * @return 对象列表
     */
    List<SmtCustomertype> queryAllLike(SmtCustomertype smtCustomertype);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCustomertype 实例对象
     * @return 对象列表
     */
    List<SmtCustomertype> queryAllWhereOr(SmtCustomertype smtCustomertype);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(SmtCustomertype smtCustomertype, @Param("ins") List<Map<String, List<String>>> ins, @Param("betweens") List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(SmtCustomertype smtCustomertype);

    /**
     * 新增数据
     *
     * @param smtCustomertype 实例对象
     * @return 影响行数
     */
    int insert(SmtCustomertype smtCustomertype);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtCustomertype> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SmtCustomertype> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SmtCustomertype> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SmtCustomertype> entities);

    /**
     * 修改数据
     *
     * @param smtCustomertype 实例对象
     * @return 影响行数
     */
    int update(SmtCustomertype smtCustomertype);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}
