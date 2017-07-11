# RxCache

[![](https://jitpack.io/v/dyguests/RxCache.svg)](https://jitpack.io/#dyguests/RxCache)

A cache mechanism with RxJava2.coding by kotlin.

一套简单的基于rxJava2的缓存机制。使用kotlin编写。

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
            compile 'com.github.dyguests:RxCache:0.1.0'
    }
```

# Usage

## Step1

### For kotlin

Add `RxCache.init(this)` in YourApplication.onCreate()

### For Java

Point: Rx**J**ache not RxCache.

Add `RxJache.init(this)` in YourApplication.onCreate()

## Step2

Use it in Rx.

### For kotlin

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

### For Java

Point: Rx**J**ache not RxCache.

```
    .compose(RxJache.cache("KEY", "id123", "car233"))
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

- [ ] RxJava1
- [ ] RxCacheJava

# License

MIT © dyguests