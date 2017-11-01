package com.fanhl.rxcache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.Type


class SharePreferenceCacheProvider() : ICacheProvider {
    private var sharedPreferences: SharedPreferences? = null

    constructor(context: Context) : this() {
        this.sharedPreferences = context.getSharedPreferences(DEFAULT_PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    constructor(sharedPreferences: SharedPreferences) : this() {
        this.sharedPreferences = sharedPreferences
    }


    override fun <T> get(key: String, type: Type): T? {
        val value = sharedPreferences!!.getString(key, null)
        return Gson().fromJson<T>(value, type)
    }

    override fun <T> put(key: String, obj: T) {
        val value = Gson().toJson(obj)
        sharedPreferences!!.edit().putString(key, value).apply()
    }

    companion object {
        private const val DEFAULT_PREF_FILE_NAME = "rxCache"
    }
}