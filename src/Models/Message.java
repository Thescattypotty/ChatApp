package Models;

import java.io.Serializable;

import Utils.User.PasswordAuthenticatedUserInterface;

public class Message implements Serializable
{
    private int messageId;
    private int discussionId;
    private int senderId;
    private int receiverId;
    
    private String sentAt;
    
    private String name;


    private String content;
    private Messagetype type;
    PasswordAuthenticatedUserInterface sender;
    PasswordAuthenticatedUserInterface receiver;
    public Message(PasswordAuthenticatedUserInterface sender , PasswordAuthenticatedUserInterface receiver , String content)
    {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }
    @Override
    public String toString()
    {
        return "Content : " + content + "Sender : " + sender.getUsername() + " receiver :" + receiver.getUsername();
        
    }


    //private ArrayList<PasswordAuthenticatedUserInterface> list;
    //private ArrayList<PasswordAuthenticatedUserInterface> Users;
    //private Status status;
    //private byte[] picture;
    //private byte[] voiceMsg;

    public Message(int messageId, int discussionId, int senderId, String content, String sentAt) {
        this.messageId = messageId;
        this.discussionId = discussionId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
    }


    public Message()
    {}
    public Message(int senderId , int ReceiverId, String content, Messagetype Type)
    {
        this.senderId = senderId;
        this.receiverId = ReceiverId;
        this.content = content;
        this.type = Type;
    }
    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public int getMessageId() {
        return messageId;
    }

    public int getDiscussionId() {
        return discussionId;
    }
    public void setDiscussionId(int discussionId) {
        this.discussionId = discussionId;
    }
    public int getSenderId() {
        return senderId;
    }
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSentAt() {
        return sentAt;
    }
    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public void setMessageId(int int1) {
    }

    public Messagetype getMessagetype() {
        return type;
    }

    public void setMessagetype(Messagetype messagetype) {
        this.type = messagetype;
    }

    public Messagetype getType() {
        return type;
    }

    public void setType(Messagetype type) {
        this.type = type;
    }/*

    public ArrayList<PasswordAuthenticatedUserInterface> getUserList() {
        return list;
    }

    public void setUserList(HashMap<String ,PasswordAuthenticatedUserInterface> list) {
        this.list = new ArrayList<>(list.values());
    }

    public ArrayList<PasswordAuthenticatedUserInterface> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<PasswordAuthenticatedUserInterface> users) {
        Users = users;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }*/
/*
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public byte[] getVoiceMsg() {
        return voiceMsg;
    }

    public void setVoiceMsg(byte[] voiceMsg) {
        this.voiceMsg = voiceMsg;
    }*/

    public int getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
    public PasswordAuthenticatedUserInterface getSender() {
        return sender;
    }
    public void setSender(PasswordAuthenticatedUserInterface sender) {
        this.sender = sender;
    }
    public PasswordAuthenticatedUserInterface getReceiver() {
        return receiver;
    }
    public void setReceiver(PasswordAuthenticatedUserInterface receiver) {
        this.receiver = receiver;
    }

    
    

    
}
