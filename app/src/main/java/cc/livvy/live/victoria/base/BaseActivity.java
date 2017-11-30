package cc.livvy.live.victoria.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.kingnet.common.util.ActivityStack;

/**
 * Created by livvy on 17-11-30.
 */

public class BaseActivity extends AppCompatActivity {
    private ActivityStack stack = ActivityStack.getInstanse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stack.addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
//        try {
//            mToolbar = (KnToolbar) findViewById(R.id.mToolbar);
//            mLayoutEmpty = findViewById(R.id.mLayoutEmpty);
//            mTextEmpty = (TextView) findViewById(R.id.mTextEmpty);
//
//            if (mToolbar != null) {
//                mToolbar.setOnBackClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//                    }
//                });
//            }
//        } catch (Throwable ex) {
//            //暂时不做任何处理
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        stack.removeActivity(this);
    }

    /**
     * show toast
     */
    public void showToast(@NonNull String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
