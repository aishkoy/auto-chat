package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private final int port;

    private EchoServer(int port) {
        this.port = port;
    }

    public static EchoServer bindToPort(int port) {
        return new EchoServer(port);
    }

    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            try(Socket socket = serverSocket.accept()){
                handle(socket);
            }
        } catch (IOException e){
            System.out.println("Вероятно этот порт уже занят");
            e.printStackTrace();
        }
    }
}
