package com.school.manager.utils.redis.service;

import com.school.manager.utils.redis.common.CacheCycle;

import java.util.List;
import java.util.Map;

/**
 * @author RoninLee
 */
public interface RedisValue<K, V> extends RedisOperation<K> {

    /**
     * 添加单个缓存内容
     * 规则：
     * 1.覆盖已有键值对
     * 2.无限周期，如存在缓存配置周期，则按配置周期
     *
     * @param k 键
     * @param v 值
     */
    void add(K k, V v);

    /**
     * 添加单个缓存内容
     * 规则：
     * 1.覆盖已有键值对
     * 2.按入参的周期缓存内容
     *
     * @param k     键
     * @param v     值
     * @param cycle 周期
     */
    void add(K k, V v, CacheCycle cycle);

    /**
     * 添加多个缓存内容
     * 规则：
     * 1.覆盖已有键值对
     * 2.无限周期，如存在缓存配置周期，则按配置周期
     *
     * @param map 键值对
     */
    void add(Map<K, V> map);

    /**
     * 添加多个缓存内容
     * 规则：
     * 1.覆盖已有键值对
     * 2.按入参的周期缓存内容
     *
     * @param map   键值对
     * @param cycle 周期
     */
    void add(Map<K, V> map, CacheCycle cycle);

    /**
     * 添加单个缓存内容
     * 规则：
     * 1.不覆盖已存在的键值对
     * 2.无限周期，如存在缓存配置周期，则按配置周期
     *
     * @param k 键
     * @param v 值
     */
    void addAbsent(K k, V v);

    /**
     * 添加单个缓存内容
     * 规则：
     * 1.不覆盖已存在的键值对
     * 2.按入参的周期缓存内容
     *
     * @param k     键
     * @param v     值
     * @param cycle 周期
     */
    void addAbsent(K k, V v, CacheCycle cycle);

    /**
     * 添加多个缓存内容
     * 规则：
     * 1.不覆盖已存在的键值对
     * 2.无限周期，如存在缓存配置周期，则按配置周期
     *
     * @param map 键值对
     */
    void addAbsent(Map<K, V> map);

    /**
     * 添加多个缓存内容
     * 规则：
     * 1.不覆盖已存在的键值对
     * 2.按入参的周期缓存内容(如已存在的键，周期也将被刷新)
     *
     * @param map   键值对
     * @param cycle 周期
     */
    void addAbsent(Map<K, V> map, CacheCycle cycle);

    /**
     * 更新值得内容
     * 规则：
     * 只更新内容，不刷新周期
     *
     * @param k 键
     * @param v 值
     */
    void updateValue(K k, V v);

    /**
     * 对键所对应的值做累加
     * 规则：
     * 整数型
     *
     * @param k   键
     * @param num 累加数
     */
    Long increment(K k, long num);

    /**
     * 对键所对应的值做累加
     * 规则：
     * 浮点型
     *
     * @param k   键
     * @param num 累加数
     */
    Double increment(K k, double num);

    /**
     * 获取缓存对象
     *
     * @param k 键
     * @return V
     */
    V get(K k);

    /**
     * 获取缓存对象
     *
     * @param kList 键
     * @return List
     */
    List<V> get(List<K> kList);
}
