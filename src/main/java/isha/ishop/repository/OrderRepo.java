package isha.ishop.repository;

import isha.ishop.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<ClientOrder, Long> {

    List<ClientOrder> findAllByStatusName(String status);

    @Query(
            value = "SELECT * FROM clientOrder c WHERE  date(c.created) = current_date",
            nativeQuery = true)
    List<ClientOrder> findAllClientOrderToday();

    long countAllByStatusName(String status);

    @Query("SELECT c FROM ClientOrder c WHERE c.client.firstName LIKE %:name%")
    List<ClientOrder> searchOrderByNameLike(@Param("name") String name);


    List<ClientOrder> findAllByClient_LastName(String name);

    ClientOrder findAllByClientPhone(String phone);




}
