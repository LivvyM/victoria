/*
 *    Copyright 2015 Kaopiz Software Co., Ltd.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package cc.livvy.widget.loadingdialog;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import cc.livvy.widget.R;


class SpinView extends ImageView implements Indeterminate {


    public SpinView(Context context) {
        super(context);
        init();
    }

    public SpinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setImageResource(R.drawable.ic_svg_load);
        setLayoutParams(new ViewGroup.LayoutParams(UIHelper.dip2px(getContext(),18), UIHelper.dip2px(getContext(),18)));
    }

    @Override
    public void setAnimationSpeed(float scale) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void loadAnimation(){
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                getContext(), R.anim.loading_animation);
        // 使用ImageView显示动画
        startAnimation(hyperspaceJumpAnimation);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        loadAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }
}
