package cn.zdh.isolation_network_processor.realSubject;

import android.content.Context;
import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import cn.zdh.isolation_network_processor.callback.ICallback;
import cn.zdh.isolation_network_processor.subject.IHttpProcessor;


public class VolleyProcessor implements IHttpProcessor {

    private static RequestQueue mQueue = null;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(@NonNull String url, @NonNull Map<String, Object> map, @NonNull ICallback iCallBack) {

    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (callback != null) {
                            callback.onNetSuccess(response);
                            callback.onNetEnd();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callback != null) {
                    callback.onNetEnd();
                    callback.onNetFailure(error.getMessage());
                }
            }
        });

        if (callback != null) {
            callback.onNetStart();
        }
        mQueue.add(stringRequest);


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










