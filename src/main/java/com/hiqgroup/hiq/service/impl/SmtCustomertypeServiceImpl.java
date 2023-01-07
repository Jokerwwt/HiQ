package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtCustomertypeDao;
import com.hiqgroup.hiq.entity.SmtCustomertype;
import com.hiqgroup.hiq.service.SmtCustomertypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * A类，业务员自己独立获得的
 * B类，公司推送，但业务员重点推进的
 * C类，公司推送的成熟客户，业务员(SmtCustomertype)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:52:41
 */
@Service("smtCustomertypeService")
public class SmtCustomertypeServiceImpl implements SmtCustomertypeService {
    @Resource
    private SmtCustomertypeDao smtCustomertypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SmtCustomertype queryById(String id) {
        return this.smtCustomertypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param smtCustomertype 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtCustomertype> queryAllByLimit(SmtCustomertype smtCustomertype, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtCustomertypeDao.queryAllByLimit(smtCustomertype, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param smtCustomertype 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtCustomertype> queryNotEqualAllByLimit(SmtCustomertype smtCustomertype, int offset, int limit, String orderby, String asc) {
        return this.smtCustomertypeDao.queryNotEqualAllByLimit(smtCustomertype, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCustomertype 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtCustomertype> queryAll(SmtCustomertype smtCustomertype) {
        return this.smtCustomertypeDao.queryAll(smtCustomertype);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCustomertype 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtCustomertype> queryAllLike(SmtCustomertype smtCustomertype) {
        return this.smtCustomertypeDao.queryAllLike(smtCustomertype);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCustomertype 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtCustomertype> queryAllWhereOr(SmtCustomertype smtCustomertype) {
        return this.smtCustomertypeDao.queryAllWhereOr(smtCustomertype);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(SmtCustomertype smtCustomertype, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtCustomertypeDao.getCount(smtCustomertype, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(SmtCustomertype smtCustomertype) {
        return this.smtCustomertypeDao.getCountByNotEqual(smtCustomertype);
    }

    /**
     * 新增数据
     *
     * @param smtCustomertype 实例对象
     * @return 实例对象
     */
    @Override
    public SmtCustomertype insert(SmtCustomertype smtCustomertype) {
        this.smtCustomertypeDao.insert(smtCustomertype);
        return smtCustomertype;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtCustomertypes List<SmtCustomertype> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtCustomertype> smtCustomertypes) {
        return this.smtCustomertypeDao.insertBatch(smtCustomertypes);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtCustomertypes List<SmtCustomertype> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtCustomertype> smtCustomertypes) {
        return this.smtCustomertypeDao.insertOrUpdateBatch(smtCustomertypes);
    }

    /**
     * 修改数据
     *
     * @param smtCustomertype 实例对象
     * @return 实例对象
     */
    @Override
    public SmtCustomertype update(SmtCustomertype smtCustomertype) {
        this.smtCustomertypeDao.update(smtCustomertype);
        return this.queryById(smtCustomertype.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.smtCustomertypeDao.deleteById(id) > 0;
    }
}
