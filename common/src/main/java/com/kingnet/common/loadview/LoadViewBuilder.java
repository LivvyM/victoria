package com.kingnet.common.loadview;

import android.support.annotation.IntDef;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.kingnet.common.loadview.LoadViewBuilder.Type.TYPE_LOAD_NORMAL;

/**
 *
 * Created by livvy on 17-3-15.
 */

public class LoadViewBuilder {


    private IVaryViewHelper helper;
    private @LoadViewBuilder.Type.TYPE int type = TYPE_LOAD_NORMAL;
    private String text;
    private View.OnClickListener listener;

    public LoadViewBuilder setView(View view){
        helper = new VaryViewHelper(view);
        return this;
    }

    public LoadViewBuilder setType(@LoadViewBuilder.Type.TYPE int type){
        this.type = type;
        return this;
    }

    public LoadViewBuilder setText(String text){
        this.text = text;
        return this;
    }

    public LoadViewBuilder setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
        return this;
    }

    public LoadView build(){
        return new LoadView(this);
    }


    @Type.TYPE
    int getType(){
        return type;
    }

    String getText(){
        return text;
    }

    View.OnClickListener getOnClickListener(){
        return listener;
    }

    IVaryViewHelper getHelper(){
        return helper;
    }

    public static class Type{

        @IntDef({TYPE_LOAD_ING,TYPE_LOAD_ERROR,TYPE_LOAD_EMPTY,TYPE_LOAD_NORMAL})
        @Retention(RetentionPolicy.RUNTIME)
        @interface TYPE{}

        public static final int TYPE_LOAD_NORMAL = 0;
        public static final int TYPE_LOAD_ING = 1;
        public static final int TYPE_LOAD_ERROR = 2;
        public static final int TYPE_LOAD_EMPTY = 3;
    }
}
