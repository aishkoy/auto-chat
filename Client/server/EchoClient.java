package server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoClient {
    private final int port;
    private final String host;

    private EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public static EchoClient connectTo(int port, String host) {
        return new EchoClient(port, host);
    }

    public void run(){
        System.out.println("Для выхода напишите 'bye'\n\n\n");

        try (Socket socket = new Socket(host, port)) {
            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Scanner serverScanner = new Scanner(inputStreamReader);

            try(scanner; writer; inputStreamReader){
                while(true){
                    String message = scanner.nextLine();
                    writer.println(message);
                    writer.flush();


                    String response = serverScanner.nextLine().trim();
                    System.out.println("Ответ от сервера: " + response);
                    if (message.equals("bye")) break;
                }
            }
        } catch (NoSuchElementException e){
            System.out.println("Сервер прервал соединение...");
        } catch (IOException e){
            System.out.println("Подключение к серверу не удалось...");
            e.printStackTrace();
        }
    }
}
