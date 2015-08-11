package com.support.design;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;
import com.support.design.common.Flog;

/**
 * Application
 * Created by Choices on 2015/5/20.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Flog.DebugTree tree = new Flog.DebugTree();
            tree.setShowLine(false); // 是否打印类名和行号
            Flog.plant(tree);
        }

        //配置Fresco
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, new OkHttpClient())
                .build();
        Fresco.initialize(this, config);
    }
}
