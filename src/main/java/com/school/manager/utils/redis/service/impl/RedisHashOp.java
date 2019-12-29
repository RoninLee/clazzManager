package com.fehorizon.commonService.common.utils.redis.service.impl;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.school.manager.utils.redis.common.CacheCycle;
import com.school.manager.utils.redis.service.RedisHash;
import com.school.manager.utils.redis.service.impl.RedisOperationDeft;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author RoninLee
 */
@Slf4j
@Service("redisHash")
public class RedisHashOp<K, HK, HV> extends RedisOperationDeft<K, Map> implements RedisHash<K, HK, HV> {

    private HashOperations<K, HK, HV> hashOperations;

    @PostConstruct
    public void init() {
        hashOperations = super.redisTemplate.opsForHash();
    }

    @Override
    public void add(K k, HK hk, HV hv) {
        add(k, hk, hv, null);
    }

    @Override
    public void add(K k, HK hk, HV hv, CacheCycle cycle) {
        addExecute(k, hk, hv, Optional.ofNullable(cycle).orElse(CacheCycle.NA), Boolean.TRUE);
    }

    @Override
    public void add(K k, Map<HK, HV> map) {
        add(k, map, CacheCycle.NA);
    }

    @Override
    public void add(K k, Map<HK, HV> map, CacheCycle cycle) {
        Optional.ofNullable(k).ifPresent(key ->
                Optional.ofNullable(map).ifPresent(hashMap -> {
                    // 过滤掉map中可能存在KEY为NULL的情况
                    Map<HK, HV> temp = Maps.newHashMap();
                    hashMap.forEach((hashKey, hashValue) -> {
                        if (Objects.nonNull(hashKey)) {
                            temp.put(hashKey, hashValue);
                        }
                    });
                    if (!temp.isEmpty()) {
                        hashOperations.putAll(key, temp);
                        if (!Objects.equals(cycle, CacheCycle.NA)) {
                            refresh(key, cycle);
                        }
                    }
                })
        );
    }

    @Override
    public void addAbsent(K k, HK hk, HV hv) {
        addAbsent(k, hk, hv, null);
    }

    @Override
    public void addAbsent(K k, HK hk, HV hv, CacheCycle cycle) {
        addExecute(k, hk, hv, Optional.ofNullable(cycle).orElse(CacheCycle.NA), Boolean.FALSE);
    }

    @Override
    public boolean doesKeyExist(K k, HK hk) {
        return Optional.ofNullable(k).map(key -> Optional.ofNullable(hk).map(hashKey -> hashOperations.hasKey(key, hashKey)).orElse(Boolean.FALSE)).orElse(Boolean.FALSE);
    }

    @Override
    public void delete(K k, Object... hk) {
        Optional.ofNullable(k).ifPresent(key -> {
            if (Objects.nonNull(hk)) {
                hashOperations.delete(key, hk);
            }
        });
    }

    @Override
    public HV get(K k, HK hk) {
        return Optional.ofNullable(k).map(key -> Optional.ofNullable(hk).map(hashKey -> hashOperations.get(key, hashKey)).orElse(null)).orElse(null);
    }

    @Override
    public List<HV> get(K k, List<HK> hks) {
        return Optional.ofNullable(k).map(key -> Optional.ofNullable(hks).map(list -> hashOperations.multiGet(key, list)).orElse(Lists.newArrayList())).orElse(Lists.newArrayList());
    }

    @Override
    public List<Map<HK, HV>> query(K k, String condition) {
        return Optional.ofNullable(k).map(key -> {
            Cursor<Map.Entry<HK, HV>> cursor;
            if (StringUtils.isBlank(condition)) {
                cursor = hashOperations.scan(key, ScanOptions.NONE);
            } else {
                long size = size(key);
                ScanOptions scan = ScanOptions.scanOptions().count(size).match(condition).build();
                cursor = hashOperations.scan(key, scan);
            }
            List<Map<HK, HV>> list = Lists.newArrayList();
            while (cursor.hasNext()) {
                Map.Entry<HK, HV> entry = cursor.next();
                Optional.ofNullable(entry).ifPresent(e -> {
                    Map<HK, HV> map = Maps.newHashMap();
                    map.put(e.getKey(), e.getValue());
                    list.add(map);
                });
            }
            return list;
        }).orElse(Lists.newArrayList());
    }

    @Override
    public Long increment(K k, HK hk, long delta) {
        return Optional.ofNullable(k).map(key ->
                Optional.ofNullable(hk).map(hashKey -> {
                    try {
                        return hashOperations.increment(k, hk, delta);
                    } catch (Exception e) {
                        log.error("值类型非整数型");
                        return null;
                    }
                }).orElse(null)
        ).orElse(null);
    }

    @Override
    public Double increment(K k, HK hk, double delta) {
        return Optional.ofNullable(k).map(key ->
                Optional.ofNullable(hk).map(hashKey -> {
                    try {
                        return hashOperations.increment(k, hk, delta);
                    } catch (Exception e) {
                        log.error("值类型非浮点型");
                        return null;
                    }
                }).orElse(null)
        ).orElse(null);
    }

    @Override
    public Set<HK> keys(K k) {
        return Optional.ofNullable(k).map(key -> hashOperations.keys(key)).orElse(Sets.newHashSet());
    }

    @Override
    public List<HV> values(K k) {
        return Optional.ofNullable(k).map(key -> hashOperations.values(key)).orElse(Lists.newArrayList());
    }

    @Override
    public long size(K k) {
        return Optional.ofNullable(k).map(key -> hashOperations.size(key)).orElse(0L);
    }

    private void addExecute(K k, HK hk, HV hv, CacheCycle cycle, boolean flag) {
        Optional.ofNullable(k).ifPresent(key ->
                Optional.ofNullable(hk).ifPresent(hashKey -> {
                    if (flag) {
                        hashOperations.put(k, hk, hv);
                    } else {
                        hashOperations.putIfAbsent(k, hk, hv);
                    }
                    if (!Objects.equals(cycle, CacheCycle.NA)) {
                        refresh(key, cycle);
                    }
                })
        );
    }
}
