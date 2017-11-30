package cc.livvy.live.victoria.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by livvy on 17-11-30.
 */

public class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof BaseActivity) {
            this.mActivity = (BaseActivity) activity;
        } else {
            throw new ClassCastException(activity.toString() + "must extends SupportActivity!");
        }
    }

}
