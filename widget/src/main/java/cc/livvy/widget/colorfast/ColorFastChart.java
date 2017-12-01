package cc.livvy.widget.colorfast;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by livvy on 17-10-25.
 */

public class ColorFastChart extends View {

    private Context mContext;

    private Paint mPaint;

    private int widthMeasure;
    private int heightMeasure;

    private List<ColorFastBean> colorFasts = new ArrayList<>();


    public ColorFastChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthMeasure = getMeasuredWidth();
        heightMeasure = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = 0;
        for (ColorFastBean item : colorFasts) {
            mPaint.setColor(item.color);
            int width = (int) (widthMeasure * item.proportion);
            canvas.drawRect(left, 0, left + width, heightMeasure, mPaint);
            left += width;
        }
    }

    public void setData(ArrayList<ColorFastBean> colorFasts){
        this.colorFasts = colorFasts;
        invalidate();
    }
}
