package Utils.DbModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Message;

public class MessageDb extends ObjectDb {

    public MessageDb(Connection connection) {
        super(connection);
    }

    @Override
    public Object getObject(int id) throws SQLException {
        String query = "SELECT * FROM Message WHERE message_id = ?";
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
        if (obj instanceof Message) {
            Message message = (Message) obj;
            String query = "INSERT INTO Message (discussion_id, sender_id, content, sent_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, message.getDiscussionId());
                preparedStatement.setInt(2, message.getSenderId());
                preparedStatement.setString(3, message.getContent());
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        message.setMessageId(generatedKeys.getInt(1));
                        message.setSentAt(getSentAtFromDatabase(message.getMessageId()));
                    }
                }
            }
        }
    }

    @Override
    public void updateObject(Object obj) throws SQLException {
        if (obj instanceof Message) {
            Message message = (Message) obj;
            String query = "UPDATE Message SET discussion_id = ?, sender_id = ?, content = ? WHERE message_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, message.getDiscussionId());
                preparedStatement.setInt(2, message.getSenderId());
                preparedStatement.setString(3, message.getContent());
                preparedStatement.setInt(4, message.getMessageId());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteObject(int id) throws SQLException {
        String query = "DELETE FROM Message WHERE message_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected Object mapResultSetToObject(ResultSet rs) throws SQLException {
        int messageId = rs.getInt("message_id");
        int discussionId = rs.getInt("discussion_id");
        int senderId = rs.getInt("sender_id");
        String content = rs.getString("content");
        String sent_at = rs.getString("sent_at");

        Message message = new Message(messageId, discussionId, senderId, content,sent_at);
        return message;
    }

    private String getSentAtFromDatabase(int messageId) throws SQLException {
        String query = "SELECT sent_at FROM Message WHERE message_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, messageId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("sent_at");
                }
            }
        }
        return null;
    }
}
