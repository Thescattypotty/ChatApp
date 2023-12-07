package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Profile;

public class ProfileRepository extends EntityRepository
{
 
    public ProfileRepository(){
        super();
    }
    public void addProfile(Profile profile) throws SQLException
    {
        String query = "INSERT INTO PROFILE (ID_Profile, ID_user, firstname, lastname, age) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, profile.getId());
                preparedStatement.setInt(2, profile.getUserId());
                preparedStatement.setString(3, profile.getFirstname());
                preparedStatement.setString(4, profile.getLastname());
                preparedStatement.setInt(5, profile.getAge());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Profile GetProfile(String username) throws SQLException
    {
        Profile profile = null;
        String SqlQuery = "SELECT PROFILE.* " +
                              "FROM USER " +
                              "JOIN PROFILE ON USER.ID_user = PROFILE.ID_user " +
                              "WHERE USER.username = ?";
        try(PreparedStatement statement = connection.prepareStatement(SqlQuery)){
            statement.setString(1, SqlQuery);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                profile = new Profile(rs.getInt("ID_PROFILE"), rs.getInt("ID_user"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("age"));
                return profile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }
    public void UpdateProfile(Profile profile, String username) throws SQLException
    {
        String query = "UPDATE PROFILE " +
        "SET firstname = ?, lastname = ?, age = ? " +
        "WHERE ID_user = (SELECT ID_user FROM USER WHERE username = ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, profile.getUserId());
                preparedStatement.setString(2, profile.getFirstname());
                preparedStatement.setString(3, profile.getLastname());
                preparedStatement.setInt(4, profile.getAge());
                preparedStatement.setString(5, username);
                preparedStatement.executeUpdate();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
    }

    public void deleteObject(String username) throws SQLException {
        String query = "DELETE FROM PROFILE WHERE ID_user = (SELECT ID_user FROM USER WHERE username = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void createTable() throws SQLException {

        String createTableSQL = "CREATE TABLE PROFILE(ID_Profile INT PRIMARY KEY, ID_user INT NOT NULL, firstname VARCHAR(30) NOT NULL , lastname VARCHAR(30) NOT NULL , age int NOT NULL , FOREIGN KEY(ID_user) REFERENCES USER(ID_user)); INSERT INTO PROFILE(firstname ,lastname ,age, ID_user) VALUES('yahya','bennis',21,0)";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void DeleteTable() throws SQLException {

        String DeleteTableSQL = "DROP TABLE IF EXISTS PROFILE ;";
        try(PreparedStatement statement = connection.prepareStatement(DeleteTableSQL))
        {
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
    }
    
}
