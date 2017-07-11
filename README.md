#RxCache

A cache mechanism with RxJava2.coding by kotlin.

一套简单的基于rxJava2的缓存机制。使用kotlin编写。

#Usage

##Step1

Add `RxCache.init(this)` in YourApplication.onCreate()

##Step2

Use it in Rx.

```
    .rcCache("KEY", "CONDITION1", "CONDITION2", ...)
```

eg.

```kotlin
    private fun refreshData() {
        Observable
                .create<String> {
                    Thread.sleep(1000)
                    it.onNext("io data")
                }
                .subscribeOn(Schedulers.io())
                .rcCache("KEY", "id123", "car233")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { print(it) }
    }
```

#API

```kotlin
fun <T> Observable<T>.rcCache(
        name: String,
        vararg conditions: String
)
```

#Logic

###Before:

    ---------------------------- io data ----------------------->

###After:

    -- cache data -------------- io data ----------------------->
    ---- ↑load↑ ----------------- ↓save↓ ----------------------->
    - cache cache  cache  cache  cache  cache  cache  cache -----

#License

MIT © dyguests