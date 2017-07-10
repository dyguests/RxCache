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
        var currStream = upStream

        val cacheData: T? = provider.get(key, object : TypeToken<T>() {}.type)

        cacheData?.let { currStream = currStream.startWith(it) }

        currStream.doOnNext {
            provider.put(key, it)
        }
    }
}