package com.fanhl.rxcache

import io.reactivex.Observable
import java.lang.reflect.Type

/**
 * desc:
 * date: 2017/7/10
 *
 * @author fanhl
 */

fun <T> Observable<T>.rcCache(
        name: String,
        vararg conditions: String,
        type: Type? = null
) = this.compose(RxCache.cache(name, *conditions, type = type))!!