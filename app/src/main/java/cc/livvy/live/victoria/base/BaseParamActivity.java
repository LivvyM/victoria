package cc.livvy.live.victoria.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by livvy on 17-11-30.
 */

public abstract class BaseParamActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null && getIntent().getExtras() != null) {
            onInitParams(getIntent().getExtras());
        } else {
            onInitParams(new Bundle());
        }
    }

    protected abstract void onInitParams(Bundle bundle);
}
