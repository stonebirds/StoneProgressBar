package com.stone.stoneprogressbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RulerView extends View {

    private Paint mPaint;
    //刻度的间距
    private int distance = 10;
    //1cm的刻度线的高度
    private int miniHeight = 30;
    private int height;
    private int mTextWidth;

    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int leftPadding = 2 * distance;
        int rightPadding = 2 * distance;
        int topPadding = 4 * miniHeight;
        int roundRectHeight = topPadding + 4 * miniHeight;
        int rectWidth = leftPadding + rightPadding + distance * 80;

        canvas.translate(0, roundRectHeight);

        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.top = -roundRectHeight;
        rectF.bottom = 0;
        rectF.right = rectWidth;
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(rectF, 5, 5, mPaint);

        canvas.save();
        canvas.translate(leftPadding, 0);

        //1. 绘制刻度线
        //1.1 绘制10cm刻度线
        //1.2 绘制刻度文字
        //1.3 绘制5cm刻度线
        //1.4 绘制1cm刻度线
        //2. 绘制外侧圆角矩形
        for (int i = 0; i <= 80; i++) {
            if (i % 10 == 0) {
                height = -4 * miniHeight;
                drawText(canvas, i, leftPadding);
            } else if (i % 5 == 0) {
                height = -2 * miniHeight;
            } else {
                height = -miniHeight;
            }

            int startX = i * distance;
            int startY = 0;
            int endX = i * distance;
            int endY = height;

            canvas.drawLine(startX, startY, endX, endY, mPaint);
        }
        canvas.save();
    }

    /**
     * 绘制刻度文字
     *
     * @param canvas
     * @param i
     * @param leftPadding
     */
    private void drawText(Canvas canvas, int i, int leftPadding) {
        mPaint.setTextSize(24);
        int num = i / 10;
        String textNum = String.valueOf(num);
        measureText(textNum);
        int startY = -5 * miniHeight;
        if (num == 0) {
            textNum = "0 cm";
            canvas.drawText(textNum, -mTextWidth / 2, startY, mPaint);
        } else {
            int startX = num * 10 * distance;
            canvas.drawText(textNum, startX - mTextWidth / 2, startY, mPaint);
        }
    }

    /**
     * 测量文字宽度
     *
     * @param textNum
     */
    private void measureText(String textNum) {
        Rect rect = new Rect();
        mPaint.getTextBounds(textNum, 0, textNum.length(), rect);
        mTextWidth = rect.width();
    }
}
