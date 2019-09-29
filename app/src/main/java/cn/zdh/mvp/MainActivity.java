package cn.zdh.mvp;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cn.zdh.mvp.adapter.GirlAdapter;
import cn.zdh.mvp.bean.GirlBean;
import cn.zdh.mvp.presenter.GirlPresenter;
import cn.zdh.mvp.view.BaseActivity;
import cn.zdh.mvp.view.IGirlView;


/**
 * mvp的view层
 */
public class MainActivity extends BaseActivity<GirlPresenter<IGirlView>, IGirlView> implements IGirlView {
    private ListView listView;
    private GirlAdapter girlAdapter;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }



    @Override
    protected void init() {
        super.init();
        listView = findViewById(R.id.lv);
        //点击item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(MainActivity.this,HomeActivity.class));

            }
        });
    }

    /**
     * 创建对应表示层
     */
    @Override
    protected GirlPresenter<IGirlView> createPresenter() {
        return new GirlPresenter();
    }


    /**
     * activity需要获取哪个结果，就实现对应接口;例如实现IGirlView接口
     *
     * @param girlBeans 需要的数据对象
     */
    @Override
    public void getGirlData(List<GirlBean> girlBeans) {
        girlAdapter = new GirlAdapter(girlBeans, getApplicationContext());
        listView.setAdapter(girlAdapter);
    }


}
