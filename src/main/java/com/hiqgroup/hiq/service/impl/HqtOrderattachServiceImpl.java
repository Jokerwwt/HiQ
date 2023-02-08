package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HqtOrderattachDao;
import com.hiqgroup.hiq.entity.HqtOrderattach;
import com.hiqgroup.hiq.service.HqtOrderattachService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单据附件表(HqtOrderattach)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-12-22 21:46:16
 */
@Service("hqtOrderattachService")
public class HqtOrderattachServiceImpl implements HqtOrderattachService {
    @Resource
    private HqtOrderattachDao hqtOrderattachDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqtOrderattach queryById(String id) {
        return this.hqtOrderattachDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hqtOrderattach 实例对象
     * @param offset         查询起始位置
     * @param limit          查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtOrderattach> queryAllByLimit(HqtOrderattach hqtOrderattach, int offset, int limit, String orderby, String asc) {
        return this.hqtOrderattachDao.queryAllByLimit(hqtOrderattach, offset, limit, orderby, asc);
    }

    /**
     * 查询不等于多条数据
     *
     * @param hqtOrderattach 实例对象
     * @param offset         查询起始位置
     * @param limit          查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtOrderattach> queryNotEqualAllByLimit(HqtOrderattach hqtOrderattach, int offset, int limit, String orderby, String asc) {
        return this.hqtOrderattachDao.queryNotEqualAllByLimit(hqtOrderattach, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtOrderattach 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtOrderattach> queryAll(HqtOrderattach hqtOrderattach) {
        return this.hqtOrderattachDao.queryAll(hqtOrderattach);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtOrderattach 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtOrderattach> queryAllLike(HqtOrderattach hqtOrderattach) {
        return this.hqtOrderattachDao.queryAllLike(hqtOrderattach);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtOrderattach 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtOrderattach> queryAllWhereOr(HqtOrderattach hqtOrderattach) {
        return this.hqtOrderattachDao.queryAllWhereOr(hqtOrderattach);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HqtOrderattach hqtOrderattach) {
        return this.hqtOrderattachDao.getCount(hqtOrderattach);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(HqtOrderattach hqtOrderattach) {
        return this.hqtOrderattachDao.getCountByNotEqual(hqtOrderattach);
    }

    /**
     * 新增数据
     *
     * @param hqtOrderattach 实例对象
     * @return 实例对象
     */
    @Override
    public HqtOrderattach insert(HqtOrderattach hqtOrderattach) {
        this.hqtOrderattachDao.insert(hqtOrderattach);
        return hqtOrderattach;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtOrderattachs List<HqtOrderattach> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HqtOrderattach> hqtOrderattachs) {
        return this.hqtOrderattachDao.insertBatch(hqtOrderattachs);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtOrderattachs List<HqtOrderattach> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HqtOrderattach> hqtOrderattachs) {
        return this.hqtOrderattachDao.insertOrUpdateBatch(hqtOrderattachs);
    }

    /**
     * 修改数据
     *
     * @param hqtOrderattach 实例对象
     * @return 实例对象
     */
    @Override
    public HqtOrderattach update(HqtOrderattach hqtOrderattach) {
        this.hqtOrderattachDao.update(hqtOrderattach);
        return this.queryById(hqtOrderattach.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hqtOrderattachDao.deleteById(id) > 0;
    }
}
