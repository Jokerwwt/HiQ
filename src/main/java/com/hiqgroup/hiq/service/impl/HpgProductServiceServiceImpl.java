package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HpgProductServiceDao;
import com.hiqgroup.hiq.entity.HpgProductService;
import com.hiqgroup.hiq.service.HpgProductServiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品与服务(HpgProductService)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-11-16 22:12:03
 */
@Service("hpgProductServiceService")
public class HpgProductServiceServiceImpl implements HpgProductServiceService {
    @Resource
    private HpgProductServiceDao hpgProductServiceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HpgProductService queryById(String id) {
        return this.hpgProductServiceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hpgProductService 实例对象
     * @param offset            查询起始位置
     * @param limit             查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgProductService> queryAllByLimit(HpgProductService hpgProductService, int offset, int limit, String orderby, String asc) {
        return this.hpgProductServiceDao.queryAllByLimit(hpgProductService, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgProductService 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgProductService> queryAll(HpgProductService hpgProductService) {
        return this.hpgProductServiceDao.queryAll(hpgProductService);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgProductService 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgProductService> queryAllLike(HpgProductService hpgProductService) {
        return this.hpgProductServiceDao.queryAllLike(hpgProductService);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgProductService 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgProductService> queryAllWhereOr(HpgProductService hpgProductService) {
        return this.hpgProductServiceDao.queryAllWhereOr(hpgProductService);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount() {
        return this.hpgProductServiceDao.getCount();
    }

    /**
     * 新增数据
     *
     * @param hpgProductService 实例对象
     * @return 实例对象
     */
    @Override
    public HpgProductService insert(HpgProductService hpgProductService) {
        this.hpgProductServiceDao.insert(hpgProductService);
        return hpgProductService;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgProductServices List<HpgProductService> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HpgProductService> hpgProductServices) {
        return this.hpgProductServiceDao.insertBatch(hpgProductServices);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgProductServices List<HpgProductService> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HpgProductService> hpgProductServices) {
        return this.hpgProductServiceDao.insertOrUpdateBatch(hpgProductServices);
    }

    /**
     * 修改数据
     *
     * @param hpgProductService 实例对象
     * @return 实例对象
     */
    @Override
    public HpgProductService update(HpgProductService hpgProductService) {
        this.hpgProductServiceDao.update(hpgProductService);
        return this.queryById(hpgProductService.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hpgProductServiceDao.deleteById(id) > 0;
    }
}
