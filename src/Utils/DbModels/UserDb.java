package Utils.DbModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDb extends ObjectDb {

    public UserDb(Connection connection) {
        super(connection);
    }

    @Override
    public Object getObject(int id) throws SQLException {
        String query = "SELECT * FROM USER WHERE ID_user = ?";
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
        if (obj instanceof User) {
            User user = (User) obj;
            String query = "INSERT INTO USER (ID_user, username, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void updateObject(Object obj) throws SQLException {
        if (obj instanceof User) {
            User user = (User) obj;
            String query = "UPDATE USER SET username = ?, password = ? WHERE ID_user = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setInt(3, user.getId());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteObject(int id) throws SQLException {
        String query = "DELETE FROM USER WHERE ID_user = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected Object mapResultSetToObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID_user");
        String username = rs.getString("username");
        String password = rs.getString("password");
        return new User(id, username, password);
    }
}
