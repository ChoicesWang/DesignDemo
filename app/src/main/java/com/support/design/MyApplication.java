package com.support.design;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.support.design.common.Flog;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Application
 * Created by Choices on 2015/5/20.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Flog是自定义Log
        if (BuildConfig.DEBUG) {
            Flog.DebugTree tree = new Flog.DebugTree();
            tree.setShowLine(false); // 是否打印类名和行号
            Flog.plant(tree);
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        //配置Fresco
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                .build();
        Fresco.initialize(this, config);

        Glide.get(this).register(GlideUrl.class, InputStream.class,
                new OkHttpUrlLoader.Factory(okHttpClient));
    }
}
