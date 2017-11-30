package com.kingnet.common.loadview;

import android.content.Context;
import android.view.View;

/**
 *
 * Created by livvy on 17-3-15.
 */

public interface IVaryViewHelper {

    View getCurrentLayout();

    void restoreView();

    void showLayout(View view);

    void showLayout(int layoutId);

    View inflate(int layoutId);

    Context getContext();

    View getView();
}
