package isha.ishop.services;

import isha.ishop.entity.ClientOrder;
import isha.ishop.entity.Status;
import isha.ishop.dto.EditOrder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    List<ClientOrder> getAllNewOrders();

    long getCountNewOrders(String status);

    List<ClientOrder> getTodayOrder();

    Page<ClientOrder> getOrdersLimit(int page, int limit,String status);

    Page<ClientOrder> getOrdersLimit(int page, int limit);

    List<ClientOrder> findOrderByName(String name);

    ClientOrder findOrderByPhone(String phone);

    List<Status> getAllStatusOrders();

    ClientOrder findClientOrderById(long id);

    ClientOrder updateClientOrderItem(long orderId, long productId, int count);

    ClientOrder updateClientOrder(EditOrder editOrder);

    void deleteOrder(Long id);

    void deleteOrder(ClientOrder clientOrder);

    void deleteItemFromClientOrder( long productId, long orderId);




}
