import server.EchoServer;

public class ServerMain {
    public static void main(String[] args) {
        EchoServer echoServer = EchoServer.bindToPort(8080);
        echoServer.run();
    }
}
