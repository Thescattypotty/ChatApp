package Utils.DbModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Profile;

public class ProfileDb extends ObjectDb {

    public ProfileDb(Connection connection) {
        super(connection);
    }

    @Override
    public Object getObject(int id) throws SQLException {
        String query = "SELECT * FROM PROFILE WHERE ID_Profile = ?";
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
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            String query = "INSERT INTO PROFILE (ID_Profile, ID_user, firstname, lastname, age) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, profile.getId());
                preparedStatement.setInt(2, profile.getUserId());
                preparedStatement.setString(3, profile.getFirstname());
                preparedStatement.setString(4, profile.getLastname());
                preparedStatement.setInt(5, profile.getAge());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void updateObject(Object obj) throws SQLException {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            String query = "UPDATE PROFILE SET ID_user = ?, firstname = ?, lastname = ?, age = ? WHERE ID_Profile = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, profile.getUserId());
                preparedStatement.setString(2, profile.getFirstname());
                preparedStatement.setString(3, profile.getLastname());
                preparedStatement.setInt(4, profile.getAge());
                preparedStatement.setInt(5, profile.getId());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteObject(int id) throws SQLException {
        String query = "DELETE FROM PROFILE WHERE ID_Profile = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    protected Object mapResultSetToObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID_Profile");
        int userId = rs.getInt("ID_user");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        int age = rs.getInt("age");
        return new Profile(id, userId, firstname, lastname, age);
    }
}
