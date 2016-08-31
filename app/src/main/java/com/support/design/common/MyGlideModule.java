package com.support.design.common;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpGlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.facebook.common.util.ByteConstants;

public class MyGlideModule extends OkHttpGlideModule {
    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        builder.setDiskCache(
                new DiskCache.Factory() {
                    @Override
                    public DiskCache build() {
                        return DiskLruCacheWrapper.get(
                                Glide.getPhotoCacheDir(context),
                                ConfigConstants.MAX_DISK_CACHE_SIZE);
                    }
                });
        builder.setMemoryCache(new LruResourceCache(ConfigConstants.MAX_MEMORY_CACHE_SIZE));
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        super.registerComponents(context, glide);
    }

    public static class ConfigConstants {
        public static final int MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB;
        private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
        public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
    }
}