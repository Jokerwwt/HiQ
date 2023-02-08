package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtRoleDao;
import com.hiqgroup.hiq.entity.SmtRole;
import com.hiqgroup.hiq.service.SmtRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统角色表(SmtRole)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:55:09
 */
@Service("smtRoleService")
public class SmtRoleServiceImpl implements SmtRoleService {
    @Resource
    private SmtRoleDao smtRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SmtRole queryById(String id) {
        return this.smtRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param smtRole 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtRole> queryAllByLimit(SmtRole smtRole, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtRoleDao.queryAllByLimit(smtRole, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param smtRole 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtRole> queryNotEqualAllByLimit(SmtRole smtRole, int offset, int limit, String orderby, String asc) {
        return this.smtRoleDao.queryNotEqualAllByLimit(smtRole, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRole 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtRole> queryAll(SmtRole smtRole) {
        return this.smtRoleDao.queryAll(smtRole);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRole 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtRole> queryAllLike(SmtRole smtRole) {
        return this.smtRoleDao.queryAllLike(smtRole);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRole 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtRole> queryAllWhereOr(SmtRole smtRole) {
        return this.smtRoleDao.queryAllWhereOr(smtRole);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(SmtRole smtRole, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtRoleDao.getCount(smtRole, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(SmtRole smtRole) {
        return this.smtRoleDao.getCountByNotEqual(smtRole);
    }

    /**
     * 新增数据
     *
     * @param smtRole 实例对象
     * @return 实例对象
     */
    @Override
    public SmtRole insert(SmtRole smtRole) {
        this.smtRoleDao.insert(smtRole);
        return smtRole;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtRoles List<SmtRole> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtRole> smtRoles) {
        return this.smtRoleDao.insertBatch(smtRoles);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtRoles List<SmtRole> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtRole> smtRoles) {
        return this.smtRoleDao.insertOrUpdateBatch(smtRoles);
    }

    /**
     * 修改数据
     *
     * @param smtRole 实例对象
     * @return 实例对象
     */
    @Override
    public SmtRole update(SmtRole smtRole) {
        this.smtRoleDao.update(smtRole);
        return this.queryById(smtRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.smtRoleDao.deleteById(id) > 0;
    }
}
