package cn.zdh.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.zdh.isolation_image.subjectProxy.ImageHelper;
import cn.zdh.mvp.R;
import cn.zdh.mvp.bean.GirlBean;


public class GirlAdapter extends BaseAdapter {

    private List<GirlBean> list;
    private Context context;


    public GirlAdapter(List<GirlBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_girl, null);
            viewHolder = new ViewHolder(convertView);

            //复用 把布局封装类保存到view里面
            convertView.setTag(viewHolder);
        } else {
            //从view获取布局封装类
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //赋值
        ImageHelper.obtain().load("https://ps.ssl.qhimg.com/sdmt/224_162_100/t0187f7f6d0515b56ae.webp",null,viewHolder.imageView);
//        viewHolder.imageView.setImageResource(list.get(position).getImage());
        viewHolder.textView.setText(list.get(position).getName());


        return convertView;
    }


    public class ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View convertView) {
            imageView = convertView.findViewById(R.id.iv);
            textView = convertView.findViewById(R.id.tv);


        }
    }


}
