package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtUserroleDao;
import com.hiqgroup.hiq.entity.SmtUserrole;
import com.hiqgroup.hiq.service.SmtUserroleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色表(SmtUserrole)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:52
 */
@Service("smtUserroleService")
public class SmtUserroleServiceImpl implements SmtUserroleService {
    @Resource
    private SmtUserroleDao smtUserroleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public SmtUserrole queryById(String userid) {
        return this.smtUserroleDao.queryById(userid);
    }

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param orderby 排序字段
     * @param asc     顺序倒序
     * @return 对象列表
     */
    @Override
    public List<SmtUserrole> queryAllByLimit(int offset, int limit, String orderby, String asc) {
        return this.smtUserroleDao.queryAllByLimit(offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserrole 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUserrole> queryAll(SmtUserrole smtUserrole) {
        return this.smtUserroleDao.queryAll(smtUserrole);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserrole 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUserrole> queryAllLike(SmtUserrole smtUserrole) {
        return this.smtUserroleDao.queryAllLike(smtUserrole);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserrole 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUserrole> queryAllWhereOr(SmtUserrole smtUserrole) {
        return this.smtUserroleDao.queryAllWhereOr(smtUserrole);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount() {
        return this.smtUserroleDao.getCount();
    }

    /**
     * 新增数据
     *
     * @param smtUserrole 实例对象
     * @return 实例对象
     */
    @Override
    public SmtUserrole insert(SmtUserrole smtUserrole) {
        this.smtUserroleDao.insert(smtUserrole);
        return smtUserrole;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtUserroles List<SmtUserrole> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtUserrole> smtUserroles) {
        return this.smtUserroleDao.insertBatch(smtUserroles);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtUserroles List<SmtUserrole> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtUserrole> smtUserroles) {
        return this.smtUserroleDao.insertOrUpdateBatch(smtUserroles);
    }

    /**
     * 修改数据
     *
     * @param smtUserrole 实例对象
     * @return 实例对象
     */
    @Override
    public SmtUserrole update(SmtUserrole smtUserrole) {
        this.smtUserroleDao.update(smtUserrole);
        return this.queryById(smtUserrole.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userid) {
        return this.smtUserroleDao.deleteById(userid) > 0;
    }
}
