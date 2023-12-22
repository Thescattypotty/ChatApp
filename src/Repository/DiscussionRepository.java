package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Discussion;

public class DiscussionRepository extends EntityRepository {
    private UserRepository userRepository;

    public DiscussionRepository() {
        super();
        userRepository = new UserRepository();
    }

    public void addDiscussion(Discussion discussion) {
        String query = "INSERT INTO DISCUSSION(ID_user1 , ID_user2 , CreatedAt , UpdatedAt) VALUES(? , ?, CURRENT_TIMESTEAMP, CURRENT_TIMESTEAMP)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, discussion.getUser1Id());
            preparedStatement.setInt(2, discussion.getUser2Id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Discussion> GetDiscussions(String username) {
        List<Discussion> discussionofuser = new ArrayList<>();
        String query = "SELECT * FROM DISCUSSION WHERE ID_user1 = (SELECT ID_user FROM USER WHERE username = ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Discussion d = new Discussion(rs.getInt("ID"), rs.getInt("ID_user1"), rs.getInt("ID_user2"),
                        rs.getString("CreatedAt"), rs.getString("UpdatedAt"));
                discussionofuser.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discussionofuser;
    }

    public Discussion GetDiscussion(String u1, String u2) {
        Discussion d = null;

        String query = "SELECT * FROM DISCUSSION WHERE ID_user1 = (SELECT ID_user FROM USER WHERE username = ?) AND ID_user2 = (SELECT ID_user FROM USER WHERE username = ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, u1);
            preparedStatement.setString(2, u2);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                d = new Discussion(rs.getInt("ID"), rs.getInt("ID_user1"), rs.getInt("ID_user2"),
                        rs.getString("CreatedAt"), rs.getString("UpdatedAt"));
            } else {
                
                String query1 = "SELECT * FROM DISCUSSION WHERE ID_user1 = (SELECT ID_user FROM USER WHERE username = ?) AND ID_user2 = (SELECT ID_user FROM USER WHERE username = ?)";

                try (PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
                    preparedStatement1.setString(1, u2);
                    preparedStatement1.setString(2, u1);
                    ResultSet rs1 = preparedStatement1.executeQuery();
                    if (rs.next()) {
                        d = new Discussion(rs1.getInt("ID"), rs1.getInt("ID_user1"), rs1.getInt("ID_user2"),
                                rs1.getString("CreatedAt"), rs1.getString("UpdatedAt"));
                    }
                    else
                    {
                        return null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public void createTable() throws SQLException {
        String query = "CREATE TABLE DISCUSSION(ID_Discussion INT PRIMARY KEY, ID_user1 INT NOT NULL , ID_user2 INT NOT NULL , CreatedAt VARCHAR(50) NOT NULL, UpdatedAt VARCHAR(50) NOT NULL, FOREIGN KEY(ID_user1) REFERENCES USER(ID_user), FOREIGN KEY(ID_user2) REFERENCES USER(ID_user) );";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteTable() throws SQLException {
        String DeleteTableSQL = "DROP TABLE IF EXISTS DISCUSSION ;";
        try (PreparedStatement statement = connection.prepareStatement(DeleteTableSQL)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

}
