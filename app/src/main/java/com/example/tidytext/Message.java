package com.example.tidytext;

public class Message {
    private String title;
    private String body;
    private String category; // e.g., Recharge, Bookings, Govt, Personal, Others

    // Constructor
    public Message(String title, String body, String category) {
        this.title = title;
        this.body = body;
        this.category = category;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getCategory() {
        return category;
    }

    // (Optional) Setters if you ever need to modify data
    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
