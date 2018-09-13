package com.stone.stoneprogressbar.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ShadowLayerView extends View {
    public ShadowLayerView(Context context) {
        this(context, null);
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Paint mPaint = new Paint();
        /*
         * radius：阴影半径
         * dx：x 方向阴影的偏移量
         * dy：y 方向阴影的偏移量
         * shadowColor：阴影的颜色
         * */
//        mPaint.setShadowLayer();
    }
}
