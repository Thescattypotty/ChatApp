package Server;

import java.io.IOException;
import java.io.InputStream;
 import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Models.Message;

public class Server {
    private static final int PORT = 9999;
    private static HashSet<ObjectOutputStream> writers = new HashSet<>();
    static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        logger.info("Server running on Port :" + PORT);
        ServerSocket listener = new ServerSocket(PORT);

        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("Server Closed .");
            listener.close();
        }
    }

    private static class Handler extends Thread {
        private Socket socket;
        private Logger logger =  LoggerFactory.getLogger(Handler.class);
        private ObjectInputStream input;
        private OutputStream os;
        private ObjectOutputStream output;
        private InputStream is;

        public Handler(Socket socket) throws IOException {
            this.socket = socket;
        }

        public void run() {
            logger.info("Waiting to connect a User ....");
            try {
                is = socket.getInputStream();
                input = new ObjectInputStream(is);
                os = socket.getOutputStream();
                output = new ObjectOutputStream(os);

                writers.add(output);

                while (true) {
                    Message inputmsg = (Message) input.readObject();
                    if(inputmsg != null)
                    {
                        logger.info("Message received(while true): " + inputmsg.toString());
                        broadcastMessage(inputmsg);
                    }
                }
            } catch (SocketException socketException) {
                logger.error("Socket Exception ");
            } catch (Exception e) {
                logger.error("Exception in run() method for user: "+ e);
            } finally {
                closeConnections();
            }
        }

        private void broadcastMessage(Message message)
        {
            for(ObjectOutputStream o : writers)
            {
                try {
                    logger.info("message sended from broadcastMessage : " + message.toString());
                    o.writeObject(message);
                    o.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("Message didn't go");
                }
            }
        }
        private synchronized void closeConnections() {
            try {
                this.input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.debug("Server CLosed Successfully !");
        
    }

    }
}
