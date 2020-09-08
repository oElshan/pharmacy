package isha.ishop.services;

import isha.ishop.entity.ClientOrder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    List<ClientOrder> getAllNewOrders();

    long getCountNewOrders(String status);

    List<ClientOrder> getTodayOrder();

    Page<ClientOrder> getOrdersLimit(int page, int limit);

    List<ClientOrder> findOrderByName(String name);

    ClientOrder findOrderByPhone(String phone);




}
