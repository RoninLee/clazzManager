package com.school.manager.utils.redis.common;

import java.util.concurrent.TimeUnit;

/**
 * @author RoninLee
 */

@SuppressWarnings("unused")
public enum CacheCycle {

    /** 无限制 */
    NA(-1L, null),

    /** 30秒 */
    THIRTY_SECONDS(30L, TimeUnit.SECONDS),

    /** 1分钟 */
    ONE_MINUTE(1L, TimeUnit.MINUTES),

    /** 3分钟 */
    THREE_MINUTES(3L, TimeUnit.MINUTES),

    /** 5分钟 */
    FIVE_MINUTES(5L, TimeUnit.MINUTES),

    /** 10分钟 */
    TEN_MINUTES(10L, TimeUnit.MINUTES),

    /** 30分钟 */
    THIRTY_MINUTES(30L, TimeUnit.MINUTES),

    /** 1小时 */
    ONE_HOUR(1L, TimeUnit.HOURS),

    /** 3小时 */
    THREE_HOURS(3L, TimeUnit.HOURS),

    /** 6小时 */
    SIX_HOURS(6L, TimeUnit.HOURS),

    /** 12小时 */
    TWELVE_HOURS(12L, TimeUnit.HOURS),

    /** 1天 */
    ONE_DAY(1L, TimeUnit.DAYS),

    /** 1周 */
    ONE_WORK(7L, TimeUnit.DAYS),

    /** 1个月 */
    ONE_MONTH(30L, TimeUnit.DAYS);

    private Long time;

    private TimeUnit unit;

    CacheCycle(Long time, TimeUnit timeUnit) {
        this.time = time;
        this.unit = timeUnit;
    }

    public Long getTime() {
        return this.time;
    }

    public TimeUnit getUnit() {
        return this.unit;
    }
}
