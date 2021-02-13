package isha.store.services;

import isha.store.entity.ClientOrder;
import isha.store.dto.OrderForm;
import isha.store.model.ShoppingCart;

public interface ClientService {

    ClientOrder newClientOrder(ShoppingCart shoppingCart, OrderForm orderForm);
}
