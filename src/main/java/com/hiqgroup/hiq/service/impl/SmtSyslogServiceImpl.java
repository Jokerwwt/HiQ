package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtSyslogDao;
import com.hiqgroup.hiq.entity.SmtSyslog;
import com.hiqgroup.hiq.service.SmtSyslogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统日志表(SmtSyslog)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:56:54
 */
@Service("smtSyslogService")
public class SmtSyslogServiceImpl implements SmtSyslogService {
    @Resource
    private SmtSyslogDao smtSyslogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SmtSyslog queryById(String id) {
        return this.smtSyslogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param smtSyslog 实例对象
     * @param offset    查询起始位置
     * @param limit     查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtSyslog> queryAllByLimit(SmtSyslog smtSyslog, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtSyslogDao.queryAllByLimit(smtSyslog, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param smtSyslog 实例对象
     * @param offset    查询起始位置
     * @param limit     查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtSyslog> queryNotEqualAllByLimit(SmtSyslog smtSyslog, int offset, int limit, String orderby, String asc) {
        return this.smtSyslogDao.queryNotEqualAllByLimit(smtSyslog, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtSyslog 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtSyslog> queryAll(SmtSyslog smtSyslog) {
        return this.smtSyslogDao.queryAll(smtSyslog);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtSyslog 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtSyslog> queryAllLike(SmtSyslog smtSyslog) {
        return this.smtSyslogDao.queryAllLike(smtSyslog);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtSyslog 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtSyslog> queryAllWhereOr(SmtSyslog smtSyslog) {
        return this.smtSyslogDao.queryAllWhereOr(smtSyslog);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(SmtSyslog smtSyslog, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtSyslogDao.getCount(smtSyslog, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(SmtSyslog smtSyslog) {
        return this.smtSyslogDao.getCountByNotEqual(smtSyslog);
    }

    /**
     * 新增数据
     *
     * @param smtSyslog 实例对象
     * @return 实例对象
     */
    @Override
    public SmtSyslog insert(SmtSyslog smtSyslog) {
        this.smtSyslogDao.insert(smtSyslog);
        return smtSyslog;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtSyslogs List<SmtSyslog> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtSyslog> smtSyslogs) {
        return this.smtSyslogDao.insertBatch(smtSyslogs);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtSyslogs List<SmtSyslog> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtSyslog> smtSyslogs) {
        return this.smtSyslogDao.insertOrUpdateBatch(smtSyslogs);
    }

    /**
     * 修改数据
     *
     * @param smtSyslog 实例对象
     * @return 实例对象
     */
    @Override
    public SmtSyslog update(SmtSyslog smtSyslog) {
        this.smtSyslogDao.update(smtSyslog);
        return this.queryById(smtSyslog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.smtSyslogDao.deleteById(id) > 0;
    }
}
