package com.example.emotionalintelligence.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Journal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String entry;
    private Date timestamp;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getEntry() { return entry; }
    public void setEntry(String entry) { this.entry = entry; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}