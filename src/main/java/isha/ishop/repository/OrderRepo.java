package isha.ishop.repository;

import isha.ishop.entity.ClientOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(
            value = "SELECT * FROM ishop.clientOrder c join ishop.status s ON c.id_status = s.id where s.name =?1;",
            countQuery = "SELECT * FROM ishop.clientOrder c join ishop.status s ON c.id_status = s.id where s.name =?1;",
            nativeQuery = true)
    Page<ClientOrder> findAllByStatusSelect(String name, Pageable page);

    ClientOrder findAllByClientPhone(String phone);


    Page<ClientOrder> findAllByStatusName(String name, Pageable page);

    ClientOrder findById(long id);

    void deleteById(Long id);



}
