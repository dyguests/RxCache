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

        val cacheData: CacheData<T>? = provider.get(key, object : TypeToken<CacheData<T>>() {}.type)

        cacheData?.data?.let { currStream = currStream.startWith(it) }

        currStream.doOnNext {
            provider.put(key, CacheData(it))
        }
    }
}

data class CacheData<T>(
        val data: T,
        val type: Type = CacheData.Type.Other

) {
    enum class Type {
        FromCache,
        Other
    }
}
