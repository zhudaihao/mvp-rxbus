package cn.zdh.mvp.presenter;


import android.util.Log;

import java.util.List;

import cn.zdh.mvp.bean.GirlBean;
import cn.zdh.mvp.model.HomeModel;
import cn.zdh.mvp.rxBus.RegisterRxBus;
import cn.zdh.mvp.view.IHomeView;

/**
 * @param <T> IHomeView  不同表示层 继承不同view接口
 */
public class HomePresenter<T extends IHomeView> extends BasePresenter<T> {


    /**
     * 调model加载数据方法
     */
    @Override
    protected void loadModelData() {
        new HomeModel().loadData();
    }


    /**
     * 通过注解获取model数据
     * 如果接受数据和其他表示层一样就需要tag区分（发送和接受的tag必须一致）
     */
    @RegisterRxBus("homeData")
    private void getShowHomeData(List<GirlBean> list) {
        //回调view层接口，返回数据到view层
        iBaseView.get().getHomeData(list);
        Log.e("zdh", "-------------homeData");
    }


}
