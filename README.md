# RxCache

[![](https://jitpack.io/v/dyguests/RxCache.svg)](https://jitpack.io/#dyguests/RxCache)

A cache mechanism with RxJava2,save in SharePreference.coding by kotlin.

一套简单的基于rxJava2的缓存机制，缓存保存存在SharePreference中。使用kotlin编写。

**已解决泛型擦除的问题，工发者不用管泛型的问题。**

## Need use in RxJava1？See [dyguests/RxCache/tree/rxJava1](https://github.com/dyguests/RxCache/tree/rxJava1)

# Dependency

## Step 1. Add the JitPack repository to your build file

Add it in your root `build.gradle` at the end of repositories:

```groovy
    allprojects {
        repositories {
            //...
            maven { url 'https://jitpack.io' }
        }
    }
```

## Step 2. Add the dependency

### For RxJava2

Add it in your app module `build.gradle` at the end of repositories:

```groovy
    dependencies {
            compile 'com.github.dyguests:RxCache:0.2.0'
    }
```

# Usage

## Step1

Add `RxCache.init(this)` in YourApplication.onCreate()

## Step2

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

# API

```kotlin
fun <T> Observable<T>.rcCache(
        name: String,
        vararg conditions: String
)
```

# Logic

### Before:

    ---------------------------- io data ----------------------->

### After:

    -- cache data -------------- io data ----------------------->
    ---- ↑load↑ ----------------- ↓save↓ ----------------------->
    - cache cache  cache  cache  cache  cache  cache  cache -----

# TODO

- [x] RxJava1
- [x] RxCacheJava
- [x] 解决泛型擦除问题

# License

MIT © dyguests