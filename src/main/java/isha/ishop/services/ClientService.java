package isha.ishop.services;

import isha.ishop.entity.ClientOrder;
import isha.ishop.form.OrderForm;
import isha.ishop.model.ShoppingCart;

public interface ClientService {

    ClientOrder newClientOrder(ShoppingCart shoppingCart, OrderForm orderForm);
}
