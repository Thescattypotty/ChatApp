package Models;

public class Discussion {
    private int discussionId;
    private int user1Id;
    private int user2Id;
    private String createdAt;
    private String lastUpdated;

    public Discussion(int discussionId, int user1Id, int user2Id, String createdAt, String lastUpdated) {
        this.discussionId = discussionId;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    public int getDiscussionId() {
        return discussionId;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
