package isha.ishop.services.impl;

import isha.ishop.entity.ClientOrder;
import isha.ishop.repository.OrderRepo;
import isha.ishop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;

    public List<ClientOrder>  getAllNewOrders() {
       return orderRepo.findAllByStatusName("new");
    }

    @Override
    public long getCountNewOrders(String status) {
        return orderRepo.countAllByStatusName("new");
    }

    @Override
    public List<ClientOrder> getTodayOrder() {
        return orderRepo.findAllClientOrderToday();
    }

    @Override
    public List<ClientOrder> findOrderByName(String name) {

        List<ClientOrder> clientOrders = orderRepo.searchOrderByNameLike(name);
        return clientOrders;
    }

    @Override
    public Page<ClientOrder> getOrdersLimit(int page, int limit) {
       return   orderRepo.findAll(PageRequest.of(page-1,limit, Sort.Direction.DESC,"created"));
//       return   orderRepo.findALL(PageRequest.of(page-1,limit));
    }

    @Override
    public ClientOrder findOrderByPhone(String phone) {


        return orderRepo.findAllByClientPhone(phone);
    }
}
