package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.entity.HpgLinks;

import java.util.List;
import java.util.Map;

/**
 * 友情链接(HpgLinks)表服务接口
 *
 * @author liugaqiong
 * @since 2023-01-06 09:43:18
 */
public interface HpgLinksService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HpgLinks queryById(String id);

    /**
     * 查询多条数据
     *
     * @param hpgLinks 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<HpgLinks> queryAllByLimit(HpgLinks hpgLinks, int offset, int limit, String orderby, String asc, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 查询不等于多条数据
     *
     * @param hpgLinks 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<HpgLinks> queryNotEqualAllByLimit(HpgLinks hpgLinks, int offset, int limit, String orderby, String asc);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgLinks 实例对象
     * @return 对象列表
     */
    List<HpgLinks> queryAll(HpgLinks hpgLinks);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgLinks 实例对象
     * @return 对象列表
     */
    List<HpgLinks> queryAllLike(HpgLinks hpgLinks);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param hpgLinks 实例对象
     * @return 对象列表
     */
    List<HpgLinks> queryAllWhereOr(HpgLinks hpgLinks);

    /**
     * 获取数据总数
     *
     * @return 记录数
     */
    int getCount(HpgLinks hpgLinks, List<Map<String, List<String>>> ins, List<Map<String, List<String>>> betweens);

    /**
     * 不等于条件获取数据总数
     *
     * @return 记录数
     */
    int getCountByNotEqual(HpgLinks hpgLinks);

    /**
     * 新增数据
     *
     * @param hpgLinks 实例对象
     * @return 实例对象
     */
    HpgLinks insert(HpgLinks hpgLinks);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param hpgLinkss List<HpgLinks> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(List<HpgLinks> hpgLinkss);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param hpgLinkss List<HpgLinks> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(List<HpgLinks> hpgLinkss);

    /**
     * 修改数据
     *
     * @param hpgLinks 实例对象
     * @return 实例对象
     */
    HpgLinks update(HpgLinks hpgLinks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
