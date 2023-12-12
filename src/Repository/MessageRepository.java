package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Message;

public class MessageRepository extends EntityRepository
{
    public MessageRepository()
    {
        super();
    }

    public void SendMessage(Message message)
    {
        String query = "INSERT INTO MESSAGE(ID_discussion , ID_sender , content , sentAt) VALUES (? , ? , ? , CURRENT_TIMESTEAMP)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, message.getDiscussionId());
            preparedStatement.setInt(2, message.getSenderId());
            preparedStatement.setString(3, message.getContent());
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public List<Message> getMessageByDiscussion(int ID_discussion)
    {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM MESSAGE WHERE ID_discussion = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, ID_discussion);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                Message m = new Message(rs.getInt("ID_Message"), rs.getInt("ID_discussion"), rs.getInt("ID_sender"), rs.getString("content") ,rs.getString("sentAt"));
                messages.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }



       @Override
    public void createTable() throws SQLException {
        String query = "CREATE TABLE MESSAGE(ID_Message INT PRIMARY KEY , ID_discussion INT NOT NULL, ID_sender INT NOT NULL, content VARCHAR(255) NOT NULL , sentAt VARCHAR(50) NOT NULL , FOREIGN KEY(ID_sender) REFERENCES USER(ID_user), FOREIGN KEY(ID_discussion) REFERENCES DISCUSSION(ID_Discussion) )";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteTable() throws SQLException {
        String DeleteTableSQL = "DROP TABLE IF EXISTS MESSAGE ;";
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
