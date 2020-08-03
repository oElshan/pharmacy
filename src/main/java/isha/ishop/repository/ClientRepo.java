package isha.ishop.repository;

import isha.ishop.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Client findByPhone(String phone);

}
