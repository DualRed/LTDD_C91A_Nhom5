package com.example.chatbot_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    ImageView backBtn;
    TextView titleActionbar;

    ListPreference languageList;

    private SharedPreferences.OnSharedPreferenceChangeListener listener;

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

        if(findViewById(R.id.setting_container) != null) {
            if(savedInstanceState != null) {
                return;
            }
            getFragmentManager().beginTransaction().add(R.id.setting_container, new SettingsFragment()).commit();
        }
    }
}