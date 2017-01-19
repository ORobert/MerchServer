package Server;

import Models.Order;
import Models.OrderState;
import Models.Product;
import Persistence.IRepository;
import Protocol.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by orobe on 31/12/2016.
 */
public class ClientThread extends Thread {
    protected Socket client;

    private IRepository repository;

    public ClientThread(Socket client, IRepository repository){
        this.client=client;
        this.repository=repository;
    }

    public Object handleRequest(Request request){
        System.out.println("Chacking request");
        if (request instanceof GetAllRequest){
            List<Product> products = repository.getAllProducts();
            Response response = new GetAllResponse();
            ((GetAllResponse) response).setProductsList(products);
            return response;
        }
        if(request instanceof OrderProductsRequest){
            repository.orderProducts(((OrderProductsRequest) request).getOrder());
            return new OkResponse();
        }
        if(request instanceof TakeOrdersRequest){
			repository.takeOrders(((TakeOrdersRequest) request).getOrders());
			return new OkResponse();
		}
		if(request instanceof DeliverOrderRequest){
        	DeliverOrderRequest req=((DeliverOrderRequest) request);
        	Order order=req.getOrder();
        	order.setState(OrderState.Delivered);
			repository.deliverOrder(order);
			return new OkResponse();
		}
        return null;
    }

    public void run(){
        ObjectInputStream recive = null;
        ObjectOutputStream send = null;
        try {
            send = new ObjectOutputStream(client.getOutputStream());
            recive = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                System.out.println("Reading request");
                Object request = recive.readObject();
                Object response = handleRequest((Request)request);
                System.out.println("Sending response");
                send.writeObject(response);
                send.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
