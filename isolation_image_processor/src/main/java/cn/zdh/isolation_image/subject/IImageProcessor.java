package cn.zdh.isolation_image.subject;

import android.widget.ImageView;

import java.util.Map;


/**
 * 中介公司
 * 代理公司
 */
public interface IImageProcessor {

    void load(Object imageUrl, Map<String, Object> map, ImageView imageView);

}
