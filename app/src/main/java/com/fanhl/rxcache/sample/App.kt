package com.fanhl.rxcache.sample

import android.app.Application
import android.preference.PreferenceManager
import com.fanhl.rxcache.RxCache

/**
 * desc:
 * date: 2017/7/11

 * @author fanhl
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

//        RxCache.init(this)
        RxCache.init(PreferenceManager.getDefaultSharedPreferences(this))
    }
}
