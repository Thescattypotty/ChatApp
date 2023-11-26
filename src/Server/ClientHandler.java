package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread
{
    private Socket clientSocket;
    private BufferedReader input;    
    private PrintWriter output;
    private String username ;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run()
    {
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream());

            
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{

        }
    }

}
