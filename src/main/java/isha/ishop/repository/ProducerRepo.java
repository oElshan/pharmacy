package isha.ishop.repository;

import isha.ishop.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepo extends JpaRepository<Producer, Long> {

    List<Producer> findAll();


}
