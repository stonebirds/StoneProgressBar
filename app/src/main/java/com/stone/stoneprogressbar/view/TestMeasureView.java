package com.stone.stoneprogressbar.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TestMeasureView extends View {
    public TestMeasureView(Context context) {
        super(context);
    }

    public TestMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    /**
     * 测量宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = 0;
        if (widthMode == MeasureSpec.EXACTLY){
            //宽度为match_parent和具体指时，直接将size作为组件的高度
            width = widthSize;
        }else if (widthMode == MeasureSpec.AT_MOST){
            //wrap_content 宽度需要計算
        }
        return width;
    }

    /**
     * 测量高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (heightMode == MeasureSpec.EXACTLY){
            //宽度为match_parent和具体指时，直接将size作为组件的高度
            height = heightSize;
        }else if (heightMode == MeasureSpec.AT_MOST){
            //wrap_content 宽度需要計算
        }
        return height;
    }
}
