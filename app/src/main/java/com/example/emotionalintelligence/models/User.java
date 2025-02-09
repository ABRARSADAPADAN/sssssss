package com.example.emotionalintelligence.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String mood;
    private String location;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}