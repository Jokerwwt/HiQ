package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HpgIntroductionDao;
import com.hiqgroup.hiq.entity.HpgIntroduction;
import com.hiqgroup.hiq.service.HpgIntroductionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 平台介绍(HpgIntroduction)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-11-16 22:12:01
 */
@Service("hpgIntroductionService")
public class HpgIntroductionServiceImpl implements HpgIntroductionService {
    @Resource
    private HpgIntroductionDao hpgIntroductionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HpgIntroduction queryById(String id) {
        return this.hpgIntroductionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hpgIntroduction 实例对象
     * @param offset          查询起始位置
     * @param limit           查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgIntroduction> queryAllByLimit(HpgIntroduction hpgIntroduction, int offset, int limit, String orderby, String asc) {
        return this.hpgIntroductionDao.queryAllByLimit(hpgIntroduction, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgIntroduction 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgIntroduction> queryAll(HpgIntroduction hpgIntroduction) {
        return this.hpgIntroductionDao.queryAll(hpgIntroduction);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgIntroduction 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgIntroduction> queryAllLike(HpgIntroduction hpgIntroduction) {
        return this.hpgIntroductionDao.queryAllLike(hpgIntroduction);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgIntroduction 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgIntroduction> queryAllWhereOr(HpgIntroduction hpgIntroduction) {
        return this.hpgIntroductionDao.queryAllWhereOr(hpgIntroduction);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount() {
        return this.hpgIntroductionDao.getCount();
    }

    /**
     * 新增数据
     *
     * @param hpgIntroduction 实例对象
     * @return 实例对象
     */
    @Override
    public HpgIntroduction insert(HpgIntroduction hpgIntroduction) {
        this.hpgIntroductionDao.insert(hpgIntroduction);
        return hpgIntroduction;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgIntroductions List<HpgIntroduction> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HpgIntroduction> hpgIntroductions) {
        return this.hpgIntroductionDao.insertBatch(hpgIntroductions);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgIntroductions List<HpgIntroduction> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HpgIntroduction> hpgIntroductions) {
        return this.hpgIntroductionDao.insertOrUpdateBatch(hpgIntroductions);
    }

    /**
     * 修改数据
     *
     * @param hpgIntroduction 实例对象
     * @return 实例对象
     */
    @Override
    public HpgIntroduction update(HpgIntroduction hpgIntroduction) {
        this.hpgIntroductionDao.update(hpgIntroduction);
        return this.queryById(hpgIntroduction.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hpgIntroductionDao.deleteById(id) > 0;
    }
}
