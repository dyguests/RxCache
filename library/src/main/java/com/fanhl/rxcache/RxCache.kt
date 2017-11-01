package com.fanhl.rxcache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken
import io.reactivex.ObservableTransformer

/**
 * desc:
 * date: 2017/7/10
 *
 * @author fanhl
 */
object RxCache {
    /** 已初始化过了 */
    var inited = false
    var provider: ICacheProvider? = null
    /**
     * This method should be called in Application.onCreate() only once.
     */
    fun init(context: Context) {
        checkInited()
        provider = SharePreferenceCacheProvider(context)
    }

    /**
     * This method should be called in Application.onCreate() only once.
     */
    fun init(sharedPreferences: SharedPreferences) {
        checkInited()
        provider = SharePreferenceCacheProvider(sharedPreferences)
    }

    inline fun <reified T> cache(
            name: Any,
            vararg conditions: Any
    ) = ObservableTransformer<T, T> { upStream ->
        checkInit()

        val key = arrayOf(name, *conditions).joinToString("-")

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

    fun checkInit() {
        if (provider == null) {
            throw Exception("""Provider not init.
may be you forget call RxCache.init().
Remember call init() before cache().
Please call init() in YourApplication.""")
        }
    }

    private fun checkInited() {
        if (!inited) {
            inited = true
        } else {
            throw Exception("""RxCache can only init once.""")
        }
    }

}