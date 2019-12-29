package com.school.manager.utils.redis.service.impl;

import com.school.manager.utils.redis.common.CacheCycle;
import com.school.manager.utils.redis.service.RedisOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author RoninLee
 */
@Service
public class RedisOperationDeft<K, V> implements RedisOperation<K> {

    @Resource
    public RedisTemplate<K, V> redisTemplate;

    @Override
    public boolean hasKey(K k) {
        return Optional.ofNullable(k).map(key -> Optional.ofNullable(redisTemplate.hasKey(key)).orElse(Boolean.FALSE)).orElse(Boolean.FALSE);
    }

    @Override
    public void remove(K k) {
        Optional.ofNullable(k).ifPresent(key -> {
            if (hasKey(key)) {
                redisTemplate.delete(k);
            }
        });
    }

    @Override
    public void remove(List<K> kList) {
        Optional.ofNullable(kList).ifPresent(list -> {
            List<K> opList = list.stream().filter(Objects::nonNull).filter(this::hasKey).collect(Collectors.toList());
            if (!opList.isEmpty()) {
                redisTemplate.delete(opList);
            }
        });
    }

    @Override
    public void refresh(K k, CacheCycle cycle) {
        Optional.ofNullable(k).ifPresent(key -> {
            if (hasKey(k)) {
                redisTemplate.expire(k, cycle.getTime(), cycle.getUnit());
            }
        });
    }
}
