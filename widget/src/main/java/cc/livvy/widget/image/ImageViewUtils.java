package cc.livvy.widget.image;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.kingnet.common.base.BaseApplication;

import cc.livvy.widget.R;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by livvy on 17-3-29.
 */

public class ImageViewUtils {

    /**
     * 添加圆形imageView
     */
    public static void bindCircleImageView(@NonNull ImageView view, String url) {
        if (url == null) {
            url = "";
        }
        Glide.with(BaseApplication.getInstance())
                .load(url.contains("\\") ? url.replace("\\", "") : url)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(BaseApplication.getInstance()))
                .placeholder(R.drawable.ic_svg_default_logo)
                .into(view);
    }

    /**
     * 添加圆形imageView
     */
    public static void bindCircleImageView(@NonNull ImageView view, int resId) {
        Glide.with(BaseApplication.getInstance())
                .load(resId)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(BaseApplication.getInstance()))
                .placeholder(R.drawable.ic_svg_default_logo)
                .into(view);
    }

    public static void bindImageView(@NonNull ImageView view, String url) {
        Glide.with(BaseApplication.getInstance())
                .load(url.replace("\\", ""))
                .centerCrop()
                .into(view);
    }

    public static void bindRoundImageView(@NonNull ImageView view, String url) {
        Glide.with(BaseApplication.getInstance())
                .load(url)
                .centerCrop()
                .bitmapTransform(new RoundedCornersTransformation(BaseApplication.getInstance(), 2, 0))
                .into(view);
    }
}
