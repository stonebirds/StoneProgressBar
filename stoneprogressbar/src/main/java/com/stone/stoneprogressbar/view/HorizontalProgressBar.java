package com.stone.stoneprogressbar.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.stone.stoneprogressbar.R;
import com.stone.stoneprogressbar.utils.Utils;

import java.text.DecimalFormat;

public class HorizontalProgressBar extends View {
    public static final String TAG = HorizontalProgressBar.class.getSimpleName();

    private Paint mBackgroundPaint;
    private Paint mProgressPaint;
    private Paint mPathPaint;
    private Path mPath;
    private Paint mTextPaint;

    private int mProgressHeight;
    private float mTextSize;
    private int mTextColor;
    private int mCenterPadding;
    private int mTextLeftPadding;
    private int mTextRightPadding;
    private int mTextTopPadding;
    private int mTextBottomPadding;
    private int mBackgroundColor;
    private int mForegroundColor;

    private float mIndicatorWidth;
    private float mIndicatorHeight;
    private int mWidth;
    private int mHeight;
    private float mTextWidth = 0;
    private float mTextHeight = 0;

    private float currentProgress;
    private String mTextProgress;

    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);
        mTextSize = ta.getDimension(R.styleable.HorizontalProgressBar_hpb_textSize, Utils.sp2px(context, 12));
        mTextColor = ta.getColor(R.styleable.HorizontalProgressBar_hpb_textColor, Color.parseColor("#FFFFFF"));
        currentProgress = ta.getFloat(R.styleable.HorizontalProgressBar_hpb_progress, 0);
        mTextLeftPadding = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_textLeftPadding, Utils.sp2px(context, 5));
        mTextRightPadding = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_textRightPadding, Utils.sp2px(context, 5));
        mTextTopPadding = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_textTopPadding, Utils.sp2px(context, 5));
        mTextBottomPadding = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_textBottomPadding, Utils.sp2px(context, 5));
        mCenterPadding = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_centerPadding, Utils.sp2px(context, 5));
        mProgressHeight = (int) ta.getDimension(R.styleable.HorizontalProgressBar_hpb_progressBarHeight, Utils.sp2px(context, 2));
        mBackgroundColor = ta.getColor(R.styleable.HorizontalProgressBar_hpb_progressBarBackgroundColor, Color.parseColor("#E8E8E8"));
        mForegroundColor = ta.getColor(R.styleable.HorizontalProgressBar_hpb_progressBarForegroundColor, Color.parseColor("#912CEE"));
        ta.recycle();

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        mBackgroundPaint.setStrokeWidth(mProgressHeight);
        mBackgroundPaint.setAntiAlias(true);

        mPathPaint = new Paint();
        mPathPaint.setColor(mForegroundColor);
        mPathPaint.setStrokeCap(Paint.Cap.ROUND);
        mPathPaint.setPathEffect(new CornerPathEffect(Utils.dp2px(getContext(), 2)));
        mPathPaint.setAntiAlias(true);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(mForegroundColor);
        mProgressPaint.setStrokeWidth(mProgressHeight);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measureWidth(widthMode, width), measureHeight(heightMode, height));
    }

    /**
     * 测量宽度
     *
     * @param mode
     * @param width
     * @return
     */
    private int measureWidth(int mode, int width) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                mWidth = width;
                break;
            case MeasureSpec.EXACTLY:
                mWidth = width;
                break;
        }
        return mWidth;
    }

    /**
     * 测量高度
     *
     * @param mode
     * @param height
     * @return
     */
    private int measureHeight(int mode, int height) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                mHeight = height;
                break;
            case MeasureSpec.EXACTLY:
                mHeight = height;
                break;
        }
        return mHeight;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mIndicatorWidth = mTextLeftPadding + mTextWidth + mTextRightPadding;
        mIndicatorHeight = mTextTopPadding + mTextHeight + mTextBottomPadding;

        float backgroundProgressBarStartX = (float) (1.0 * mIndicatorWidth / 2);
        float backgroundProgressBarEndX = mWidth - mIndicatorWidth / 2;

        float foregroundProgress = (float) (1.0 * (mWidth - mIndicatorWidth) * currentProgress / 100);

        float foregroundProgressBarStartX = (float) (1.0 * mIndicatorWidth / 2);
        float foregroundProgressBarEndX = foregroundProgressBarStartX + foregroundProgress;

        Log.e(TAG, "backgroundProgressBarStartX----" + backgroundProgressBarStartX
                + "----backgroundProgressBarEndX----" + backgroundProgressBarEndX
                + "----foregroundProgress----" + foregroundProgress
                + "----foregroundProgressBarStartX----" + foregroundProgressBarStartX
                + "----foregroundProgressBarEndX----" + foregroundProgressBarEndX
        );

        float progressX = foregroundProgress;
        float progressY = (float) (mCenterPadding + mIndicatorHeight * 1.5);


        //进度背景
        canvas.drawLine(backgroundProgressBarStartX, progressY, backgroundProgressBarEndX, progressY, mBackgroundPaint);

        //进度
        canvas.drawLine(foregroundProgressBarStartX, progressY, foregroundProgressBarEndX, progressY, mProgressPaint);

        //指示器
        drawPath(canvas, progressX, 45);

        //文字
        drawText(canvas, mTextProgress, progressX);
    }

    /**
     * 绘制文字
     *
     * @param canvas
     * @param text
     * @param progressX
     */
    private void drawText(Canvas canvas, String text, float progressX) {
        float baseline = getBaseline(mTextPaint);
        float textY = mTextTopPadding + mTextHeight / 2 + baseline;
        canvas.drawText(text, progressX + mTextTopPadding, textY, mTextPaint);
    }

    private void measureText(String text) {
        Rect rect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        mTextWidth = rect.width();
        mTextHeight = rect.height();
    }

    /**
     * 绘制指示器
     *
     * @param canvas
     * @param progressX
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawPath(Canvas canvas, float progressX, float rotateAngle) {
        mPath.reset();

        mPath.moveTo(progressX, 0); //1
        mPath.lineTo(progressX + mIndicatorWidth, 0); //2
        mPath.lineTo(progressX + mIndicatorWidth, mIndicatorHeight); //3
        mPath.lineTo((float) (progressX + mIndicatorWidth * 0.75), mIndicatorHeight); //4
        mPath.lineTo((float) (progressX + mIndicatorWidth * 0.5), (float) (mIndicatorHeight * 1.5)); //5
        mPath.lineTo((float) (progressX + mIndicatorWidth * 0.25), mIndicatorHeight); //6
        mPath.lineTo(progressX, mIndicatorHeight);//7
        mPath.close();

        canvas.drawPath(mPath, mPathPaint);
    }

    public void setCurrentProgress(float progress) {
        initAnimation(progress);
    }

    /**
     * 初始化动画
     *
     * @param progress
     */
    private void initAnimation(float progress) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, progress);
        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                currentProgress = value;
                mTextProgress = new DecimalFormat("#0.00").format(new Float(value));
                measureText(mTextProgress);
                invalidate();
            }
        });

        animator.start();

    }

    /**
     * 设置指示器与进度条之间的间距
     *
     * @param mCenterPadding
     */
    public void setmCenterPadding(int mCenterPadding) {
        this.mCenterPadding = mCenterPadding;
    }

    /**
     * 计算绘制文字时的基线到中轴线的距离
     *
     * @param p
     * @return 基线和centerY的距离
     */
    public float getBaseline(Paint p) {
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
        return (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;
    }
}
