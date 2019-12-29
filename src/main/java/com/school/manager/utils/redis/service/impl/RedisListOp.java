package com.school.manager.utils.redis.service.impl;

import com.google.common.collect.Lists;
import com.school.manager.utils.redis.service.RedisList;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * @author RoninLee
 */
@Service("redisList")
public class RedisListOp<K, V> extends RedisOperationDeft<K, V> implements RedisList<K, V> {

    private ListOperations<K, V> listOperations;

    @PostConstruct
    public void init() {
        listOperations = super.redisTemplate.opsForList();
    }

    @Override
    public void add(K k, V v) {
        Optional.ofNullable(k).ifPresent(key -> listOperations.rightPush(key, v));
    }

    @Override
    public void add(K k, List<V> vs) {
        Optional.ofNullable(k).ifPresent(key -> listOperations.rightPushAll(key, Optional.ofNullable(vs).orElse(Lists.newArrayList())));
    }

    @Override
    public List<V> get(K k) {
        return Optional.ofNullable(k).map(key -> listOperations.range(k, 0, -1)).orElse(Lists.newArrayList());
    }

    @Override
    public V getFirst(K k) {
        return get(k).stream().findFirst().orElse(null);
    }

    @Override
    public V getLast(K k) {
        return size(k) > 0L ? get(k).get((int) size(k) - 1) : null;
    }

    @Override
    public void trim(K k, long num) {
        Optional.ofNullable(k).ifPresent(key -> {
            if (num < size(k)) {
                listOperations.trim(k, size(k) - num, -1);
            }
        });
    }

    @Override
    public long size(K k) {
        return Optional.ofNullable(k).map(key -> listOperations.size(k)).orElse(0L);
    }


}
