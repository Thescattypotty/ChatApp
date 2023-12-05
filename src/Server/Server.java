package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 9999;
    private Map<String, PrintWriter> connectedClient = new HashMap<>();

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server Started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                //new ClientHandler(clientSocket).start();// here i need to get users
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConnectedClient(Map<String, PrintWriter> connectedClient) {
        this.connectedClient = connectedClient;
    }

    public static void main(String[] args) {
        new Server().startServer();
    }

}
