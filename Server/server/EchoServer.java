package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoServer {
    private final int port;

    private EchoServer(int port) {
        this.port = port;
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
        return message.equalsIgnoreCase("bye") ? "bye bye!" :
                message.equalsIgnoreCase("date") ? LocalDate.now().format(DateTimeFormatter.ISO_DATE) :
                        message.equalsIgnoreCase("time") ? LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) :
                                message.toLowerCase().startsWith("reverse ") ? new StringBuilder(message.substring(8)).reverse().toString() :
                                        message.toLowerCase().startsWith("upper ") ? message.substring(6).toUpperCase() :
                                                message;
    }

}
