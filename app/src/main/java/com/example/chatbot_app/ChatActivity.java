package com.example.chatbot_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatbot_app.Adapter.CustomAdapter;
import com.example.chatbot_app.Helper.HttpDataHandler;
import com.example.chatbot_app.Models.ChatModel;
import com.example.chatbot_app.Models.ChatbotModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<ChatModel> list_chat = new ArrayList<>();
    FloatingActionButton btn_send_message;

    ImageView homeBtn, settingBtn;
    TextView titleActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Khai báo cho phần toolbar
        View myToolbar = findViewById(R.id.my_toolbar);

        homeBtn = myToolbar.findViewById(R.id.left_btn);
        settingBtn = myToolbar.findViewById(R.id.right_btn);
        titleActionbar = myToolbar.findViewById(R.id.title_actionbar);

        //Set ảnh cho icon trên toolbar
        homeBtn.setImageResource(R.drawable.ic_baseline_home_24);
        settingBtn.setImageResource(R.drawable.ic_baseline_settings_24);
        titleActionbar.setText("Simsimi bot");

        //Thêm event onClick cho ImageView
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up,  R.anim.no_animation);
            }
        });

        //Khai báo cho phần chat activity
        listView = (ListView)findViewById(R.id.list_of_messages);
        editText = (EditText)findViewById(R.id.text_box);
        btn_send_message = (FloatingActionButton)findViewById(R.id.send_btn);

        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ChatModel model = new ChatModel(text,true); // user send message
                list_chat.add(model);
                new SimsimiAPI().execute(list_chat);

                //remove user message
                editText.setText("");
            }
        });
    }

    public String GetBotLang() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String botLang = pref.getString("pref_language","default");
        Log.d("Language", botLang);
        return botLang;
    }

    private class SimsimiAPI extends AsyncTask<List<ChatModel>,Void,String> {
        String stream = null;
        List<ChatModel> models;
        String text = editText.getText().toString();
        String botLang = GetBotLang();

        @Override
        protected String doInBackground(List<ChatModel>... params) {
            String url = String.format("https://api.simsimi.net/v2/?text=%s&lc=%s",text, botLang);
            models = params[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url);
            Log.d("api callback",stream);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            ChatbotModel response = gson.fromJson(s,ChatbotModel.class);

            if(response == null) {
                Toast.makeText(ChatActivity.this, "Không kết nối được với server.", Toast.LENGTH_LONG).show();
            }
            else {
                ChatModel chatModel = new ChatModel(response.getSuccess(),false); // get response from simsimi
                models.add(chatModel);
                CustomAdapter adapter = new CustomAdapter(models,getApplicationContext());
                listView.setAdapter(adapter);
            }
        }
    }
}