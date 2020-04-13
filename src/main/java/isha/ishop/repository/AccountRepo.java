package isha.ishop.repository;

import isha.ishop.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Long> {

    Account findByName(String name);

    List<Account> findAllByRole(String role);

    List<Account> findAll();

    Account findByEmail(String email);

 }
