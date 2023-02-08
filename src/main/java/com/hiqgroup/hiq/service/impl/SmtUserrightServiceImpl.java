package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtUserrightDao;
import com.hiqgroup.hiq.entity.SmtUserright;
import com.hiqgroup.hiq.service.SmtUserrightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户权限表(SmtUserright)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:49
 */
@Service("smtUserrightService")
public class SmtUserrightServiceImpl implements SmtUserrightService {
    @Resource
    private SmtUserrightDao smtUserrightDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public SmtUserright queryById(String userid) {
        return this.smtUserrightDao.queryById(userid);
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
    public List<SmtUserright> queryAllByLimit(int offset, int limit, String orderby, String asc) {
        return this.smtUserrightDao.queryAllByLimit(offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserright 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUserright> queryAll(SmtUserright smtUserright) {
        return this.smtUserrightDao.queryAll(smtUserright);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserright 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUserright> queryAllLike(SmtUserright smtUserright) {
        return this.smtUserrightDao.queryAllLike(smtUserright);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtUserright 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtUserright> queryAllWhereOr(SmtUserright smtUserright) {
        return this.smtUserrightDao.queryAllWhereOr(smtUserright);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount() {
        return this.smtUserrightDao.getCount();
    }

    /**
     * 新增数据
     *
     * @param smtUserright 实例对象
     * @return 实例对象
     */
    @Override
    public SmtUserright insert(SmtUserright smtUserright) {
        this.smtUserrightDao.insert(smtUserright);
        return smtUserright;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtUserrights List<SmtUserright> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtUserright> smtUserrights) {
        return this.smtUserrightDao.insertBatch(smtUserrights);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtUserrights List<SmtUserright> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtUserright> smtUserrights) {
        return this.smtUserrightDao.insertOrUpdateBatch(smtUserrights);
    }

    /**
     * 修改数据
     *
     * @param smtUserright 实例对象
     * @return 实例对象
     */
    @Override
    public SmtUserright update(SmtUserright smtUserright) {
        this.smtUserrightDao.update(smtUserright);
        return this.queryById(smtUserright.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userid) {
        return this.smtUserrightDao.deleteById(userid) > 0;
    }
}
