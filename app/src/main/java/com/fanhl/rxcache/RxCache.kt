package com.fanhl.rxcache

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.lang.reflect.Type

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

        currStream.doOnNext { provider.put(key, it) }
    }
}

fun <T> Observable<T>.rxCache(key: String) = this.compose(RxCache.cache(key))!!

class DefaultCacheProvider : ICacheProvider {
    override fun <T> get(key: String, type: Type): T? {
        return Gson().fromJson<T>(cacheStr, type)
    }

    override fun <T> put(key: String, it: T) {
        cacheStr = Gson().toJson(it)
    }

    companion object {
        var cacheStr: String? = null
    }
}

interface ICacheProvider {

    fun <T> get(key: String, type: Type): T?

    fun <T> put(key: String, it: T)
}
