package com.fanhl.rxcache

import rx.Observable

/**
 * desc:
 * date: 2017/7/10
 *
 * @author fanhl
 */

fun <T> Observable<T>.rcCache(
        name: String,
        vararg conditions: String
) = this.compose(RxCache.cache(name, *conditions))!!