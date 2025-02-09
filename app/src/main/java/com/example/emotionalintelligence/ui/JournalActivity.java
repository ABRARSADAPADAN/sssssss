package com.example.emotionalintelligence.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emotionalintelligence.R;
import com.example.emotionalintelligence.models.Journal;
import com.example.emotionalintelligence.network.ApiClient;
import com.example.emotionalintelligence.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.openai.api.OpenAiApi;
import com.openai.api.CompletionRequest;
import com.openai.api.CompletionResponse;

public class JournalActivity extends AppCompatActivity {
    private EditText editTextJournal;
    private OpenAiApi openAiApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        editTextJournal = findViewById(R.id.editTextJournal);
        openAiApi = new OpenAiApi("YOUR_OPENAI_API_KEY");
    }

    public void saveJournalEntry(View view) {
        String entry = editTextJournal.getText().toString().trim();
        if (entry.isEmpty()) {
            Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show();
            return;
        }

        generateJournalEntryWithAI(entry);
    }

    private void generateJournalEntryWithAI(String prompt) {
        CompletionRequest request = new CompletionRequest.Builder()
                .model("text-davinci-002")
                .prompt(prompt)
                .maxTokens(150)
                .build();

        openAiApi.createCompletion(request).enqueue(new Callback<CompletionResponse>() {
            @Override
            public void onResponse(Call<CompletionResponse> call, Response<CompletionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String aiEntry = response.body().getChoices().get(0).getText().trim();
                    saveJournalEntryToServer(aiEntry);
                } else {
                    Toast.makeText(JournalActivity.this, "Failed to generate entry", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CompletionResponse> call, Throwable t) {
                Toast.makeText(JournalActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveJournalEntryToServer(String entry) {
        Journal journal = new Journal();
        journal.setUserId(1); // Replace with actual user ID
        journal.setEntry(entry);
        journal.setTimestamp(new java.util.Date());

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.addJournalEntry(journal);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(JournalActivity.this, "Journal entry added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(JournalActivity.this, "Failed to add entry", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(JournalActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}