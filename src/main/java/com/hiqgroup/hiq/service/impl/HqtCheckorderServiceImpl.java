package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HqtCheckorderDao;
import com.hiqgroup.hiq.entity.HqtCheckorder;
import com.hiqgroup.hiq.service.HqtCheckorderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 检测方订单(HqtCheckorder)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 10:27:12
 */
@Service("hqtCheckorderService")
public class HqtCheckorderServiceImpl implements HqtCheckorderService {
    @Resource
    private HqtCheckorderDao hqtCheckorderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqtCheckorder queryById(String id) {
        return this.hqtCheckorderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hqtCheckorder 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtCheckorder> queryAllByLimit(HqtCheckorder hqtCheckorder, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hqtCheckorderDao.queryAllByLimit(hqtCheckorder, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param hqtCheckorder 实例对象
     * @param offset        查询起始位置
     * @param limit         查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtCheckorder> queryNotEqualAllByLimit(HqtCheckorder hqtCheckorder, int offset, int limit, String orderby, String asc) {
        return this.hqtCheckorderDao.queryNotEqualAllByLimit(hqtCheckorder, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckorder> queryAll(HqtCheckorder hqtCheckorder) {
        return this.hqtCheckorderDao.queryAll(hqtCheckorder);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckorder> queryAllLike(HqtCheckorder hqtCheckorder) {
        return this.hqtCheckorderDao.queryAllLike(hqtCheckorder);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckorder> queryAllWhereOr(HqtCheckorder hqtCheckorder) {
        return this.hqtCheckorderDao.queryAllWhereOr(hqtCheckorder);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HqtCheckorder hqtCheckorder, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hqtCheckorderDao.getCount(hqtCheckorder, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(HqtCheckorder hqtCheckorder) {
        return this.hqtCheckorderDao.getCountByNotEqual(hqtCheckorder);
    }

    /**
     * 新增数据
     *
     * @param hqtCheckorder 实例对象
     * @return 实例对象
     */
    @Override
    public HqtCheckorder insert(HqtCheckorder hqtCheckorder) {
        this.hqtCheckorderDao.insert(hqtCheckorder);
        return hqtCheckorder;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckorders List<HqtCheckorder> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HqtCheckorder> hqtCheckorders) {
        return this.hqtCheckorderDao.insertBatch(hqtCheckorders);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckorders List<HqtCheckorder> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HqtCheckorder> hqtCheckorders) {
        return this.hqtCheckorderDao.insertOrUpdateBatch(hqtCheckorders);
    }

    /**
     * 修改数据
     *
     * @param hqtCheckorder 实例对象
     * @return 实例对象
     */
    @Override
    public HqtCheckorder update(HqtCheckorder hqtCheckorder) {
        this.hqtCheckorderDao.update(hqtCheckorder);
        return this.queryById(hqtCheckorder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hqtCheckorderDao.deleteById(id) > 0;
    }
}
