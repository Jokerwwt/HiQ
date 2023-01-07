package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HpgDailyDao;
import com.hiqgroup.hiq.entity.HpgDaily;
import com.hiqgroup.hiq.service.HpgDailyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 日常动态(HpgDaily)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:32:18
 */
@Service("hpgDailyService")
public class HpgDailyServiceImpl implements HpgDailyService {
    @Resource
    private HpgDailyDao hpgDailyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HpgDaily queryById(String id) {
        return this.hpgDailyDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hpgDaily 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgDaily> queryAllByLimit(HpgDaily hpgDaily, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hpgDailyDao.queryAllByLimit(hpgDaily, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param hpgDaily 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgDaily> queryNotEqualAllByLimit(HpgDaily hpgDaily, int offset, int limit, String orderby, String asc) {
        return this.hpgDailyDao.queryNotEqualAllByLimit(hpgDaily, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDaily 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgDaily> queryAll(HpgDaily hpgDaily) {
        return this.hpgDailyDao.queryAll(hpgDaily);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDaily 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgDaily> queryAllLike(HpgDaily hpgDaily) {
        return this.hpgDailyDao.queryAllLike(hpgDaily);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDaily 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgDaily> queryAllWhereOr(HpgDaily hpgDaily) {
        return this.hpgDailyDao.queryAllWhereOr(hpgDaily);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HpgDaily hpgDaily, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hpgDailyDao.getCount(hpgDaily, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(HpgDaily hpgDaily) {
        return this.hpgDailyDao.getCountByNotEqual(hpgDaily);
    }

    /**
     * 新增数据
     *
     * @param hpgDaily 实例对象
     * @return 实例对象
     */
    @Override
    public HpgDaily insert(HpgDaily hpgDaily) {
        this.hpgDailyDao.insert(hpgDaily);
        return hpgDaily;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgDailys List<HpgDaily> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HpgDaily> hpgDailys) {
        return this.hpgDailyDao.insertBatch(hpgDailys);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgDailys List<HpgDaily> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HpgDaily> hpgDailys) {
        return this.hpgDailyDao.insertOrUpdateBatch(hpgDailys);
    }

    /**
     * 修改数据
     *
     * @param hpgDaily 实例对象
     * @return 实例对象
     */
    @Override
    public HpgDaily update(HpgDaily hpgDaily) {
        this.hpgDailyDao.update(hpgDaily);
        return this.queryById(hpgDaily.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hpgDailyDao.deleteById(id) > 0;
    }
}
