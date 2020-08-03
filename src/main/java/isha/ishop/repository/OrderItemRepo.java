package isha.ishop.repository;

import isha.ishop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {



    List<OrderItem> findAll();

   OrderItem save(OrderItem orderItem);
}
