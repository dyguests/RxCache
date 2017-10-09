package com.fanhl.rxcache;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import io.reactivex.ObservableTransformer;

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
    public static <T> ObservableTransformer<T, T> cache(@NotNull String name, String... conditions) {
        return RxCache.INSTANCE.cache(name, conditions, null);
    }

    @NotNull
    public static <T> ObservableTransformer<T, T> cache(@NotNull String name, Type type, String... conditions) {
        return RxCache.INSTANCE.cache(name, conditions, type);
    }

}
