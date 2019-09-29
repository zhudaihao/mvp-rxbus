package cn.zdh.mvp.model;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.zdh.isolation_network_processor.callback.ICallback;
import cn.zdh.isolation_network_processor.subjectProxy.HttpHelper;
import cn.zdh.mvp.MyApp;
import cn.zdh.mvp.R;
import cn.zdh.mvp.bean.GirlBean;
import cn.zdh.mvp.rxBus.RxBus;
import io.reactivex.functions.Function;

/**
 * model实现类
 */
public class GirlModel {
    //获取数据方法
    public void loadData() {
        //测试隔离层代码
        String url = "https://mbd.baidu.com/";
        final HashMap params = new HashMap();
        params.put("context", "%7B%22nid%22%3A%22news_9665426971658518505%22%7D");
        params.put("n_type", 0);
        params.put("p_from", 1);
        HttpHelper.obtain().post(url, params, new ICallback() {
            @Override
            public void onNetStart() {

            }

            @Override
            public void onNetEnd() {

            }

            @Override
            public void onNetSuccess(String result) {
                Toast.makeText(MyApp.getInstance(), "请求网络成功" , Toast.LENGTH_SHORT).show();
                sendData();
            }

            @Override
            public void onNetFailure(String error) {

            }
        });



    }

    private void sendData() {
        //发送数据
        RxBus.getInstance().chainProcess(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                //获取数据
                List<GirlBean> list = new ArrayList();

                GirlBean girlBean1 = new GirlBean(R.mipmap.t1, "丰收");
                GirlBean girlBean2 = new GirlBean(R.mipmap.t2, "大海");
                GirlBean girlBean3 = new GirlBean(R.mipmap.t3, "山");

                list.add(girlBean1);
                list.add(girlBean2);
                list.add(girlBean3);
                return list;
            }
        });
    }


}
