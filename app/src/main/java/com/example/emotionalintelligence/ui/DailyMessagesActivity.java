package com.example.emotionalintelligence.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emotionalintelligence.R;
import com.example.emotionalintelligence.models.Message;
import com.example.emotionalintelligence.network.ApiClient;
import com.example.emotionalintelligence.network.ApiService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyMessagesActivity extends AppCompatActivity {
    private ListView listViewMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_messages);
        listViewMessages = findViewById(R.id.listViewMessages);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Message>> call = apiService.getMessages(1); // Replace with actual user ID
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    List<Message> messages = response.body();
                    ArrayAdapter