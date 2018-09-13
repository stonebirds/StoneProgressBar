package com.stone.stoneprogressbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class WatchView extends View {
    private Paint mPaint;
    private Calendar mCalendar;

    public WatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);

        mCalendar = Calendar.getInstance();
    }

    /**
     * 开启
     */
    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        }, 0, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取控件宽高
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //计算圆盘直径取短
        int len = Math.min(width, height);
        //绘制表盘
        drawWatchPlate(canvas, len);
        //绘制指针
        drawWatchPoints(canvas, len);
    }

    /**
     * 绘制表盘
     *
     * @param canvas
     * @param len
     */
    private void drawWatchPlate(Canvas canvas, int len) {
        canvas.save();
        //画圆
        int r = len / 2;
        canvas.drawCircle(r, r, r, mPaint);
        //画刻度
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                //绘制长刻度
                mPaint.setColor(Color.RED);
                mPaint.setStrokeWidth(4);
                canvas.drawLine(r + r * 9 / 10, r, len, r, mPaint);
            } else {
                //绘制短刻度
                mPaint.setColor(Color.GRAY);
                mPaint.setStrokeWidth(2);
                canvas.drawLine(r + 14 * r / 15, r, len, r, mPaint);
            }
            canvas.rotate(6, r, r);
        }
        canvas.restore();
    }

    /**
     * 绘制指针
     *
     * @param canvas
     * @param len
     */
    private void drawWatchPoints(Canvas canvas, int len) {
        //获取系统时间
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        //获取时分秒
        int hours = mCalendar.get(Calendar.HOUR) % 12; //转换为12进制
        int minutes = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);

        //画时针
        //角度（顺时针）
        int degree = 360 / 12 * hours;
        //转换成弧度
        double radians = Math.toRadians(degree);
        //根据当前时计算时针的两个点的坐标
        //起点圆心终点计算得到
        int r = len / 2;
        int startX = r;
        int startY = r;
        int endX = (int) (startX + r * 0.5 * Math.cos(radians));
        int endY = (int) (startY + r * 0.5 * Math.sin(radians));
        canvas.save();
        mPaint.setStrokeWidth(3);
        //0度从3点开始，时间12点出开始，所以需要画布旋转90度
        canvas.rotate(-90, r, r);
        //画时针
        canvas.drawLine(startX, startY, endX, endY, mPaint);
        canvas.restore();

        //画分针
        //计算角度
        degree = 360 / 60 * minutes;
        radians = Math.toRadians(degree);
        endX = (int) (startX + r * 0.6 * Math.cos(radians));
        endY = (int) (startY + r * 0.6 * Math.sin(radians));
        canvas.save();
        mPaint.setStrokeWidth(2);
        //0 度从 3 点处开始，时间从 12 点处开始，所以需要将画布旋转 90 度
        canvas.rotate(-90, r, r);
        //画分针
        canvas.drawLine(startX, startY, endX, endY, mPaint);
        canvas.restore();

        //画秒针
        //计算角度
        degree = 360 / 60 * second;
        radians = Math.toRadians(degree);
        endX = (int) (startX + r * 0.8 * Math.cos(radians));
        endY = (int) (startY + r * 0.8 * Math.sin(radians));
        canvas.save();
        mPaint.setStrokeWidth(2);
        //0 度从 3 点处开始，时间从 12 点处开始，所以需要将画布旋转 90 度
        canvas.rotate(-90, r, r);
        //画秒针
        canvas.drawLine(startX, startY, endX, endY, mPaint);

        //再给秒针画个尾巴
        radians = Math.toRadians(degree - 180);
        endX = (int) (startX + r * 0.15 * Math.cos(radians));
        endY = (int) (startY + r * 0.15 * Math.sin(radians));
        canvas.drawLine(startX, startY, endX, endY, mPaint);
        canvas.restore();
    }
}
