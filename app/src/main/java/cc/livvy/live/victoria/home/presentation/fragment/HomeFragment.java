package cc.livvy.live.victoria.home.presentation.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cc.livvy.live.victoria.R;
import cc.livvy.live.victoria.base.BaseFragment;
import cc.livvy.widget.image.ImageViewUtils;

/**
 * discover
 * <p>
 * Created by livvy on 17-11-30.
 */

public class HomeFragment extends BaseFragment {

    private View rootView;
    private boolean isFirst = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
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
        mTextTitle.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),"font/font_default.ttf"));

        ImageView mImageBanner = view.findViewById(R.id.mImageBanner);
        ImageViewUtils.bindImageView(mImageBanner,"http://img.hb.aicdn.com/361368b552300e465a5c43482934959b2c509b458a3b-YBd44R");

        ImageView mImageCard1 = view.findViewById(R.id.mImageCard1);
//        ImageView mImageCard2 = view.findViewById(R.id.mImageCard2);
        ImageView mImageCard3 = view.findViewById(R.id.mImageCard3);
        ImageView mImageCard4 = view.findViewById(R.id.mImageCard4);
        ImageViewUtils.bindImageView(mImageCard1,"http://img.hb.aicdn.com/c254f95e04b4aac0d48e0f7d21440dc4d47d5648350ed-rbQmeD");
//        ImageViewUtils.bindImageView(mImageCard2,"http://img.hb.aicdn.com/218c6ccf19e75bd6d30dec7272eb2bd63a62efc42927c-WL82Yg");
        ImageViewUtils.bindImageView(mImageCard3,"http://img.hb.aicdn.com/218c6ccf19e75bd6d30dec7272eb2bd63a62efc42927c-WL82Yg");
        ImageViewUtils.bindImageView(mImageCard4,"http://img.hb.aicdn.com/f220c8f9965253238c1e61871c51c003a4b2d2bf174be-GgqZJq");

    }
}
