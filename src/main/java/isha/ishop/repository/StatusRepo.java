package isha.ishop.repository;

import isha.ishop.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Integer> {

    Status getById(Integer id);

    Status findByName(String name);
}
