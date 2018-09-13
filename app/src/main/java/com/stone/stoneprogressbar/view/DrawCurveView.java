package com.stone.stoneprogressbar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawCurveView extends View {

    private Paint mPaint;
    private Bitmap mBitmapBuffer;
    private Canvas mBitmapCanvas;
    private int preX;
    private int preY;
    private int currentX;
    private int currentY;

    public DrawCurveView(Context context) {
        this(context, null);
    }

    public DrawCurveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawCurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    /**
     * 当组件大小发生变化时调用，在这里创建bitmap
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mBitmapBuffer == null) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            //新建Bitmap缓存区
            mBitmapBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mBitmapCanvas = new Canvas(mBitmapBuffer);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将Bitmap中的内容绘制到View上
        canvas.drawBitmap(mBitmapBuffer, 0, 0, null);
    }

    /**
     * 处理手势
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手指按下，记录第一个点的坐标
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动，记录当前点的坐标
                currentX = x;
                currentY = y;
                mBitmapCanvas.drawLine(preX, preY, currentX, currentY, mPaint);
                this.invalidate();
                //当前点的坐标成为下一个点的起始坐标
                preX = currentX;
                preY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

}
