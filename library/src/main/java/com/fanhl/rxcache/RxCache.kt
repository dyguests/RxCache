package com.fanhl.rxcache

import com.google.gson.reflect.TypeToken
import io.reactivex.ObservableTransformer

/**
 * desc:
 * date: 2017/7/10
 *
 * @author fanhl
 */
object RxCache {
    val provider: ICacheProvider = DefaultCacheProvider()

    fun <T> cache(key: String) = ObservableTransformer<T, T> { upStream ->
        var currStream = upStream.map { CacheWrap(it) }

        val cacheWrap: CacheWrap<T>? = provider.get(key, object : TypeToken<CacheWrap<T>>() {}.type)

        cacheWrap?.let {
            it.type = CacheWrap.Type.FromCache
            currStream = currStream.startWith(it)
        }

        currStream.doOnNext {
            if (it.type != CacheWrap.Type.FromCache) {
                provider.put(key, it)
            }
        }.map { it.data }
    }
}

