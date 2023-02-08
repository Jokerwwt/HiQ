package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtDeptDao;
import com.hiqgroup.hiq.entity.SmtDept;
import com.hiqgroup.hiq.service.SmtDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 单位部门资料表(SmtDept)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:53:50
 */
@Service("smtDeptService")
public class SmtDeptServiceImpl implements SmtDeptService {
    @Resource
    private SmtDeptDao smtDeptDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SmtDept queryById(String id) {
        return this.smtDeptDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param smtDept 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtDept> queryAllByLimit(SmtDept smtDept, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtDeptDao.queryAllByLimit(smtDept, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param smtDept 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtDept> queryNotEqualAllByLimit(SmtDept smtDept, int offset, int limit, String orderby, String asc) {
        return this.smtDeptDao.queryNotEqualAllByLimit(smtDept, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtDept 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtDept> queryAll(SmtDept smtDept) {
        return this.smtDeptDao.queryAll(smtDept);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtDept 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtDept> queryAllLike(SmtDept smtDept) {
        return this.smtDeptDao.queryAllLike(smtDept);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtDept 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtDept> queryAllWhereOr(SmtDept smtDept) {
        return this.smtDeptDao.queryAllWhereOr(smtDept);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(SmtDept smtDept, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtDeptDao.getCount(smtDept, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(SmtDept smtDept) {
        return this.smtDeptDao.getCountByNotEqual(smtDept);
    }

    /**
     * 新增数据
     *
     * @param smtDept 实例对象
     * @return 实例对象
     */
    @Override
    public SmtDept insert(SmtDept smtDept) {
        this.smtDeptDao.insert(smtDept);
        return smtDept;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtDepts List<SmtDept> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtDept> smtDepts) {
        return this.smtDeptDao.insertBatch(smtDepts);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtDepts List<SmtDept> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtDept> smtDepts) {
        return this.smtDeptDao.insertOrUpdateBatch(smtDepts);
    }

    /**
     * 修改数据
     *
     * @param smtDept 实例对象
     * @return 实例对象
     */
    @Override
    public SmtDept update(SmtDept smtDept) {
        this.smtDeptDao.update(smtDept);
        return this.queryById(smtDept.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.smtDeptDao.deleteById(id) > 0;
    }
}
