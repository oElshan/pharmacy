package isha.ishop.repository;

import isha.ishop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    List<Category> findAll();

}
