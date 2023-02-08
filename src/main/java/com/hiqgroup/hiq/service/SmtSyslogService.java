package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.SmtSyslog;

import java.util.List;
import java.util.Map;

/**
 * 系统日志表(SmtSyslog)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 09:56:54
 */
public interface SmtSyslogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SmtSyslog queryById(String id);

    /**
     * 查询多条数据
     *
     * @param smtSyslog 实例对象
     * @param offset    查询起始位置
     * @param limit     查询条数
     * @return 对象列表
     */
    List<SmtSyslog> queryAllByLimit(SmtSyslog smtSyslog, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param smtSyslog 实例对象
     * @param offset    查询起始位置
     * @param limit     查询条数
     * @return 对象列表
     */
    List<SmtSyslog> queryNotEqualAllByLimit(SmtSyslog smtSyslog, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtSyslog 实例对象
     * @return 对象列表
     */
    List<SmtSyslog> queryAll(SmtSyslog smtSyslog);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtSyslog 实例对象
     * @return 对象列表
     */
    List<SmtSyslog> queryAllLike(SmtSyslog smtSyslog);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtSyslog 实例对象
     * @return 对象列表
     */
    List<SmtSyslog> queryAllWhereOr(SmtSyslog smtSyslog);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(SmtSyslog smtSyslog, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(SmtSyslog smtSyslog);

    /**
     * 新增数据
     *
     * @param smtSyslog 实例对象
     * @return 实例对象
     */
    SmtSyslog insert(SmtSyslog smtSyslog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtSyslogs List<SmtSyslog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<SmtSyslog> smtSyslogs);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtSyslogs List<SmtSyslog> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<SmtSyslog> smtSyslogs);

    /**
     * 修改数据
     *
     * @param smtSyslog 实例对象
     * @return 实例对象
     */
    SmtSyslog update(SmtSyslog smtSyslog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
