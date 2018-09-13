package com.stone.stoneprogressbar.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.stone.stoneprogressbar.R;
import com.stone.stoneprogressbar.view.GuaGuaLeView;
import com.stone.stoneprogressbar.view.HorizontalProgressBar;

public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv_image);

        Button btnCamera = findViewById(R.id.btn_camera);
        Button btn_draw = findViewById(R.id.btn_draw);
        Button btn_nfc = findViewById(R.id.btn_nfc);
        Button btn_gua = findViewById(R.id.btn_gua);

        HorizontalProgressBar mProgressBar = findViewById(R.id.hpb);
        mProgressBar.setCurrentProgress(0);

        HorizontalProgressBar mProgressBar1 = findViewById(R.id.hpb1);
        mProgressBar1.setCurrentProgress(20);

        HorizontalProgressBar mProgressBar2 = findViewById(R.id.hpb2);
        mProgressBar2.setCurrentProgress(50);

        HorizontalProgressBar mProgressBar3 = findViewById(R.id.hpb3);
        mProgressBar3.setCurrentProgress(80);

        HorizontalProgressBar mProgressBar4 = findViewById(R.id.hpb4);
        mProgressBar4.setCurrentProgress(100);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
            }
        });

        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DrawPlateActivity.class));
            }
        });
        btn_nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NFCActivity.class));
            }
        });
        btn_gua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GuaGuaLeActivity.class));
            }
        });

        initBitmap();
    }

    private void initBitmap() {
        Bitmap bmpBuffer = Bitmap.createBitmap(150, 150, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmpBuffer);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap, 0, 0, null);
        //对图片进行缩放
//        int bmpWidth = bitmap.getWidth();
//        int bmpHeight =bitmap.getHeight();
//        Rect src = new Rect(0, 0, bmpWidth, bmpHeight);
//        Rect dst = new Rect(0, bmpHeight, bmpWidth * 3, bmpHeight * 3 + bmpHeight);
//        canvas.drawBitmap(bitmap, src, dst, null);
        iv.setImageBitmap(bmpBuffer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            System.gc();
            bitmap = null;
        }
    }
}
