package cn.zdh.isolation_network_processor.realSubject;


import android.app.Application;
import androidx.annotation.NonNull;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

import cn.zdh.isolation_network_processor.callback.ICallback;
import cn.zdh.isolation_network_processor.subject.IHttpProcessor;


public class XUtilsProcessor implements IHttpProcessor{
    public XUtilsProcessor(Application app) {
        x.Ext.init(app);
    }

    @Override
    public void get(@NonNull String url, @NonNull Map<String, Object> map, @NonNull ICallback iCallBack) {

    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        RequestParams requestParams = new RequestParams(url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onNetSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void upload(@NonNull String url, @NonNull String filePath, @NonNull ICallback iCallBack) {

    }

    @Override
    public void download(@NonNull String url, @NonNull ICallback iCallBack) {

    }

    @Override
    public void download(@NonNull String url, @NonNull Map<String, Object> params, @NonNull ICallback iCallBack) {

    }


}










