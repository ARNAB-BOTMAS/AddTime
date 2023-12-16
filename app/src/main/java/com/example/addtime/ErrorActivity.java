package com.example.addtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class ErrorActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        lottieAnimationView = findViewById(R.id.goBackImg);
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ErrorActivity.this, OpenActivity.class));
                finish();
            }
        });

        textView = findViewById(R.id.goBack);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ErrorActivity.this, OpenActivity.class));
                finish();
            }
        });
    }
}