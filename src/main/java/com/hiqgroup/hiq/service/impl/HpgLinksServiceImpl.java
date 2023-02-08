package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HpgLinksDao;
import com.hiqgroup.hiq.entity.HpgLinks;
import com.hiqgroup.hiq.service.HpgLinksService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 友情链接(HpgLinks)表服务实现类
 *
 * @author liugaqiong
 * @since 2023-01-06 09:43:19
 */
@Service("hpgLinksService")
public class HpgLinksServiceImpl implements HpgLinksService {
    @Resource
    private HpgLinksDao hpgLinksDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HpgLinks queryById(String id) {
        return this.hpgLinksDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param hpgLinks 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgLinks> queryAllByLimit(HpgLinks hpgLinks, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hpgLinksDao.queryAllByLimit(hpgLinks, offset, limit, orderby, asc, ins, betweens);
    }

    /**
     * 查询不等于多条数据
     *
     * @param hpgLinks 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public List<HpgLinks> queryNotEqualAllByLimit(HpgLinks hpgLinks, int offset, int limit, String orderby, String asc) {
        return this.hpgLinksDao.queryNotEqualAllByLimit(hpgLinks, offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgLinks 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgLinks> queryAll(HpgLinks hpgLinks) {
        return this.hpgLinksDao.queryAll(hpgLinks);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgLinks 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgLinks> queryAllLike(HpgLinks hpgLinks) {
        return this.hpgLinksDao.queryAllLike(hpgLinks);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgLinks 实例对象
     * @return 对象列表
     */
    @Override
    public List<HpgLinks> queryAllWhereOr(HpgLinks hpgLinks) {
        return this.hpgLinksDao.queryAllWhereOr(hpgLinks);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount(HpgLinks hpgLinks, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens) {
        return this.hpgLinksDao.getCount(hpgLinks, ins, betweens);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCountByNotEqual(HpgLinks hpgLinks) {
        return this.hpgLinksDao.getCountByNotEqual(hpgLinks);
    }

    /**
     * 新增数据
     *
     * @param hpgLinks 实例对象
     * @return 实例对象
     */
    @Override
    public HpgLinks insert(HpgLinks hpgLinks) {
        this.hpgLinksDao.insert(hpgLinks);
        return hpgLinks;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgLinkss List<HpgLinks> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<HpgLinks> hpgLinkss) {
        return this.hpgLinksDao.insertBatch(hpgLinkss);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgLinkss List<HpgLinks> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<HpgLinks> hpgLinkss) {
        return this.hpgLinksDao.insertOrUpdateBatch(hpgLinkss);
    }

    /**
     * 修改数据
     *
     * @param hpgLinks 实例对象
     * @return 实例对象
     */
    @Override
    public HpgLinks update(HpgLinks hpgLinks) {
        this.hpgLinksDao.update(hpgLinks);
        return this.queryById(hpgLinks.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.hpgLinksDao.deleteById(id) > 0;
    }
}
