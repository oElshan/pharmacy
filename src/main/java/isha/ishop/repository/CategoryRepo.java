package isha.ishop.repository;

import isha.ishop.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Long> {

    List<Category> findAll();

}
