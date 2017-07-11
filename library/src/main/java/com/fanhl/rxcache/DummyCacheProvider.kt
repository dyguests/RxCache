package com.fanhl.rxcache

import com.google.gson.Gson
import java.lang.reflect.Type

class DummyCacheProvider : ICacheProvider {
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