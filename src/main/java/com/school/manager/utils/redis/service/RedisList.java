package com.school.manager.utils.redis.service;

import java.util.List;

/**
 * @author RoninLee
 */
public interface RedisList<K, V> extends RedisOperation<K> {

    /**
     * 添加单个缓存内容
     * 规则：
     * 无限周期，如存在缓存配置周期，则按配置周期
     * @param k 键
     * @param v 值
     */
    void add(K k, V v);

    /**
     * 添加多个缓存内容
     * 规则：
     * 无限周期，如存在缓存配置周期，则按配置周期
     * @param k 键
     * @param vList 值
     */
    void add(K k, List<V> vList);

    /**
     * 查询元素
     * @param k 键
     * @return List
     */
    List<V> get(K k);

    /**
     * 获取第一个写入的元素
     * @param k 键
     * @return V
     */
    V getFirst(K k);

    /**
     * 获取最后一个写入的元素
     * @param k 键
     * @return V
     */
    V getLast(K k);

    /**
     * 截取最近写入的指定数量元素
     * @param k 键
     * @param num 数量
     */
    void trim(K k, long num);

    /**
     * 元素数量
     * @param k 键
     * @return long
     */
    long size(K k);
}
