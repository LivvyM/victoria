package cc.livvy.widget.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cc.livvy.widget.R;

/**
 * Created by Administrator on 2017/11/21 0021.
 */

public class JcToastView {
    private Toast mToast;

    private JcToastView(Context context, CharSequence title, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView mTextTitle = v.findViewById(R.id.mTextTitle);
        TextView mTextContent = v.findViewById(R.id.mTextContent);
        mTextTitle.setText(text);
        mTextContent.setText(title);
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
        /*
         * 默认居中
         */
        setGravity(Gravity.CENTER, 0, 0);
    }

    public static JcToastView makeText(Context context, CharSequence title, CharSequence text, int duration) {
        return new JcToastView(context, title, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}
