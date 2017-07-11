package com.fanhl.rxcache

import android.content.Context
import com.google.gson.reflect.TypeToken
import io.reactivex.ObservableTransformer

/**
 * desc:
 * date: 2017/7/10
 *
 * @author fanhl
 */
object RxCache {
    var provider: ICacheProvider? = null

    fun init(context: Context) {
//        provider = DefaultCacheProvider(context)
    }

    fun <T> cache(
            name: String,
            vararg conditions: String
    ) = ObservableTransformer<T, T> { upStream ->
        checkInit()

        val key = name + "-" + conditions.joinToString("-")

        var currStream = upStream.map { CacheWrap(it) }

        val cacheWrap: CacheWrap<T>? = provider!!.get(key, object : TypeToken<CacheWrap<T>>() {}.type)

        cacheWrap?.let {
            //以防doOnNext时进行重复缓存
            it.type = CacheWrap.Type.FromCache
            currStream = currStream.startWith(it)
        }

        currStream.doOnNext {
            if (it.type != CacheWrap.Type.FromCache) {
                provider!!.put(key, it)
            }
        }.map { it.data }
    }

    private fun checkInit() {
        if (provider == null) {
            throw Exception("""Provider not init.
may be you forget call RxCache.init().
Remember call init() before cache().
Please call init() in YourApplication.""")
        }
    }
}