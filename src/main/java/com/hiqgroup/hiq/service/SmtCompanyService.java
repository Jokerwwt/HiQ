package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtCompany;

import java.util.List;
import java.util.Map;

/**
 * 单位资料表(SmtCompany)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 09:50:35
 */
public interface SmtCompanyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtCompany queryById(String id);

    /**
     * 查询多条数据
     *
     * @param smtCompany 实例对象
     * @param offset     查询起始位置
     * @param limit      查询条数
     * @return 对象列表
     */
    List<SmtCompany> queryAllByLimit(SmtCompany smtCompany, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param smtCompany 实例对象
     * @param offset     查询起始位置
     * @param limit      查询条数
     * @return 对象列表
     */
    List<SmtCompany> queryNotEqualAllByLimit(SmtCompany smtCompany, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    List<SmtCompany> queryAll(SmtCompany smtCompany);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    List<SmtCompany> queryAllLike(SmtCompany smtCompany);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    List<SmtCompany> queryAllWhereOr(SmtCompany smtCompany);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(SmtCompany smtCompany, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(SmtCompany smtCompany);

    /**
     * 新增数据
     *
     * @param smtCompany 实例对象
     * @return 实例对象
     */
    SmtCompany insert(SmtCompany smtCompany);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtCompanys List<SmtCompany> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtCompany> smtCompanys);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtCompanys List<SmtCompany> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtCompany> smtCompanys);

    /**
     * 修改数据
     *
     * @param smtCompany 实例对象
     * @return 实例对象
     */
    SmtCompany update(SmtCompany smtCompany);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
