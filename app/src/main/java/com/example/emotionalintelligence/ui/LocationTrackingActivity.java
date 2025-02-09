package com.example.emotionalintelligence.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emotionalintelligence.R;
import com.example.emotionalintelligence.models.User;
import com.example.emotionalintelligence.network.ApiClient;
import com.example.emotionalintelligence.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationTrackingActivity extends AppCompatActivity {
    private EditText editTextLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tracking);
        editTextLocation = findViewById(R.id.editTextLocation);
    }

    public void updateLocation(View view) {
        String location = editTextLocation.getText().toString().trim();
        if (!location.isEmpty()) {
            User user = new User();
            user.setId(1); // Replace with actual user ID
            user.setLocation(location);

            ApiService apiService = ApiClient.getClient().create(ApiService.class);
            Call<Void> call = apiService.createUser(user);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(LocationTrackingActivity.this, "Location updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LocationTrackingActivity.this, "Failed to update location", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(LocationTrackingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
        }
    }
}