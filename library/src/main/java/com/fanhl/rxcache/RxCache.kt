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

    fun <T> cache(
            name: String,
            vararg conditions: String
    ) = ObservableTransformer<T, T> { upStream ->
        val key = name + "-" + conditions.joinToString("-")

        var currStream = upStream.map { CacheWrap(it) }

        val cacheWrap: CacheWrap<T>? = provider.get(key, object : TypeToken<CacheWrap<T>>() {}.type)

        cacheWrap?.let {
            //以防doOnNext时进行重复缓存
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