package com.school.manager.utils.redis.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.school.manager.utils.redis.common.CacheCycle;
import com.school.manager.utils.redis.service.RedisValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author RoninLee
 */
@Slf4j
@Service("redisValue")
public class RedisValueOp<K, V> extends RedisOperationDeft<K, V> implements RedisValue<K, V> {

    private ValueOperations<K, V> valueOperations;

    @PostConstruct
    public void init() {
        valueOperations = super.redisTemplate.opsForValue();
    }

    @Override
    public void add(K k, V v) {
        add(k, v, null);
    }

    @Override
    public void add(K k, V v, CacheCycle cycle) {
        addExecute(k, v, Optional.ofNullable(cycle).orElse(CacheCycle.NA), Boolean.TRUE);
    }

    @Override
    public void add(Map<K, V> map) {
        add(map, null);
    }

    @Override
    public void add(Map<K, V> map, CacheCycle cycle) {
        addExecute(map, Optional.ofNullable(cycle).orElse(CacheCycle.NA), Boolean.TRUE);
    }

    @Override
    public void addAbsent(K k, V v) {
        addAbsent(k, v, null);
    }

    @Override
    public void addAbsent(K k, V v, CacheCycle cycle) {
        addExecute(k, v, Optional.ofNullable(cycle).orElse(CacheCycle.NA), Boolean.FALSE);
    }

    @Override
    public void addAbsent(Map<K, V> map) {
        addAbsent(map, null);
    }

    @Override
    public void addAbsent(Map<K, V> map, CacheCycle cycle) {
        addExecute(map, Optional.ofNullable(cycle).orElse(CacheCycle.NA), Boolean.FALSE);
    }

    @Override
    public void updateValue(K k, V v) {
        Optional.ofNullable(k).ifPresent(key -> {
            if (hasKey(k)) {
                // 获取当前key的周期时间
                Long cycle = Optional.ofNullable(redisTemplate.getExpire(k, TimeUnit.SECONDS)).orElse(0L);
                // 移除老的键
                remove(k);
                // 写入新的键值信息
                valueOperations.set(k, v, cycle, TimeUnit.SECONDS);
            }
        });
    }

    @Override
    public Long increment(K k, long num) {
        return Optional.ofNullable(k).map(key -> {
            try {
                return valueOperations.increment(k, num);
            } catch (Exception e) {
                log.error("值类型非整数型");
                return null;
            }
        }).orElse(null);
    }

    @Override
    public Double increment(K k, double num) {
        return Optional.ofNullable(k).map(key -> {
            try {
                return valueOperations.increment(k, num);
            } catch (Exception e) {
                log.error("值类型非浮点型");
                return null;
            }
        }).orElse(null);
    }

    @Override
    public V get(K k) {
        return Optional.ofNullable(k).map(key -> valueOperations.get(key)).orElse(null);
    }

    @Override
    public List<V> get(List<K> ks) {
        return Optional.ofNullable(ks).map(list -> valueOperations.multiGet(list)).orElse(Lists.newArrayList());
    }

    private void addExecute(K k, V v, CacheCycle cycle, boolean flag) {
        Optional.ofNullable(k).ifPresent(key -> {
            if (flag) {
                valueOperations.set(k, v, cycle.getTime(), cycle.getUnit());
            } else {
                valueOperations.setIfAbsent(k, v);
                redisTemplate.expire(k, cycle.getTime(), cycle.getUnit());
            }
        });
    }

    private void addExecute(Map<K, V> map, CacheCycle cycle, boolean flag) {
        Optional.ofNullable(map).ifPresent(kvMap -> {
            // 过滤掉map中可能存在KEY为NULL的情况
            Map<K, V> temp = Maps.newHashMap();
            kvMap.forEach((key, value) -> {
                if (Objects.nonNull(key)) {
                    temp.put(key, value);
                }
            });
            if (!temp.isEmpty()) {
                if (flag) {
                    valueOperations.multiSet(temp);
                } else {
                    valueOperations.multiSetIfAbsent(temp);
                }
                if (!Objects.equals(cycle, CacheCycle.NA)) {
                    temp.keySet().forEach(key -> refresh(key, cycle));
                }
            }
        });
    }
}
