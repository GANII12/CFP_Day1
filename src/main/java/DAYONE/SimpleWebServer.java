package DAYONE;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleWebServer {
    public static int DEFAULT_PORT = 1467;
    public static int port;
    private HttpServer httpServer;

    public static void main(String[] args) {
        SimpleWebServer webServer = new SimpleWebServer();
        webServer.start(DEFAULT_PORT);
    }

    private void start(int port) {
        this.port = port;
        try {
            httpServer =HttpServer.create(new InetSocketAddress(port) , 0);
            System.out.println("Server Started at port" + port);

            httpServer.createContext("/" , new Handlers.RootHandler());
            httpServer.createContext("/echoHeader" , new Handlers.EchoHeaderHandler());
            httpServer.createContext("/echoGet" , new Handlers.EchoGetHandler());
            httpServer.createContext("/echoPost" , new Handlers.EchoPostHandler());

            httpServer.setExecutor(null);
            httpServer.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
