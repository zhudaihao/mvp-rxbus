package cn.zdh.mvp.model;


import java.util.List;

import cn.zdh.mvp.bean.GirlBean;

/**
 * model层接口
 */
public interface IHomeModel {

    void loadData(IHomeData iHomeData);

    /**
     * 结果用接口回调，结果有很多情况，方便拓展 用接口
     *
     * 表示层需要什么数据就在接口添加方法返回需要数据
     */
    interface IHomeData {
            void getData(List<GirlBean> list);
    }


}
