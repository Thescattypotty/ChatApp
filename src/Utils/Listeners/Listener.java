package Utils.Listeners;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Controllers.Main.MessagerieController;
import Models.Message;
import Server.Server;

import Utils.User.PasswordAuthenticatedUserInterface;

public class Listener {

    private Socket socket;
    private int port;
    private String hostname;
    public MessagerieController messagerieController;
    public PasswordAuthenticatedUserInterface user;
    public static ArrayList<Listener> ConnectedUsers = null;
    private static ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectIntputStream;

    Logger logger = LoggerFactory.getLogger(Listener.class);

    public Listener(PasswordAuthenticatedUserInterface user, MessagerieController messagerieController) {
        this.port = 9999;
        this.hostname = "localhost";
        this.user = user;
        this.messagerieController = messagerieController;
        try {
            socket = new Socket(hostname, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectIntputStream = new ObjectInputStream(socket.getInputStream());
            Server.AddUser(this.user , null);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        new ServerListener().start();

    }

    public void send(Message message) throws IOException {
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    public void setController(MessagerieController controller) {
        this.messagerieController = controller;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private class ServerListener extends Thread {
        @Override
        public void run() {
            logger.info("Connect Accepted " + socket.getInetAddress() + " : " + socket.getPort());

            try {
                logger.info("Socket is ready to in & out");
                while (true) {
                    Message message = null;
                    message = (Message) objectIntputStream.readObject();
                    logger.info("Waiting for message");
                    if (message != null) {
                        logger.debug("Message Received");
                        messagerieController.receiveMessage(message);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
