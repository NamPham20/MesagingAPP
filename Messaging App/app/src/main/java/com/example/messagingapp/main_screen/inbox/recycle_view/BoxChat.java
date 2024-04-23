package com.example.messagingapp.main_screen.inbox.recycle_view;

public class BoxChat {

    private String imageUrl;
    private String status;
    private String nickName;
    private String lastMessage;
    private String lastTime;
    private int numberUnSeenMessage;

    public BoxChat(String imageUrl, String status, String nickName, String lastMessage, String lastTime, int numberUnSeenMessage) {
        this.imageUrl = imageUrl;
        this.status = status;
        this.nickName = nickName;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
        this.numberUnSeenMessage = numberUnSeenMessage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getNumberUnSeenMessage() {
        return numberUnSeenMessage;
    }

    public void setNumberUnSeenMessage(int numberUnSeenMessage) {
        this.numberUnSeenMessage = numberUnSeenMessage;
    }
}
