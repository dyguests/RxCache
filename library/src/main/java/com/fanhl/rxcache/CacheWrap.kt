package com.fanhl.rxcache

data class CacheWrap<out T>(
        val data: T,
        var type: Type = CacheWrap.Type.Other

) {
    enum class Type {
        FromCache,
        Other
    }
}