package cn.zdh.isolation_network_processor.subject;

import androidx.annotation.NonNull;

import java.util.Map;

import cn.zdh.isolation_network_processor.callback.ICallback;


/**
 * 代理
 * 房产公司
 */
public interface IHttpProcessor {

    /**
     * 网络操作 get post del update pu ...
     */
    void get(@NonNull String url, @NonNull Map<String, Object> map, @NonNull ICallback iCallBack);


    void post(@NonNull String url, @NonNull Map<String, Object> map, @NonNull ICallback iCallBack);

    //上传
    void upload(@NonNull String url, @NonNull String filePath, @NonNull ICallback iCallBack);

    void download(@NonNull String url, @NonNull ICallback iCallBack);

    void download(@NonNull String url, @NonNull Map<String, Object> params, @NonNull ICallback iCallBack);


}
