package com.carryondown.app.util;
import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.carryondown.app.R;
import com.lzy.imagepicker.loader.ImageLoader;
import java.io.File;

public class GlideImagePickerLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        RequestOptions options = new RequestOptions()
                .error(R.drawable.default_image)           //设置错误图片
                .placeholder(R.drawable.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL);//缓存全尺寸
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
