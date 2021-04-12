package com.middleware.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangqian
 * @date 2021/4/12
 */
public class Constants {

    public static Map<String, RateLimiter> rateLimiterMap = Collections.synchronizedMap(new HashMap<String, RateLimiter>());

}
