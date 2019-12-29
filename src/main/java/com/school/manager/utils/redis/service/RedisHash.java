package com.school.manager.utils.redis.service;

import com.school.manager.utils.redis.common.CacheCycle;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author RoninLee
 */
public interface RedisHash<K, HK, HV> extends RedisOperation<K> {

    /**
     * 添加单个缓存内容
     * 规则：
     * 1.覆盖已有键值对
     * 2.无限周期，如存在缓存配置周期，则按配置周期
     * @param k 键
     * @param hk hash键
     * @param hv 值
     */
    void add(K k, HK hk, HV hv);

    /**
     * 添加单个缓存内容
     * 规则：
     * 1.覆盖已有键值对
     * 2.按入参的周期缓存内容(缓存周期计算为键，而非hash键)
     * @param k 键
     * @param hk hash键
     * @param hv 值
     * @param cycle 周期
     */
    void add(K k, HK hk, HV hv, CacheCycle cycle);

    /**
     * 添加多个缓存内容
     * 规则：
     * 1.覆盖已有键值对
     * 2.无限周期，如存在缓存配置周期，则按配置周期
     * @param k 键
     * @param map 键值对
     */
    void add(K k, Map<HK, HV> map);

    /**
     * 添加多个缓存内容
     * 规则：
     * 1.覆盖已有键值对
     * 2.按入参的周期缓存内容(缓存周期计算为键，而非hash键)
     * @param k 键
     * @param map 键值对
     * @param cycle 周期
     */
    void add(K k, Map<HK, HV> map, CacheCycle cycle);

    /**
     * 添加单个缓存内容
     * 规则：
     * 1.不覆盖已存在的键值对
     * 2.无限周期，如存在缓存配置周期，则按配置周期
     * @param k 键
     * @param hk hash键
     * @param hv 值
     */
    void addAbsent(K k, HK hk, HV hv);

    /**
     * 添加单个缓存内容
     * 规则：
     * 1.不覆盖已存在的键值对
     * 2.按入参的周期缓存内容(缓存周期计算为键，而非hash键)
     * @param k 键
     * @param hk hash键
     * @param hv 值
     * @param cycle 周期
     */
    void addAbsent(K k, HK hk, HV hv, CacheCycle cycle);

    /**
     * hash键是否存在
     * @param k 键
     * @param hk hash键是否存在
     * @return boolean
     */
    boolean doesKeyExist(K k, HK hk);

    /**
     * 删除hash元素
     * @param k 键
     * @param hk hash键集合
     */
    void delete(K k, Object... hk);

    /**
     * 获取元素
     * @param k 键
     * @param hk hash键
     * @return HV
     */
    HV get(K k, HK hk);

    /**
     * 获取元素
     * @param k 键
     * @param hks hash键
     * @return HV
     */
    List<HV> get(K k, List<HK> hks);

    /**
     * 查询
     * @param k 键
     * @param condition 条件
     * @return List
     */
    List<Map<HK, HV>> query(K k, String condition);

    /**
     * 对hash键所对应的值做累加
     * 规则：
     * 整数型
     * @param k 键
     * @param hk hash键
     * @param delta 累加值
     */
    Long increment(K k, HK hk, long delta);

    /**
     * 对hash键所对应的值做累加
     * 规则：
     * 浮点型
     * @param k 键
     * @param hk hash键
     * @param delta 累加值
     */
    Double increment(K k, HK hk, double delta);

    /**
     * 获取所有的hash键
     * @param k 键
     * @return Set
     */
    Set<HK> keys(K k);

    /**
     * 获取所有的值
     * @param k 键
     * @return List
     */
    List<HV> values(K k);

    /**
     * hash键的数量
     * @param k 键
     * @return long
     */
    long size(K k);
}
