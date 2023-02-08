package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtUserDao;
import com.hiqgroup.hiq.entity.SmtUser;
import com.hiqgroup.hiq.service.SmtUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户资料表(SmtUser)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:58:02
 */
@Service("smtUserService")
public class SmtUserServiceImpl implements SmtUserService {
    @Resource
    private SmtUserDao smtUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SmtUser queryById(String id) {
        return this.smtUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param smtUser 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtUser> queryAllByLimit(SmtUser smtUser, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtUserDao.queryAllByLimit(smtUser, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param smtUser 实例对象
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtUser> queryNotEqualAllByLimit(SmtUser smtUser, int offset, int limit, String orderby, String asc) {
        return this.smtUserDao.queryNotEqualAllByLimit(smtUser, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUser 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUser> queryAll(SmtUser smtUser) {
        return this.smtUserDao.queryAll(smtUser);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUser 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUser> queryAllLike(SmtUser smtUser) {
        return this.smtUserDao.queryAllLike(smtUser);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUser 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUser> queryAllWhereOr(SmtUser smtUser) {
        return this.smtUserDao.queryAllWhereOr(smtUser);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(SmtUser smtUser, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.smtUserDao.getCount(smtUser, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(SmtUser smtUser) {
        return this.smtUserDao.getCountByNotEqual(smtUser);
    }

    /**
     * 新增数据
     *
     * @param smtUser 实例对象
     * @return 实例对象
     */
    @Override
    public SmtUser insert(SmtUser smtUser) {
        this.smtUserDao.insert(smtUser);
        return smtUser;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtUsers List<SmtUser> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtUser> smtUsers) {
        return this.smtUserDao.insertBatch(smtUsers);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtUsers List<SmtUser> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtUser> smtUsers) {
        return this.smtUserDao.insertOrUpdateBatch(smtUsers);
    }

    /**
     * 修改数据
     *
     * @param smtUser 实例对象
     * @return 实例对象
     */
    @Override
    public SmtUser update(SmtUser smtUser) {
        this.smtUserDao.update(smtUser);
        return this.queryById(smtUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.smtUserDao.deleteById(id) > 0;
    }
}
