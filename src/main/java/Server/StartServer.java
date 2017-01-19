/**
 * Created by orobe on 31/12/2016.
 */
package Server;

import Persistence.Repository;

public class StartServer {
    public static void main(String[] args) {
        Server server = new Server(55555);
        server.setRepository(new Repository());
        server.start();
    }
}
