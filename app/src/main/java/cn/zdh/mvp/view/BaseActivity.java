package cn.zdh.mvp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.zdh.mvp.rxBus.RxBus;
import cn.zdh.mvp.presenter.BasePresenter;


public abstract class BaseActivity<T extends BasePresenter, V extends IBaseView> extends AppCompatActivity {

    /**
     * 表示层
     */
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        //初始化方法
        init();

        //选择对应的表示层(注意最新创建表示层)
        presenter = createPresenter();

        //注册线程路由
        RxBus.getInstance().register(presenter);

        //绑定表示层
        if (presenter != null) {
            presenter.attachView((V) this);
        }


    }

    /**
     * 获取布局id方法
     */
    protected abstract int getLayoutResID();

    /**
     * 初始化
     */
    protected void init() {
    }

    /**
     * 具体创建哪个表示层由子类选择，创建
     */
    protected abstract T createPresenter();


    /**
     * 处理 注销，解绑方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑表示层
        if (presenter != null) {
            presenter.detachView();
        }

        //注销线程路由
        RxBus.getInstance().unRegister(presenter);
    }


}
