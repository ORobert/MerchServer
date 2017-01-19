package Server;

import Persistence.IRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by orobe on 31/12/2016.
 */
public class Server {

    private ServerSocket server;

    private int port;

    private IRepository repository;

    public Server(int port){
        this.port=port;
    }

    public void setRepository(IRepository repository){
        this.repository=repository;
        //((ShopRepository) this.repository).seed("products.xml");
    }

    public void start(){
        try {
            server=new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            Socket client;
            System.out.println("Waiting for clients!");
            try {
                client = server.accept();
                (new ClientThread(client,repository)).run();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void stop(){
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
