package com.stone.stoneprogressbar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.stone.stoneprogressbar.R;

public class CircleImageView extends View {

    private static final float OFFSET = 10;
    private Bitmap bitmapLayer;
    private Bitmap bitmapDog;
    private Paint paint;

    public CircleImageView(Context context) {
        this(context,null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        bitmapLayer = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        bitmapDog = BitmapFactory.decodeResource(getResources(), R.mipmap.dog);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int id = canvas.saveLayer(OFFSET, OFFSET, bitmapLayer.getWidth() + OFFSET, bitmapLayer.getHeight() + OFFSET, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmapDog, 0, 0, null);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapLayer, OFFSET, OFFSET, paint);
        canvas.restoreToCount(id);
    }
}
