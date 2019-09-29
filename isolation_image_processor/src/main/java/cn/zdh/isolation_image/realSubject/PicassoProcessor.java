package cn.zdh.isolation_image.realSubject;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Map;

import cn.zdh.isolation_image.subject.IImageProcessor;


/**
 * 业主
 * 具体主体
 */
public class PicassoProcessor implements IImageProcessor {

    private Context context;

    public PicassoProcessor(Context context) {
        this.context = context;
    }

    @Override
    public void load(Object imageUrl, Map<String, Object> map, ImageView imageView) {
        //毕加索
        if (imageUrl instanceof Uri) {
            Picasso.with(context)
                    .load((Uri) imageUrl)
                    .into(imageView);
        }

        if (imageUrl instanceof String) {
            Picasso.with(context)
                    .load((String) imageUrl)
                    .into(imageView);
        }

        if (imageUrl instanceof File) {
            Picasso.with(context)
                    .load((File) imageUrl)
                    .into(imageView);
        }
        if (imageUrl instanceof Integer) {
            Picasso.with(context)
                    .load((int) imageUrl)
                    .into(imageView);
        }

    }
}
