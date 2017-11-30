package cc.livvy.live.victoria.app.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import cc.livvy.live.victoria.R;
import cc.livvy.live.victoria.base.BaseActivity;
import cc.livvy.live.victoria.home.presentation.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    protected FragmentTabHost fragmentTabHost;

    private String mTextTitle[] = {"home", "discover", "live", "message", "mine"};
    private int mDrawableSelector[] = {
            R.drawable.main_tab_home_selector,
            R.drawable.main_tab_home_selector,
            R.drawable.main_tab_home_selector,
            R.drawable.main_tab_home_selector,
            R.drawable.main_tab_home_selector
    };

    private Class mFragmentArray[] = {HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.mLayoutContent);
        for (int i = 0; i < mDrawableSelector.length; i++) {
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec(mTextTitle[i]).setIndicator(getView(i));
            fragmentTabHost.addTab(spec, mFragmentArray[i], null);
        }
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
    }

    private View getView(int i) {
        View view = View.inflate(MainActivity.this, R.layout.tab_bottom_menu, null);
        ImageView imageView = view.findViewById(R.id.mImageIcon);
        imageView.setImageResource(mDrawableSelector[i]);
        return view;
    }
}
