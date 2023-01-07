package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtRolerightDao;
import com.hiqgroup.hiq.entity.SmtRoleright;
import com.hiqgroup.hiq.service.SmtRolerightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限表(SmtRoleright)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:39
 */
@Service("smtRolerightService")
public class SmtRolerightServiceImpl implements SmtRolerightService {
    @Resource
    private SmtRolerightDao smtRolerightDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleid 主键
     * @return 实例对象
     */
    @Override
    public SmtRoleright queryById(String roleid) {
        return this.smtRolerightDao.queryById(roleid);
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
    public List<SmtRoleright> queryAllByLimit(int offset, int limit, String orderby, String asc) {
        return this.smtRolerightDao.queryAllByLimit(offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRoleright 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtRoleright> queryAll(SmtRoleright smtRoleright) {
        return this.smtRolerightDao.queryAll(smtRoleright);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRoleright 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtRoleright> queryAllLike(SmtRoleright smtRoleright) {
        return this.smtRolerightDao.queryAllLike(smtRoleright);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtRoleright 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtRoleright> queryAllWhereOr(SmtRoleright smtRoleright) {
        return this.smtRolerightDao.queryAllWhereOr(smtRoleright);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount() {
        return this.smtRolerightDao.getCount();
    }

    /**
     * 新增数据
     *
     * @param smtRoleright 实例对象
     * @return 实例对象
     */
    @Override
    public SmtRoleright insert(SmtRoleright smtRoleright) {
        this.smtRolerightDao.insert(smtRoleright);
        return smtRoleright;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtRolerights List<SmtRoleright> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtRoleright> smtRolerights) {
        return this.smtRolerightDao.insertBatch(smtRolerights);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtRolerights List<SmtRoleright> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtRoleright> smtRolerights) {
        return this.smtRolerightDao.insertOrUpdateBatch(smtRolerights);
    }

    /**
     * 修改数据
     *
     * @param smtRoleright 实例对象
     * @return 实例对象
     */
    @Override
    public SmtRoleright update(SmtRoleright smtRoleright) {
        this.smtRolerightDao.update(smtRoleright);
        return this.queryById(smtRoleright.getRoleid());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String roleid) {
        return this.smtRolerightDao.deleteById(roleid) > 0;
    }
}
