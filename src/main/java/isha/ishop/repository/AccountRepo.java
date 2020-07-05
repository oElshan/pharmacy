package isha.ishop.repository;

import isha.ishop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findByName(String name);

    List<Account> findAllByRole(String role);

    List<Account> findAll();

    Account findByEmail(String email);

 }
