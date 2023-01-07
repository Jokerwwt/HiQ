package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HqtCheckpriceDao;
import com.hiqgroup.hiq.entity.HqtCheckprice;
import com.hiqgroup.hiq.service.HqtCheckpriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 检测报价表(HqtCheckprice)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 11:08:14
 */
@Service("hqtCheckpriceService")
public class HqtCheckpriceServiceImpl implements HqtCheckpriceService {
    @Resource
    private HqtCheckpriceDao hqtCheckpriceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqtCheckprice queryById(String id) {
        return this.hqtCheckpriceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hqtCheckprice 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtCheckprice> queryAllByLimit(HqtCheckprice hqtCheckprice, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hqtCheckpriceDao.queryAllByLimit(hqtCheckprice, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param hqtCheckprice 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtCheckprice> queryNotEqualAllByLimit(HqtCheckprice hqtCheckprice, int offset, int limit, String orderby, String asc) {
        return this.hqtCheckpriceDao.queryNotEqualAllByLimit(hqtCheckprice, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckprice 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckprice> queryAll(HqtCheckprice hqtCheckprice) {
        return this.hqtCheckpriceDao.queryAll(hqtCheckprice);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckprice 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckprice> queryAllLike(HqtCheckprice hqtCheckprice) {
        return this.hqtCheckpriceDao.queryAllLike(hqtCheckprice);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckprice 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckprice> queryAllWhereOr(HqtCheckprice hqtCheckprice) {
        return this.hqtCheckpriceDao.queryAllWhereOr(hqtCheckprice);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HqtCheckprice hqtCheckprice, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hqtCheckpriceDao.getCount(hqtCheckprice, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(HqtCheckprice hqtCheckprice) {
        return this.hqtCheckpriceDao.getCountByNotEqual(hqtCheckprice);
    }

    /**
     * 新增数据
     *
     * @param hqtCheckprice 实例对象
     * @return 实例对象
     */
    @Override
    public HqtCheckprice insert(HqtCheckprice hqtCheckprice) {
        this.hqtCheckpriceDao.insert(hqtCheckprice);
        return hqtCheckprice;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckprices List<HqtCheckprice> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HqtCheckprice> hqtCheckprices) {
        return this.hqtCheckpriceDao.insertBatch(hqtCheckprices);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckprices List<HqtCheckprice> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HqtCheckprice> hqtCheckprices) {
        return this.hqtCheckpriceDao.insertOrUpdateBatch(hqtCheckprices);
    }

    /**
     * 修改数据
     *
     * @param hqtCheckprice 实例对象
     * @return 实例对象
     */
    @Override
    public HqtCheckprice update(HqtCheckprice hqtCheckprice) {
        this.hqtCheckpriceDao.update(hqtCheckprice);
        return this.queryById(hqtCheckprice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hqtCheckpriceDao.deleteById(id) > 0;
    }
}
