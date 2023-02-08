package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HqtCustomerorderDao;
import com.hiqgroup.hiq.entity.HqtCustomerorder;
import com.hiqgroup.hiq.service.HqtCustomerorderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 委托方订单(HqtCustomerorder)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-05 16:24:30
 */
@Service("hqtCustomerorderService")
public class HqtCustomerorderServiceImpl implements HqtCustomerorderService {
    @Resource
    private HqtCustomerorderDao hqtCustomerorderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqtCustomerorder queryById(String id) {
        return this.hqtCustomerorderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hqtCustomerorder 实例对象
     * @param offset           查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtCustomerorder> queryAllByLimit(HqtCustomerorder hqtCustomerorder, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hqtCustomerorderDao.queryAllByLimit(hqtCustomerorder, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param hqtCustomerorder 实例对象
     * @param offset           查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtCustomerorder> queryNotEqualAllByLimit(HqtCustomerorder hqtCustomerorder, int offset, int limit, String orderby, String asc) {
        return this.hqtCustomerorderDao.queryNotEqualAllByLimit(hqtCustomerorder, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCustomerorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCustomerorder> queryAll(HqtCustomerorder hqtCustomerorder) {
        return this.hqtCustomerorderDao.queryAll(hqtCustomerorder);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCustomerorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCustomerorder> queryAllLike(HqtCustomerorder hqtCustomerorder) {
        return this.hqtCustomerorderDao.queryAllLike(hqtCustomerorder);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCustomerorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCustomerorder> queryAllWhereOr(HqtCustomerorder hqtCustomerorder) {
        return this.hqtCustomerorderDao.queryAllWhereOr(hqtCustomerorder);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HqtCustomerorder hqtCustomerorder, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hqtCustomerorderDao.getCount(hqtCustomerorder, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(HqtCustomerorder hqtCustomerorder) {
        return this.hqtCustomerorderDao.getCountByNotEqual(hqtCustomerorder);
    }

    /**
     * 新增数据
     *
     * @param hqtCustomerorder 实例对象
     * @return 实例对象
     */
    @Override
    public HqtCustomerorder insert(HqtCustomerorder hqtCustomerorder) {
        this.hqtCustomerorderDao.insert(hqtCustomerorder);
        return hqtCustomerorder;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtCustomerorders List<HqtCustomerorder> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HqtCustomerorder> hqtCustomerorders) {
        return this.hqtCustomerorderDao.insertBatch(hqtCustomerorders);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtCustomerorders List<HqtCustomerorder> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HqtCustomerorder> hqtCustomerorders) {
        return this.hqtCustomerorderDao.insertOrUpdateBatch(hqtCustomerorders);
    }

    /**
     * 修改数据
     *
     * @param hqtCustomerorder 实例对象
     * @return 实例对象
     */
    @Override
    public HqtCustomerorder update(HqtCustomerorder hqtCustomerorder) {
        this.hqtCustomerorderDao.update(hqtCustomerorder);
        return this.queryById(hqtCustomerorder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hqtCustomerorderDao.deleteById(id) > 0;
    }
}
