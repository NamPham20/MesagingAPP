package com.example.messagingapp.main_screen.inbox.recycle_view;

public class UserOnline {
    String id;
    String imageUri;

    public UserOnline(String id, String imageUri) {
        this.id = id;
        this.imageUri = imageUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
