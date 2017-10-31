package com.fanhl.rxcache

import io.reactivex.Observable

/**
 * desc:
 * date: 2017/7/10
 *
 * @author fanhl
 */

inline fun <reified T> Observable<T>.rcCache(
        name: Any,
        vararg conditions: Any
) = this.compose(RxCache.cache(name, *conditions))!!