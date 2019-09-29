package cn.zdh.isolation_image.realSubject;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Map;

import cn.zdh.isolation_image.subject.IImageProcessor;


/**
 * 业主
 * <p>
 * 具体主体
 */
public class FresoProcessor implements IImageProcessor {


    public FresoProcessor(Context context) {
        //初始化
        Fresco.initialize(context);
    }


    @Override
    public void load(Object imageUrl, Map<String, Object> map, ImageView imageView) {
        //壁画
        Uri uri = null;
        if (imageUrl instanceof String) {
            uri = Uri.parse((String) imageUrl);
        } else {
            throw new RuntimeException("imageUrl type  no string");
        }

        if (imageView instanceof SimpleDraweeView) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) imageView;
            simpleDraweeView.setImageURI(uri);
        } else {
            try {
                throw new Exception("imageView no is SimpleDraweeView");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
