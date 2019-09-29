package cn.zdh.isolation_network_processor.realSubject;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.zdh.isolation_network_processor.callback.ICallback;
import cn.zdh.isolation_network_processor.subject.IHttpProcessor;

public class RetrofitProcessor implements IHttpProcessor {
    @Override
    public void get(@NonNull String url, @NonNull Map map, @NonNull ICallback iCallBack) {

    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        //在这里用已经写好的代码来访问网络

        //从参数map保存到新建的map里面
        Map<String, Object> map = new HashMap<>();
        Set<Map.Entry<String, Object>> entries = params.entrySet();

        for (Map.Entry<String, Object> entry : entries) {

            String key = entry.getKey();
            Object value = entry.getValue();
            map.put(key, value);
        }

        //使用retrofit框架API


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
