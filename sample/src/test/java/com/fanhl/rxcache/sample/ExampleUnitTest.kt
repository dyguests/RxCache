package com.fanhl.rxcache.sample

import com.fanhl.rxcache.RxCache
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        Observable.just("dummy data", "dummy data2")
                .compose(RxCache.cache("KEY"))
                .subscribe { println(it) }
    }
}
