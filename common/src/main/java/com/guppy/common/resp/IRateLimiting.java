package com.guppy.common.resp;

/**
 * Created by guppy
 * on 16-9-3 ����10:19.
 */
public interface IRateLimiting {
    int getRateLimitQuota();

    int getRateLimitRemaining();

    int getRateLimitReset();
}
