package server;

import ProcessMessage.MessageProcessor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoServer {
    private final int port;
    private final MessageProcessor messageProcessor;

    private EchoServer(int port) {
        this.port = port;
        this.messageProcessor = new MessageProcessor();
    }

    public static EchoServer bindToPort(int port) {
        return new EchoServer(port);
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            try (Socket socket = serverSocket.accept()) {
                handle(socket);
            }
        } catch (IOException e) {
            System.out.println("Вероятно этот порт уже занят");
            e.printStackTrace();
        }
    }

    private void handle(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        try (Scanner scanner = new Scanner(inputStreamReader)) {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);

            while (true) {
                String message = scanner.nextLine().trim();
                System.out.println("От клиента: " + message);
                String response = processMessage(message);
                writer.println(response);
                writer.flush();

                if (message.equalsIgnoreCase("bye")) {
                    return;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Клиент прервал соединение...");
        }
    }

    private String processMessage(String message) {
        return messageProcessor.processMessage(message);
    }
}
