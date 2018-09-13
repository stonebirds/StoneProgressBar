package com.stone.stoneprogressbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class ShadowTextView extends AppCompatTextView {
    public ShadowTextView(Context context) {
        super(context);
    }

    public ShadowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShadowTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TextPaint mPaint = getPaint();
        mPaint.setTextSize(getTextSize());
        mPaint.setColor(getCurrentTextColor());
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setShadowLayer(10, 1, 1, Color.RED);
        canvas.drawText(getText(), 0, getText().length(), getSelectionStart(), getSelectionEnd(), mPaint);
    }
}
