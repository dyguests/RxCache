package com.fanhl.rxcache.sample

import android.app.Application
import com.fanhl.rxcache.RxCache

/**
 * desc:
 * date: 2017/7/11

 * @author fanhl
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()

        RxCache.init(this)
//        RxJache.init(this)
    }
}
