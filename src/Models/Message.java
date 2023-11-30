package Models;

public class Message 
{
    private int messageId;
    private int discussionId;
    private int senderId;
    private String content;
    private String sentAt;

    
    public Message(int messageId, int discussionId, int senderId, String content, String sentAt) {
        this.messageId = messageId;
        this.discussionId = discussionId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
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

    
}
