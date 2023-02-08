package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtMenuDao;
import com.hiqgroup.hiq.entity.SmtMenu;
import com.hiqgroup.hiq.service.SmtMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单资料表(SmtMenu)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:21
 */
@Service("smtMenuService")
public class SmtMenuServiceImpl implements SmtMenuService {
    @Resource
    private SmtMenuDao smtMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SmtMenu queryById(String id) {
        return this.smtMenuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param orderby 排序字段
     * @param asc     顺序倒序
     * @return 对象列表
     */
    @Override
    public List<SmtMenu> queryAllByLimit(int offset, int limit, String orderby, String asc) {
        return this.smtMenuDao.queryAllByLimit(offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenu 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtMenu> queryAll(SmtMenu smtMenu) {
        return this.smtMenuDao.queryAll(smtMenu);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenu 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtMenu> queryAllLike(SmtMenu smtMenu) {
        return this.smtMenuDao.queryAllLike(smtMenu);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenu 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtMenu> queryAllWhereOr(SmtMenu smtMenu) {
        return this.smtMenuDao.queryAllWhereOr(smtMenu);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount() {
        return this.smtMenuDao.getCount();
    }

    /**
     * 新增数据
     *
     * @param smtMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SmtMenu insert(SmtMenu smtMenu) {
        this.smtMenuDao.insert(smtMenu);
        return smtMenu;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtMenus List<SmtMenu> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtMenu> smtMenus) {
        return this.smtMenuDao.insertBatch(smtMenus);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtMenus List<SmtMenu> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtMenu> smtMenus) {
        return this.smtMenuDao.insertOrUpdateBatch(smtMenus);
    }

    /**
     * 修改数据
     *
     * @param smtMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SmtMenu update(SmtMenu smtMenu) {
        this.smtMenuDao.update(smtMenu);
        return this.queryById(smtMenu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.smtMenuDao.deleteById(id) > 0;
    }
}
