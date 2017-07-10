package com.fanhl.rxcache

import com.google.gson.Gson
import io.reactivex.ObservableTransformer

/**
 * desc:
 * date: 2017/7/10
 *
 * @author fanhl
 */
object RxCache {
    val provider: ICacheProvider = DefaultCacheProvider()

    fun <T> cache(key: String, clazz: Class<T>) = ObservableTransformer<T, T> { upStream ->
        var currStream = upStream

        val cacheData = provider.get(key, clazz)

        cacheData?.let { currStream = currStream.startWith(it) }

        currStream.doOnNext { provider.put(key, it) }
    }
}

class DefaultCacheProvider : ICacheProvider {
    override fun <T> get(key: String, clazz: Class<T>): T? {
        return Gson().fromJson<T>(cacheStr, clazz)
    }

    override fun <T> put(key: String, it: T) {
        cacheStr = Gson().toJson(it)
    }

    companion object {
        var cacheStr: String? = null
    }
}

interface ICacheProvider {
    fun <T> get(key: String, clazz: Class<T>): T?

    fun <T> put(key: String, it: T)
}
