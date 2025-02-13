import server.EchoClient;

public class ClientMain {
    public static void main(String[] args) {
        EchoClient echoClient = EchoClient.connectTo(8080, "localhost");
        echoClient.run();
    }
}
