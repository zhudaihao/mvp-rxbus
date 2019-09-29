package cn.zdh.mvp.presenter;

import java.lang.ref.WeakReference;

import cn.zdh.mvp.view.IBaseView;


public abstract class BasePresenter<T extends IBaseView> {

    public BasePresenter() {
        loadModelData();
    }

    /**
     * model层调用model数据方法
     */
    protected abstract void loadModelData();

    //持有view对象
    //IGirlView iBaseView;
    //使用引用来解决持有activity对象导致的内存泄漏问题
    protected WeakReference<T> iBaseView;


    /**
     * 模拟fragment绑定activity ，实现表示层绑定 view和解绑vie，实现Gc扫描，表示层和view层解绑
     *
     * @param view
     */
    public void attachView(T view) {
        iBaseView = new WeakReference<>(view);
    }

    /**
     * 解绑view
     */
    public void detachView() {
        if (iBaseView != null) {
            iBaseView.clear();
            iBaseView = null;
        }
    }



}
