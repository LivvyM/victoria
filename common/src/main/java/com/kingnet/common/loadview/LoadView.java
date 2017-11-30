package com.kingnet.common.loadview;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.kingnet.common.R;

import static com.kingnet.common.loadview.LoadViewBuilder.Type.TYPE_LOAD_EMPTY;
import static com.kingnet.common.loadview.LoadViewBuilder.Type.TYPE_LOAD_ERROR;
import static com.kingnet.common.loadview.LoadViewBuilder.Type.TYPE_LOAD_ING;
import static com.kingnet.common.loadview.LoadViewBuilder.Type.TYPE_LOAD_NORMAL;


/**
 * 自定义要切换的布局，通过IVaryViewHelper实现真正的切换<br>
 * 使用者可以根据自己的需求，使用自己定义的布局样式
 *
 * Created by livvy on 17-3-15.
 * @author livvy
 */
public class LoadView {

    private View layout;
    private IVaryViewHelper helper;

    public static LoadViewBuilder newLoadView(){
        return new LoadViewBuilder();
    }

    LoadView(@NonNull LoadViewBuilder builder){
        if(builder.getHelper() == null){
            throw new NullPointerException("build helper is null,build helper must be init");
        }
        helper = builder.getHelper();
        @LoadViewBuilder.Type.TYPE int type = builder.getType();
        switch (type){
            case TYPE_LOAD_ING:
                layout = builder.getHelper().inflate(R.layout.layout_load_ing);
                TextView mTextLoading = (TextView) layout.findViewById(R.id.mTextLoading);
                String text = builder.getText();
                if(null == text){
                    text = "数据加载中..";
                }
                mTextLoading.setText(text);
                break;
            case TYPE_LOAD_ERROR:
                layout = builder.getHelper().inflate(R.layout.layout_load_error);
                TextView mTextError = (TextView) layout.findViewById(R.id.mTextError);
                text = builder.getText();
                if(null == text){
                    text = "数据加载错误";
                }
                mTextError.setText(text);
                break;
            case TYPE_LOAD_EMPTY:
                layout = builder.getHelper().inflate(R.layout.layout_load_empty);
                TextView mTextEmpty = (TextView) layout.findViewById(R.id.mTextEmpty);
                text = builder.getText();
                if(null == text){
                    text = "暂时没有数据哦";
                }
                mTextEmpty.setText(text);
                break;
            case TYPE_LOAD_NORMAL:
                throw new NullPointerException("build type is null,build type must be init");
            default:
                break;
        }
    }

    public void restore() {
        if(helper != null){
            helper.restoreView();
        }
    }

    public void show(){
        if(helper != null && layout != null){
            helper.showLayout(layout);
        }
    }

}
