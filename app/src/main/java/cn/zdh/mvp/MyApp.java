package cn.zdh.mvp;

import android.app.Application;

import cn.zdh.isolation_image.realSubject.PicassoProcessor;
import cn.zdh.isolation_image.subjectProxy.ImageHelper;
import cn.zdh.isolation_network_processor.realSubject.VolleyProcessor;
import cn.zdh.isolation_network_processor.subjectProxy.HttpHelper;


public class MyApp extends Application {
    private static volatile MyApp myApp;

    public static MyApp getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

        //一行代码切换网络框架
        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new OkHttpProcessor());
//        HttpHelper.init(new XUtilsProcessor(this));


        //一行代码切换图片加载框架
//        ImageHelper.init(new GlideProcessor(this));
        ImageHelper.init(new PicassoProcessor(this));

//        ImageHelper.init(new FresoProcessor(this));


    }
}
