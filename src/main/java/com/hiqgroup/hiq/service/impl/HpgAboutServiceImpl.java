package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HpgAboutDao;
import com.hiqgroup.hiq.entity.HpgAbout;
import com.hiqgroup.hiq.service.HpgAboutService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 关于我们(HpgAbout)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-12-14 16:10:28
 */
@Service("hpgAboutService")
public class HpgAboutServiceImpl implements HpgAboutService {
    @Resource
    private HpgAboutDao hpgAboutDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HpgAbout queryById(String id) {
        return this.hpgAboutDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hpgAbout 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgAbout> queryAllByLimit(HpgAbout hpgAbout, int offset, int limit, String orderby, String asc) {
        return this.hpgAboutDao.queryAllByLimit(hpgAbout, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgAbout 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgAbout> queryAll(HpgAbout hpgAbout) {
        return this.hpgAboutDao.queryAll(hpgAbout);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgAbout 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgAbout> queryAllLike(HpgAbout hpgAbout) {
        return this.hpgAboutDao.queryAllLike(hpgAbout);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgAbout 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgAbout> queryAllWhereOr(HpgAbout hpgAbout) {
        return this.hpgAboutDao.queryAllWhereOr(hpgAbout);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HpgAbout hpgAbout) {
        return this.hpgAboutDao.getCount(hpgAbout);
    }

    /**
     * 新增数据
     *
     * @param hpgAbout 实例对象
     * @return 实例对象
     */
    @Override
    public HpgAbout insert(HpgAbout hpgAbout) {
        this.hpgAboutDao.insert(hpgAbout);
        return hpgAbout;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgAbouts List<HpgAbout> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HpgAbout> hpgAbouts) {
        return this.hpgAboutDao.insertBatch(hpgAbouts);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgAbouts List<HpgAbout> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HpgAbout> hpgAbouts) {
        return this.hpgAboutDao.insertOrUpdateBatch(hpgAbouts);
    }

    /**
     * 修改数据
     *
     * @param hpgAbout 实例对象
     * @return 实例对象
     */
    @Override
    public HpgAbout update(HpgAbout hpgAbout) {
        this.hpgAboutDao.update(hpgAbout);
        return this.queryById(hpgAbout.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hpgAboutDao.deleteById(id) > 0;
    }
}
