package com.fanhl.rxcache

import io.reactivex.Observable

/**
 * desc:
 * date: 2017/7/10
 *
 * @author fanhl
 */

fun <T> Observable<T>.rcCache(key: String) = this.compose(RxCache.cache(key))!!