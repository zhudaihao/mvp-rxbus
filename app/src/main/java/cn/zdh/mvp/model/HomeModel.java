package cn.zdh.mvp.model;

import java.util.ArrayList;
import java.util.List;

import cn.zdh.mvp.bean.GirlBean;
import cn.zdh.mvp.rxBus.RxBus;
import io.reactivex.functions.Function;

public class HomeModel {


    //加载数据方法
    public void loadData() {
        //发送数据
        RxBus.getInstance().chainProcess("homeData", new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                //获取数据
                List<GirlBean> list = new ArrayList();
                GirlBean homeBean1 = new GirlBean("https://www.baidu.com/");
                GirlBean homeBean2 = new GirlBean("https://ai.taobao.com/?pid=mm_26632323_6762370_107181600323&clk1=b2f7aac6f31cd94f179da92a25668a37");
                list.add(homeBean1);
                list.add(homeBean2);
                return list;
            }
        });
    }


}
