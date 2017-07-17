package com.fanhl.rxcache

import com.google.gson.Gson
import java.lang.reflect.Type

class DummyCacheProvider : ICacheProvider {
    override fun <T> get(key: String, type: Type): T? {
        return Gson().fromJson<T>(cacheStr, type)
    }

    override fun <T> put(key: String, obj: T) {
        cacheStr = Gson().toJson(obj)
    }

    companion object {
        var cacheStr: String? = null
    }
}