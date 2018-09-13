package com.stone.stoneprogressbar.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class BallMoveView extends View {
    /**
     * 小球的水平个位置
     */
    private float x;
    /**
     * 小球的垂直个位置
     */
    private int y = 100;
    /**
     * 小球的半径
     */
    private int mBallRadius = 40;
    /**
     * 小球的颜色
     */
    private int mBallColor = Color.RED;
    private Paint mPaint;
    /**
     * 小球的移动方向
     */
    private boolean direction;
    private int width;
    private TimerTask timerTask;
    private Timer timer;

    public BallMoveView(Context context) {
        this(context, null);
    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mBallColor);
        mPaint.setAntiAlias(true);
        x = mBallRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据x，y坐标画小球
        canvas.drawCircle(x, y, mBallRadius, mPaint);
        //获取控件的宽度
        width = getMeasuredWidth();
        if (x <= mBallRadius) {
            direction = true;
        }
        if (x >= width - mBallRadius) {
            direction = false;
        }
        x = direction ? x + 2 : x - 2;
    }

    public void startMove() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 5, 5);
    }

    public void stop() {
        if (timerTask != null && timer != null) {
            timerTask.cancel();
            timer.cancel();
            timerTask = null;
            timer = null;
        }
    }
}
