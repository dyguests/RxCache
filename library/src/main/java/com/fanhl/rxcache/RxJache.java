package com.fanhl.rxcache;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import rx.Observable;

/**
 * desc:
 * date: 2017/7/11
 *
 * @author fanhl
 */
public class RxJache {
    public static void init(@NotNull Context context) {
        RxCache.INSTANCE.init(context);
    }

    @NotNull
    public static <T> Observable.Transformer<T, T> cache(@NotNull String name, String... conditions) {
        return RxCache.INSTANCE.cache(name, conditions);
    }
}
