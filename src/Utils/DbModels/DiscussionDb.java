package Utils.DbModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscussionDb extends ObjectDb {

    public DiscussionDb(Connection connection) {
        super(connection);
    }

    @Override
    public Object getObject(int id) throws SQLException {
        String query = "SELECT * FROM Discussion WHERE discussion_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToObject(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void insertObject(Object obj) throws SQLException {
        if (obj instanceof Discussion) {
            Discussion discussion = (Discussion) obj;
            String query = "INSERT INTO Discussion (user1_id, user2_id, created_at, last_updated) VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, discussion.getUser1Id());
                preparedStatement.setInt(2, discussion.getUser2Id());
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        discussion.setDiscussionId(generatedKeys.getInt(1));
                        discussion.setCreatedAt(getCreatedAtFromDatabase(discussion.getDiscussionId()));
                        discussion.setLastUpdated(getLastUpdatedFromDatabase(discussion.getDiscussionId()));
                    }
                }
            }
        }
    }

    @Override
    public void updateObject(Object obj) throws SQLException {
        if (obj instanceof Discussion) {
            Discussion discussion = (Discussion) obj;
            String query = "UPDATE Discussion SET user1_id = ?, user2_id = ?, last_updated = CURRENT_TIMESTAMP WHERE discussion_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, discussion.getUser1Id());
                preparedStatement.setInt(2, discussion.getUser2Id());
                preparedStatement.setInt(3, discussion.getDiscussionId());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteObject(int id) throws SQLException {
        String query = "DELETE FROM Discussion WHERE discussion_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected Object mapResultSetToObject(ResultSet rs) throws SQLException {
        int discussionId = rs.getInt("discussion_id");
        int user1Id = rs.getInt("user1_id");
        int user2Id = rs.getInt("user2_id");
        String createdAt = rs.getString("created_at");
        String lastUpdated = rs.getString("last_updated");

        Discussion discussion = new Discussion(discussionId, user1Id, user2Id, createdAt, lastUpdated);    

        return discussion;
    }
    

    private String getCreatedAtFromDatabase(int discussionId) throws SQLException {
        String query = "SELECT created_at FROM Discussion WHERE discussion_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, discussionId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("created_at");
                }
            }
        }
        return null;
    }
    
    private String getLastUpdatedFromDatabase(int discussionId) throws SQLException {
        String query = "SELECT last_updated FROM Discussion WHERE discussion_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, discussionId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("last_updated");
                }
            }
        }
        return null;
    }
}
