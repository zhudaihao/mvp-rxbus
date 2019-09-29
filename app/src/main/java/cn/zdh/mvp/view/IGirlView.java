package cn.zdh.mvp.view;


import java.util.List;

import cn.zdh.mvp.bean.GirlBean;

/**
 * view层 逻辑抽离层接口，具体实现交给表示层
 */
public interface IGirlView  extends IBaseView{

    void getGirlData(List<GirlBean> girlBeans);

}
