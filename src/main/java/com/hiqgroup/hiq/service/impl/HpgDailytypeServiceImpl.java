package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HpgDailytypeDao;
import com.hiqgroup.hiq.entity.HpgDailytype;
import com.hiqgroup.hiq.service.HpgDailytypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 日常动态类别(HpgDailytype)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:38:56
 */
@Service("hpgDailytypeService")
public class HpgDailytypeServiceImpl implements HpgDailytypeService {
    @Resource
    private HpgDailytypeDao hpgDailytypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HpgDailytype queryById(String id) {
        return this.hpgDailytypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hpgDailytype 实例对象
     * @param offset       查询起始位置
     * @param limit        查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgDailytype> queryAllByLimit(HpgDailytype hpgDailytype, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hpgDailytypeDao.queryAllByLimit(hpgDailytype, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param hpgDailytype 实例对象
     * @param offset       查询起始位置
     * @param limit        查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgDailytype> queryNotEqualAllByLimit(HpgDailytype hpgDailytype, int offset, int limit, String orderby, String asc) {
        return this.hpgDailytypeDao.queryNotEqualAllByLimit(hpgDailytype, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDailytype 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgDailytype> queryAll(HpgDailytype hpgDailytype) {
        return this.hpgDailytypeDao.queryAll(hpgDailytype);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDailytype 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgDailytype> queryAllLike(HpgDailytype hpgDailytype) {
        return this.hpgDailytypeDao.queryAllLike(hpgDailytype);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgDailytype 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgDailytype> queryAllWhereOr(HpgDailytype hpgDailytype) {
        return this.hpgDailytypeDao.queryAllWhereOr(hpgDailytype);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HpgDailytype hpgDailytype, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hpgDailytypeDao.getCount(hpgDailytype, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(HpgDailytype hpgDailytype) {
        return this.hpgDailytypeDao.getCountByNotEqual(hpgDailytype);
    }

    /**
     * 新增数据
     *
     * @param hpgDailytype 实例对象
     * @return 实例对象
     */
    @Override
    public HpgDailytype insert(HpgDailytype hpgDailytype) {
        this.hpgDailytypeDao.insert(hpgDailytype);
        return hpgDailytype;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgDailytypes List<HpgDailytype> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HpgDailytype> hpgDailytypes) {
        return this.hpgDailytypeDao.insertBatch(hpgDailytypes);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgDailytypes List<HpgDailytype> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HpgDailytype> hpgDailytypes) {
        return this.hpgDailytypeDao.insertOrUpdateBatch(hpgDailytypes);
    }

    /**
     * 修改数据
     *
     * @param hpgDailytype 实例对象
     * @return 实例对象
     */
    @Override
    public HpgDailytype update(HpgDailytype hpgDailytype) {
        this.hpgDailytypeDao.update(hpgDailytype);
        return this.queryById(hpgDailytype.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hpgDailytypeDao.deleteById(id) > 0;
    }
}
