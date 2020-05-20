package isha.ishop.repository;

import isha.ishop.entity.Producer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProducerRepo extends CrudRepository<Producer, Long> {

    List<Producer> findAll();

}
