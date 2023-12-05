package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Models.User;
import javafx.util.Pair;


public class ClientHandler extends Thread
{
    private Socket clientSocket;
    private BufferedReader input;    
    private PrintWriter output;
    private Pair<User, User> discussionBetweenUser; //discussionBetweenUser.getKey() --> to get senderuser , discussionBetweenUser.getValue()  --> to get receiveruser


    public ClientHandler(Socket socket, User sender , User receiver) {
        this.clientSocket = socket;
        this.discussionBetweenUser = new Pair<>(sender,receiver);
    }

    @Override
    public void run()
    {
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{

        }
    }

    public Pair<User, User> getDiscussionBetweenUser() {
        return discussionBetweenUser;
    }

    public void setDiscussionBetweenUser(Pair<User, User> discussionBetweenUser) {
        this.discussionBetweenUser = discussionBetweenUser;
    }
}
