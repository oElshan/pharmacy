package isha.ishop.repository;

import isha.ishop.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<ClientOrder, Long> {


}
