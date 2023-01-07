package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtCustomertype;

import java.util.List;
import java.util.Map;

/**
 * A类，业务员自己独立获得的
 * B类，公司推送，但业务员重点推进的
 * C类，公司推送的成熟客户，业务员(SmtCustomertype)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 09:52:41
 */
public interface SmtCustomertypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtCustomertype queryById(String id);

    /**
     * 查询多条数据
     *
     * @param smtCustomertype 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    List<SmtCustomertype> queryAllByLimit(SmtCustomertype smtCustomertype, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param smtCustomertype 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    List<SmtCustomertype> queryNotEqualAllByLimit(SmtCustomertype smtCustomertype, int offset, int limit, String orderby, String asc);

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
    int getCount(SmtCustomertype smtCustomertype, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

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
     * @return 实例对象
     */
    SmtCustomertype insert(SmtCustomertype smtCustomertype);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtCustomertypes List<SmtCustomertype> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtCustomertype> smtCustomertypes);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtCustomertypes List<SmtCustomertype> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtCustomertype> smtCustomertypes);

    /**
     * 修改数据
     *
     * @param smtCustomertype 实例对象
     * @return 实例对象
     */
    SmtCustomertype update(SmtCustomertype smtCustomertype);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
