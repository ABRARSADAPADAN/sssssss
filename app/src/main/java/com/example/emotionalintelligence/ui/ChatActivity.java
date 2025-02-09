package com.example.emotionalintelligence.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emotionalintelligence.R;

public class ChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    public void startVoiceCall(View view) {
        Intent intent = new Intent(this, VoiceCallActivity.class);
        startActivity(intent);
    }
}