package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.SmtMenuRightitemDao;
import com.hiqgroup.hiq.entity.SmtMenuRightitem;
import com.hiqgroup.hiq.service.SmtMenuRightitemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单功能项目表(SmtMenuRightitem)表服务实现类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:27
 */
@Service("smtMenuRightitemService")
public class SmtMenuRightitemServiceImpl implements SmtMenuRightitemService {
    @Resource
    private SmtMenuRightitemDao smtMenuRightitemDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuid 主键
     * @return 实例对象
     */
    @Override
    public SmtMenuRightitem queryById(String menuid) {
        return this.smtMenuRightitemDao.queryById(menuid);
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
    public List<SmtMenuRightitem> queryAllByLimit(int offset, int limit, String orderby, String asc) {
        return this.smtMenuRightitemDao.queryAllByLimit(offset, limit, orderby, asc);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtMenuRightitem> queryAll(SmtMenuRightitem smtMenuRightitem) {
        return this.smtMenuRightitemDao.queryAll(smtMenuRightitem);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtMenuRightitem> queryAllLike(SmtMenuRightitem smtMenuRightitem) {
        return this.smtMenuRightitemDao.queryAllLike(smtMenuRightitem);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param smtMenuRightitem 实例对象
     * @return 对象列表
     */
    @Override
    public List<SmtMenuRightitem> queryAllWhereOr(SmtMenuRightitem smtMenuRightitem) {
        return this.smtMenuRightitemDao.queryAllWhereOr(smtMenuRightitem);
    }

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    @Override
    public int getCount() {
        return this.smtMenuRightitemDao.getCount();
    }

    /**
     * 新增数据
     *
     * @param smtMenuRightitem 实例对象
     * @return 实例对象
     */
    @Override
    public SmtMenuRightitem insert(SmtMenuRightitem smtMenuRightitem) {
        this.smtMenuRightitemDao.insert(smtMenuRightitem);
        return smtMenuRightitem;
    }

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param smtMenuRightitems List<SmtMenuRightitem> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SmtMenuRightitem> smtMenuRightitems) {
        return this.smtMenuRightitemDao.insertBatch(smtMenuRightitems);
    }

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param smtMenuRightitems List<SmtMenuRightitem> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertOrUpdateBatch(List<SmtMenuRightitem> smtMenuRightitems) {
        return this.smtMenuRightitemDao.insertOrUpdateBatch(smtMenuRightitems);
    }

    /**
     * 修改数据
     *
     * @param smtMenuRightitem 实例对象
     * @return 实例对象
     */
    @Override
    public SmtMenuRightitem update(SmtMenuRightitem smtMenuRightitem) {
        this.smtMenuRightitemDao.update(smtMenuRightitem);
        return this.queryById(smtMenuRightitem.getMenuid());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String menuid) {
        return this.smtMenuRightitemDao.deleteById(menuid) > 0;
    }
}
