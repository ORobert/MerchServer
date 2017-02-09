package Server;

import Models.Order;
import Models.Product;
import Models.User;
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

    public void handleUpdateRequest(UpdateRequest request){
		if(request instanceof UpdateLocationRequest){
			UpdateLocationRequest req= (UpdateLocationRequest) request;
			repository.updateLocation(req.getOrders(),req.getLongitude(),req.getLatitude());
		}
    }

    public Object handleRequest(Request request){
        System.out.println("Checking request");
        if (request instanceof GetAllRequest){
            List<Product> products = repository.getAllProducts();
            Response response = new GetAllResponse();
            ((GetAllResponse) response).setProductsList(products);
            return response;
        }
        if(request instanceof OrderProductsRequest){
            repository.orderProducts(((OrderProductsRequest) request).getOwnerId(),((OrderProductsRequest) request).getProducts());
            return new OkResponse();
        }
        if(request instanceof TakeOrdersRequest){
			repository.takeOrders(((TakeOrdersRequest) request).getOrders());
			return new OkResponse();
		}
		if(request instanceof GetProductsByOrderRequest){
        	return new GetAllResponse(repository.getProductsByOrder(((GetProductsByOrderRequest) request).getOrder()));
		}
		if(request instanceof GetOrdersByDriverRequest){
            return new GetOrdersResponse(repository.getOrdersByDriver(((GetOrdersByDriverRequest) request).getUser()));
        }
        if(request instanceof DeliverOrderRequest){
		    repository.deliverOrder(((DeliverOrderRequest) request).getOrder());
		    return new OkResponse();
        }
		if(request instanceof DeliverOrderRequest){
        	DeliverOrderRequest req=((DeliverOrderRequest) request);
        	Order order=req.getOrder();
        	order.setState("Delivered");
			repository.deliverOrder(order);
			return new OkResponse();
		}
		if(request instanceof GetLocationRequest){
			return new LocationResponse(repository.getOrderLocation(((GetLocationRequest) request).getOrder()));
		}
		if (request instanceof LoginRequest){
            User user = repository.login(((LoginRequest) request).getUsername(),((LoginRequest) request).getPassword());
            if (user!=null){
                LoginResponse response = new LoginResponse();
                response.setUser(user);
                return response;
            }
            return new ErrorResponse();
        }
        if(request instanceof GetConfirmedOrdersRequest){
		    return new GetOrdersResponse(repository.getAllConfirmedOrders());
        }
        return null;
    }

    public void run(){
        ObjectInputStream receive = null;
        ObjectOutputStream send = null;
        try {
            receive = new ObjectInputStream(client.getInputStream());
            send = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                System.out.println("Reading request");
                Object request = receive.readObject();
                if(request instanceof UpdateRequest){
                    handleUpdateRequest((UpdateRequest) request);
                }else {
                    Object response = handleRequest((Request)request);
                    System.out.println("Sending response");
                    send.writeObject(response);
                    send.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
