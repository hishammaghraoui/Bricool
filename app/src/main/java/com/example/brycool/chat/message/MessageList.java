package com.example.brycool.chat.message;

import com.example.brycool.UserHelperClass;

public class MessageList {
    private String name , mobile , lastMessage , profilepic,chatKey;
    UserHelperClass userHelperClass ;

    public MessageList(String name, String mobile, String lastMessage, String profilepic,int unseenMessages  , String chatKey) {
        this.name = name;
        this.mobile = mobile;
        this.lastMessage = lastMessage;
        this.profilepic = profilepic;
        this.chatKey = chatKey;
        this.unseenMessages = unseenMessages;
    }

    private  int unseenMessages ;

    public MessageList(String name, String mobile, String lastMessage, String profilepic, int unseenMessages , String chatKey, UserHelperClass userHelperClass) {
        this.name = name;
        this.mobile = mobile;
        this.lastMessage = lastMessage;
        this.profilepic =profilepic;
        this.unseenMessages = unseenMessages;
        this.chatKey = chatKey ;
        this.userHelperClass = userHelperClass ;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setUserHelperClass(UserHelperClass userHelperClass) {
        this.userHelperClass = userHelperClass;
    }

    public UserHelperClass getUserHelperClass() {
        return userHelperClass;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public int getUnseenMessages() {
        return unseenMessages;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getChatKey() {
        return chatKey;
    }

    public void setUnseenMessages(int unseenMessages) {
        this.unseenMessages = unseenMessages;
    }
}
