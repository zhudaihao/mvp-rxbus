package cn.zdh.isolation_network_processor.subjectProxy;

import androidx.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import cn.zdh.isolation_network_processor.callback.ICallback;
import cn.zdh.isolation_network_processor.subject.IHttpProcessor;


/**
 * 代理类（业务员）
 */
public class HttpHelper implements IHttpProcessor {

    /**
     * 单利
     */
    private static volatile HttpHelper instance;

    private HttpHelper() {
    }

    public static synchronized HttpHelper obtain() {
        if (instance == null) {
            synchronized (HttpHelper.class) {
                if (instance == null) {
                    instance = new HttpHelper();
                }
            }
        }
        return instance;
    }


    /**
     * 持有业主的父类（房产公司）
     */
    private static IHttpProcessor mHttpProcessor;

    //定义一个API,用来设置代码的接口（业务员找到对应的的有房人）
    public static void init(IHttpProcessor iHttpProcessor) {
        mHttpProcessor = iHttpProcessor;
    }


    /**
     * ---------------------------------------------------------------------------------------------
     * 代理具体逻辑
     * 买房操作
     *
     * @param url
     * @param map
     * @param iCallBack
     */

    @Override
    public void get(@NonNull String url, @NonNull Map<String, Object> map, @NonNull ICallback iCallBack) {
        //调用网络请求
        mHttpProcessor.get(url, map, iCallBack);
    }


    @Override
    public void post(String url, Map<String, Object> map, ICallback iCallBack) {
        //兼容 发post请求 使用get请求 （post转get）
        String urlPath = appendParams(url, map);

        //调用网络请求
        mHttpProcessor.post(urlPath, map, iCallBack);
    }

    @Override
    public void upload(@NonNull String url, @NonNull String filePath, @NonNull ICallback iCallBack) {
        //调用网络请求
        mHttpProcessor.upload(url, filePath, iCallBack);
    }


    @Override
    public void download(@NonNull String url, @NonNull ICallback iCallBack) {
        //调用网络请求
        mHttpProcessor.download(url, iCallBack);
    }

    @Override
    public void download(@NonNull String url, @NonNull Map<String, Object> params, @NonNull ICallback iCallBack) {
        //调用网络请求
        mHttpProcessor.download(url, params, iCallBack);
    }


    /**
     * post转get方法
     * <p>
     * "http：wwww.123/1222" "name=张三" "pws=123"  --转换----》"http：wwww.123/1222?name=张三&pws=123"
     */

    public static String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        if (urlBuilder.indexOf("?") <= 0) {
            urlBuilder.append("?");
        } else {
            if (!urlBuilder.toString().endsWith("?")) {
                urlBuilder.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlBuilder.append("&" + entry.getKey())
                    .append("=")
                    .append(encode(entry.getValue().toString()));
        }
        return urlBuilder.toString();
    }

    private static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


}
