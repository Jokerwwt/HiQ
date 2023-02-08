package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtParamssetDao;
import com.hiqgroup.hiq.entity.SmtParamsset;
import com.hiqgroup.hiq.service.SmtParamssetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统设置表(SmtParamsset)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-12-10 16:07:14
 */
@Service("smtParamssetService")
public class SmtParamssetServiceImpl implements SmtParamssetService {
    @Resource
    private SmtParamssetDao smtParamssetDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SmtParamsset queryById(String id) {
        return this.smtParamssetDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param smtParamsset 实例对象
     * @param offset       查询起始位置
     * @param limit        查询条数
     * @return 对象列表
     */
    @Override
    public List<SmtParamsset> queryAllByLimit(SmtParamsset smtParamsset, int offset, int limit, String orderby, String asc) {
        return this.smtParamssetDao.queryAllByLimit(smtParamsset, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtParamsset 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtParamsset> queryAll(SmtParamsset smtParamsset) {
        return this.smtParamssetDao.queryAll(smtParamsset);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtParamsset 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtParamsset> queryAllLike(SmtParamsset smtParamsset) {
        return this.smtParamssetDao.queryAllLike(smtParamsset);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtParamsset 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtParamsset> queryAllWhereOr(SmtParamsset smtParamsset) {
        return this.smtParamssetDao.queryAllWhereOr(smtParamsset);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(SmtParamsset smtParamsset) {
        return this.smtParamssetDao.getCount(smtParamsset);
    }

    /**
     * 新增数据
     *
     * @param smtParamsset 实例对象
     * @return 实例对象
     */
    @Override
    public SmtParamsset insert(SmtParamsset smtParamsset) {
        this.smtParamssetDao.insert(smtParamsset);
        return smtParamsset;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtParamssets List<SmtParamsset> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtParamsset> smtParamssets) {
        return this.smtParamssetDao.insertBatch(smtParamssets);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtParamssets List<SmtParamsset> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtParamsset> smtParamssets) {
        return this.smtParamssetDao.insertOrUpdateBatch(smtParamssets);
    }

    /**
     * 修改数据
     *
     * @param smtParamsset 实例对象
     * @return 实例对象
     */
    @Override
    public SmtParamsset update(SmtParamsset smtParamsset) {
        this.smtParamssetDao.update(smtParamsset);
        return this.queryById(smtParamsset.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.smtParamssetDao.deleteById(id) > 0;
    }
}
