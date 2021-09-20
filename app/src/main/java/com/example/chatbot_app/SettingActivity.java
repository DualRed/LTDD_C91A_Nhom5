package com.example.chatbot_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    ImageView backBtn;
    TextView titleActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Khai báo cho phần toolbar
        View myToolbar = findViewById(R.id.my_toolbar);

        backBtn = findViewById(R.id.left_btn);
        titleActionbar = findViewById(R.id.title_actionbar);

        //Set ảnh cho icon trên toolbar
        backBtn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
        titleActionbar.setText("Cài đặt");

        //Thêm event onClick cho ImageView
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.no_animation, R.anim.slide_down);
            }
        });
    }
}