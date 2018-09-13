package com.stone.stoneprogressbar.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.stone.stoneprogressbar.R;
import com.stone.stoneprogressbar.view.BallMoveView;
import com.stone.stoneprogressbar.view.WatchView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ViewActivity extends AppCompatActivity {

    private ImageView iv_1;
    private ImageView iv_2;
    private ImageView iv_3;
    private ImageView iv_4;
    private ImageView iv_5;
    private ImageView iv_6;
    private ImageView iv_7;
    private ImageView iv_8;
    private BallMoveView bmv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        initView();

        initBallView();
        initWatchView();
    }

    private void initWatchView() {
        WatchView watchView = findViewById(R.id.wv_watch_view);
        watchView.run();
    }

    private void initBallView() {
        bmv = findViewById(R.id.bmv);
        bmv.startMove();
    }

    private void initView() {
        iv_1 = findViewById(R.id.iv_1);
        iv_2 = findViewById(R.id.iv_2);
        iv_3 = findViewById(R.id.iv_3);
        iv_4 = findViewById(R.id.iv_4);
        iv_5 = findViewById(R.id.iv_5);
        iv_6 = findViewById(R.id.iv_6);
        iv_7 = findViewById(R.id.iv_7);
        iv_8 = findViewById(R.id.iv_8);

        setImageView1();
        setImageView2();
        setImageView3();
        setImageView4();
        setImageView5();
        setImageView6();
        setImageView7();
        setImageView8();
    }

    private void setImageView1() {
        Bitmap bmpBuffer = Bitmap.createBitmap(800, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(100, 100);
        path.quadTo(200, 10, 300, 100);
        canvas.drawPath(path, paint);
        //画起始点
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLUE);
        canvas.drawPoints(new float[]{100, 100, 200, 10, 300, 100}, paint);
        iv_1.setImageBitmap(bmpBuffer);
    }

    private void setImageView2() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 400,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        String text = "我是一只小小小小鸟，小鸟飞呀飞，飞呀飞不高！";
        canvas.drawText(text, 10, 50, paint);
        canvas.drawText(text, 0, 4, 10, 100, paint);
        canvas.drawText(text.toCharArray(), 5, 10, 10, 150, paint);
        Path path = new Path();
        path.moveTo(10, 200);
        path.quadTo(100, 100, 300, 300);
        canvas.drawTextOnPath(text, path, 15, 15, paint);
        canvas.drawTextOnPath(text, path, -15, -15, paint);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        iv_2.setImageBitmap(bmpBuffer);
    }

    private void setImageView3() {
        Bitmap bmpBuffer = Bitmap.createBitmap(1080, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int colorA = random.nextInt(255);
            int colorR = random.nextInt(255);
            int colorG = random.nextInt(255);
            int colorB = random.nextInt(255);
            paint.setColor(Color.argb(colorA, colorR, colorG, colorB));

            int strokeWidth = random.nextInt(6) + 1;
            paint.setStrokeWidth(strokeWidth);

            float startX = random.nextFloat() * 400;
            float startY = random.nextFloat() * 400;
            float endX = random.nextFloat() * 1080;
            float endY = random.nextFloat() * 400;

            canvas.drawLine(startX, startY, endX, endY, paint);
        }

        for (int i = 0; i < 4; i++) {
            int num = random.nextInt(10);
            String text = String.valueOf(num);
            int textSize = random.nextInt(30) + 30;
            paint.setTextSize(textSize);
            paint.setStrokeWidth(random.nextFloat() * 10 + 10);
            int colorA = random.nextInt(255);
            int colorR = random.nextInt(255);
            int colorG = random.nextInt(255);
            int colorB = random.nextInt(255);
            paint.setColor(Color.argb(colorA, colorR, colorG, colorB));
            float sX = random.nextInt(200 + i * 200);
            float sY = random.nextInt(400);
            canvas.drawText(text, 0, text.length(), sX, sY, paint);
        }

        iv_3.setImageBitmap(bmpBuffer);
    }

    private void setImageView4() {

    }

    private void setImageView5() {

    }

    private void setImageView6() {

    }

    private void setImageView7() {

    }

    private void setImageView8() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bmv.stop();
    }
}
