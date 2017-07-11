package com.fanhl.rxcache

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.Type


class SharePreferenceCacheProvider(val context: Context) : ICacheProvider {
    private val sharedPreferences by lazy { context.getSharedPreferences(DEFAULT_PREF_FILE_NAME, Context.MODE_PRIVATE) }

    override fun <T> get(key: String, type: Type): T? {
        val value = sharedPreferences.getString(key, null)
        return Gson().fromJson<T>(value, type)
    }

    override fun <T> put(key: String, it: T) {
        val value = Gson().toJson(it)
        sharedPreferences.edit().putString(key, value).apply()
    }

    companion object {
        private const val DEFAULT_PREF_FILE_NAME = "rxCache"
    }
}