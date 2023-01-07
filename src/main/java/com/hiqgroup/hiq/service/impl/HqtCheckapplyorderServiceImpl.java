package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HqtCheckapplyorderDao;
import com.hiqgroup.hiq.entity.HqtCheckapplyorder;
import com.hiqgroup.hiq.service.HqtCheckapplyorderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 委托检测申请单(HqtCheckapplyorder)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 10:20:17
 */
@Service("hqtCheckapplyorderService")
public class HqtCheckapplyorderServiceImpl implements HqtCheckapplyorderService {
    @Resource
    private HqtCheckapplyorderDao hqtCheckapplyorderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HqtCheckapplyorder queryById(String id) {
        return this.hqtCheckapplyorderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @param offset             查询起始位置
     * @param limit              查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtCheckapplyorder> queryAllByLimit(HqtCheckapplyorder hqtCheckapplyorder, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hqtCheckapplyorderDao.queryAllByLimit(hqtCheckapplyorder, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @param offset             查询起始位置
     * @param limit              查询条数
     * @return 对象列表
     */
    @Override
    public List<HqtCheckapplyorder> queryNotEqualAllByLimit(HqtCheckapplyorder hqtCheckapplyorder, int offset, int limit, String orderby, String asc) {
        return this.hqtCheckapplyorderDao.queryNotEqualAllByLimit(hqtCheckapplyorder, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckapplyorder> queryAll(HqtCheckapplyorder hqtCheckapplyorder) {
        return this.hqtCheckapplyorderDao.queryAll(hqtCheckapplyorder);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckapplyorder> queryAllLike(HqtCheckapplyorder hqtCheckapplyorder) {
        return this.hqtCheckapplyorderDao.queryAllLike(hqtCheckapplyorder);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 对象列表
     */
    @Override
    public List<HqtCheckapplyorder> queryAllWhereOr(HqtCheckapplyorder hqtCheckapplyorder) {
        return this.hqtCheckapplyorderDao.queryAllWhereOr(hqtCheckapplyorder);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HqtCheckapplyorder hqtCheckapplyorder, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hqtCheckapplyorderDao.getCount(hqtCheckapplyorder, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(HqtCheckapplyorder hqtCheckapplyorder) {
        return this.hqtCheckapplyorderDao.getCountByNotEqual(hqtCheckapplyorder);
    }

    /**
     * 新增数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 实例对象
     */
    @Override
    public HqtCheckapplyorder insert(HqtCheckapplyorder hqtCheckapplyorder) {
        this.hqtCheckapplyorderDao.insert(hqtCheckapplyorder);
        return hqtCheckapplyorder;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckapplyorders List<HqtCheckapplyorder> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HqtCheckapplyorder> hqtCheckapplyorders) {
        return this.hqtCheckapplyorderDao.insertBatch(hqtCheckapplyorders);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hqtCheckapplyorders List<HqtCheckapplyorder> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HqtCheckapplyorder> hqtCheckapplyorders) {
        return this.hqtCheckapplyorderDao.insertOrUpdateBatch(hqtCheckapplyorders);
    }

    /**
     * 修改数据
     *
     * @param hqtCheckapplyorder 实例对象
     * @return 实例对象
     */
    @Override
    public HqtCheckapplyorder update(HqtCheckapplyorder hqtCheckapplyorder) {
        this.hqtCheckapplyorderDao.update(hqtCheckapplyorder);
        return this.queryById(hqtCheckapplyorder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hqtCheckapplyorderDao.deleteById(id) > 0;
    }
}
