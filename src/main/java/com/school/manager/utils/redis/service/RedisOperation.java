package com.school.manager.utils.redis.service;

import com.school.manager.utils.redis.common.CacheCycle;

import java.util.List;

/**
 * @author RoninLee
 */
public interface RedisOperation<K> {

    /**
     * 键是否存在
     *
     * @param k 键
     * @return boolean
     */
    boolean hasKey(K k);

    /**
     * 移除键
     *
     * @param k 键
     */
    void remove(K k);

    /**
     * 移除键
     *
     * @param kList 键
     */
    void remove(List<K> kList);

    /**
     * 重置缓存时间
     *
     * @param k     键
     * @param cycle 周期
     */
    void refresh(K k, CacheCycle cycle);

}
