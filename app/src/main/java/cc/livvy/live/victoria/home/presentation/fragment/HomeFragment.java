package cc.livvy.live.victoria.home.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.livvy.live.victoria.R;
import cc.livvy.live.victoria.base.BaseFragment;

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

    }
}
