package cn.zdh.mvp.presenter;


import android.util.Log;

import java.util.List;

import cn.zdh.mvp.bean.GirlBean;
import cn.zdh.mvp.model.GirlModel;
import cn.zdh.mvp.rxBus.RegisterRxBus;
import cn.zdh.mvp.view.IGirlView;

/**
 * p层
 * @param <T> IHomeView  不同表示层 继承不同view接口
 */
public class GirlPresenter<T extends IGirlView> extends BasePresenter<T> {

    @Override
    protected void loadModelData() {
        new GirlModel().loadData();
    }


    /**
     * @param list 接受的数据类
     * @RegisterRxBus 注解不区分接受对象就不用写标记，如果要区分就要发送和接受标记一致
     */
    @RegisterRxBus
    private void getShowGirlsData(List<GirlBean> list) {
        //把model层数据回调费view层
        iBaseView.get().getGirlData(list);

        Log.e("zdh","-------------GirlsData");
    }


}
