package isha.ishop.services.impl;


import isha.ishop.entity.Client;
import isha.ishop.entity.ClientOrder;
import isha.ishop.entity.OrderItem;
import isha.ishop.entity.Status;
import isha.ishop.dto.OrderForm;
import isha.ishop.model.ShoppingCart;
import isha.ishop.model.ShoppingCartItem;
import isha.ishop.repository.*;
import isha.ishop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ClientServiceImpl  implements ClientService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    StatusRepo statusRepo;

    @Transactional
    public ClientOrder newClientOrder(ShoppingCart shoppingCart, OrderForm orderForm) {

        Collection<ShoppingCartItem> items = shoppingCart.getItems();
        ClientOrder clientOrder = new ClientOrder();
        List<OrderItem> orderItems = new ArrayList<>();
        clientOrder.setOrderItems(orderItems);
        clientOrder.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        for (ShoppingCartItem item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setCount(item.getCount());
            orderItem.setClientOrder(clientOrder);
            clientOrder.getOrderItems().add(orderItem);
        }


        List<ClientOrder> clientOrders = new ArrayList<>();


       Client client = clientRepo.findByPhone(orderForm.getPhone());

        if (client== null) {
            client = new Client();
            client.setFirstName(orderForm.getFirstName());
            client.setLastName(orderForm.getLastName());
            client.setStreetAddress(orderForm.getStreetAddress());
            client.setTown(orderForm.getTown());
            client.setEmail(orderForm.getEmail());
            client.setPhone(orderForm.getPhone());
        }
        clientOrder.setClient(client);
        Status status = statusRepo.getById(1);
        clientOrder.setStatus(status);
        clientOrders.add(orderRepo.save(clientOrder));
        client.setClientOrders(clientOrders);


        clientRepo.save(client);
        return clientOrder;
    }




}
