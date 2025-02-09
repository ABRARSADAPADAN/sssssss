package com.example.emotionalintelligence.network;

import com.example.emotionalintelligence.models.Journal;
import com.example.emotionalintelligence.models.Message;
import com.example.emotionalintelligence.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("user")
    Call<Void> createUser(@Body User user);

    @POST("journal")
    Call<Void> addJournalEntry(@Body Journal journal);

    @POST("messages")
    Call<Void> addMessage(@Body Message message);

    @GET("messages/{userId}")
    Call<List<Message>> getMessages(@Path("userId") int userId);
}