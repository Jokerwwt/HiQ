package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtCompanyDao;
import com.hiqgroup.hiq.entity.SmtCompany;
import com.hiqgroup.hiq.service.SmtCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 单位资料表(SmtCompany)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:50:35
 */
@Service("smtCompanyService")
public class SmtCompanyServiceImpl implements SmtCompanyService {
    @Resource
    private SmtCompanyDao smtCompanyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SmtCompany queryById(String id) {
        return this.smtCompanyDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param smtCompany 实例对象
     * @param offset     查询起始位置
     * @param limit      查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtCompany> queryAllByLimit(SmtCompany smtCompany, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtCompanyDao.queryAllByLimit(smtCompany, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param smtCompany 实例对象
     * @param offset     查询起始位置
     * @param limit      查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtCompany> queryNotEqualAllByLimit(SmtCompany smtCompany, int offset, int limit, String orderby, String asc) {
        return this.smtCompanyDao.queryNotEqualAllByLimit(smtCompany, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtCompany> queryAll(SmtCompany smtCompany) {
        return this.smtCompanyDao.queryAll(smtCompany);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtCompany> queryAllLike(SmtCompany smtCompany) {
        return this.smtCompanyDao.queryAllLike(smtCompany);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtCompany 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtCompany> queryAllWhereOr(SmtCompany smtCompany) {
        return this.smtCompanyDao.queryAllWhereOr(smtCompany);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(SmtCompany smtCompany, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtCompanyDao.getCount(smtCompany, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(SmtCompany smtCompany) {
        return this.smtCompanyDao.getCountByNotEqual(smtCompany);
    }

    /**
     * 新增数据
     *
     * @param smtCompany 实例对象
     * @return 实例对象
     */
    @Override
    public SmtCompany insert(SmtCompany smtCompany) {
        this.smtCompanyDao.insert(smtCompany);
        return smtCompany;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtCompanys List<SmtCompany> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtCompany> smtCompanys) {
        return this.smtCompanyDao.insertBatch(smtCompanys);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtCompanys List<SmtCompany> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtCompany> smtCompanys) {
        return this.smtCompanyDao.insertOrUpdateBatch(smtCompanys);
    }

    /**
     * 修改数据
     *
     * @param smtCompany 实例对象
     * @return 实例对象
     */
    @Override
    public SmtCompany update(SmtCompany smtCompany) {
        this.smtCompanyDao.update(smtCompany);
        return this.queryById(smtCompany.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.smtCompanyDao.deleteById(id) > 0;
    }
}
