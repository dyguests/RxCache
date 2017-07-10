package com.fanhl.rxcache

import java.lang.reflect.Type

interface ICacheProvider {

    fun <T> get(key: String, type: Type): T?

    fun <T> put(key: String, it: T)
}