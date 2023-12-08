package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.User;
import Utils.User.PasswordAuthenticatedUserInterface;

public class UserRepository extends EntityRepository
{
    public UserRepository() {
        super();
    }

    public void addUser(PasswordAuthenticatedUserInterface user) throws SQLException {
        String query = "INSERT INTO USER (username, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PasswordAuthenticatedUserInterface getUser(String username) throws SQLException {
        String selectSQL = "SELECT * FROM USER WHERE username = ?";
        PasswordAuthenticatedUserInterface user = null;

        try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(resultSet.getInt("ID_user"), resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public void UpdateUser(PasswordAuthenticatedUserInterface user, String username) throws SQLException
    {
        String query = "UPDATE USER SET username = ?, password = ? WHERE username";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
    }


    public void DeleteUser(String username) throws SQLException {
        String query = "DELETE FROM USER WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getPassword(String username) throws SQLException
    {
        String query = "SELECT password FROM USER WHERE username = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                return rs.getString("password");
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createTable() throws SQLException
    {
        String createTableSQL = "CREATE TABLE USER(ID_user INT PRIMARY KEY , username VARCHAR(40) NOT NULL, password varchar(220) NOT NULL, UNIQUE(username) ); INSERT INTO USER(ID_user, username , password) VALUES(0,'yahya','yahyabgbg');";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DeleteTable() throws SQLException
    {
        String DeleteTableSQL = "DROP TABLE IF EXISTS USER ;";
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

