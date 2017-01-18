/**
 * Created by orobe on 31/12/2016.
 */
package Server;

import Persistence.ShopRepository;

public class StartServer {
    public static void main(String[] args) {
        Server server = new Server(55555);
        server.setRepository(new ShopRepository());
        server.start();
    }
}
