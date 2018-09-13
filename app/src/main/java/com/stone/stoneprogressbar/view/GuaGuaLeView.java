package com.stone.stoneprogressbar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.stone.stoneprogressbar.R;

import java.util.Random;

public class GuaGuaLeView extends View {

    private Random random;
    private Paint mPaint;
    private Paint mClearPaint;
    private int fingerWidth = 50; //手指涂抹的粗细
    private String[] PRIZE = {
            "恭喜，您中了一等奖，奖金 1 亿元",
            "恭喜，您中了二等奖，奖金 5000 万元",
            "恭喜，您中了三等奖，奖金 100 元",
            "很遗憾，您没有中奖，继续加油哦"
    };
    private Bitmap bmpBuffer;
    private Canvas cvsBuffer;
    private int curX;
    private int curY;

    public GuaGuaLeView(Context context) {
        this(context, null);
    }

    public GuaGuaLeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuaGuaLeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        random = new Random();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(20);
        mPaint.setColor(Color.WHITE);

        mClearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mClearPaint.setStrokeWidth(fingerWidth);
        mClearPaint.setStrokeCap(Paint.Cap.ROUND);
        mClearPaint.setStrokeJoin(Paint.Join.ROUND);
        //画背景
        drawBackground();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化缓存区
        bmpBuffer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cvsBuffer = new Canvas(bmpBuffer);
        //为缓存区蒙上一层灰色
        cvsBuffer.drawColor(Color.parseColor("#FF808080"));
    }

    /**
     * 随机生成中奖信息
     *
     * @return
     */
    private int getPrizeIndex() {
        return random.nextInt(PRIZE.length);
    }

    private void drawBackground() {
        Bitmap bmpBackground = BitmapFactory.decodeResource(getResources(), R.mipmap.background);
        //从资源中读取BMPBackground不可以修改，复制出一张可以修改的图片
        Bitmap bmpBackgroundMutable = bmpBackground.copy(Bitmap.Config.ARGB_8888, true);
        //在图片上画上中奖的信息
        Canvas cvsBackground = new Canvas(bmpBackgroundMutable);
        //计算出文字的所占区域，将文字放在正中间
        String text = PRIZE[getPrizeIndex()];
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        int x = (bmpBackgroundMutable.getWidth() - rect.width()) / 2;
        int y = (bmpBackgroundMutable.getHeight() - rect.height()) / 2;
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setShadowLayer(10, 0, 0, Color.GREEN);
        cvsBackground.drawText(text, x, y, mPaint);
        mPaint.setShadowLayer(0, 0, 0, Color.YELLOW);
        //画背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(new BitmapDrawable(getResources(), bmpBackgroundMutable));
        } else {
            this.setBackgroundDrawable(new BitmapDrawable(getResources(), bmpBackgroundMutable));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bmpBuffer, 0, 0, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                curX = x;
                curY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                cvsBuffer.drawLine(curX, curY, x, y, mClearPaint);
                invalidate();
                curX = x;
                curY = y;
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
