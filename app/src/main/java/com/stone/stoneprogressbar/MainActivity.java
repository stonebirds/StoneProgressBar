package com.stone.stoneprogressbar;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stone.stoneprogressbar.view.HorizontalProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btnCamera = findViewById(R.id.btn_camera);

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
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            }
        });
    }
}
