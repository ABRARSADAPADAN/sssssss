package com.example.emotionalintelligence.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emotionalintelligence.R;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void openJournal(View view) {
        Intent intent = new Intent(this, JournalActivity.class);
        startActivity(intent);
    }

    public void openChat(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void openVoiceMessages(View view) {
        Intent intent = new Intent(this, VoiceMessageActivity.class);
        startActivity(intent);
    }

    public void openRomanticSuggestions(View view) {
        Intent intent = new Intent(this, RomanticSuggestionsActivity.class);
        startActivity(intent);
    }

    public void openDailyMessages(View view) {
        Intent intent = new Intent(this, DailyMessagesActivity.class);
        startActivity(intent);
    }

    public void openLocationTracking(View view) {
        Intent intent = new Intent(this, LocationTrackingActivity.class);
        startActivity(intent);
    }

    public void openIntimacyFeatures(View view) {
        Intent intent = new Intent(this, IntimacyFeaturesActivity.class);
        startActivity(intent);
    }

    public void openPrivacySettings(View view) {
        Intent intent = new Intent(this, PrivacySettingsActivity.class);
        startActivity(intent);
    }
}