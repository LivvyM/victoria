package cc.livvy.live.victoria.mine.presentation.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cc.livvy.live.victoria.R;
import cc.livvy.live.victoria.app.presentation.CameraActivity;
import cc.livvy.live.victoria.base.BaseFragment;
import cc.livvy.widget.image.ImageViewUtils;

/**
 * Created by livvy on 17-12-1.
 */

public class MineFragment extends BaseFragment {

    private View rootView;
    private boolean isFirst = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        } else {
            isFirst = false;
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isFirst) {
            initView(view);
        }
    }

    private void initView(View view) {
        TextView mTextTitle = view.findViewById(R.id.mTextTitle);
        TextView mTextName = view.findViewById(R.id.mTextName);
        mTextTitle.setTypeface(Typeface.createFromAsset(mActivity.getAssets(), "font/font_default.ttf"));
        mTextName.setTypeface(Typeface.createFromAsset(mActivity.getAssets(), "font/font_default.ttf"));

        ImageView mImageHeader = view.findViewById(R.id.mImageHeader);
        ImageView mImageLive = view.findViewById(R.id.mImageLive);
        ImageViewUtils.bindCircleImageView(mImageHeader, "http://img3.imgtn.bdimg.com/it/u=3067862065,1178074544&fm=27&gp=0.jpg");

        mImageLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity, CameraActivity.class));
            }
        });


    }
}
