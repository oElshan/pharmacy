package isha.store.repository;

import isha.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    List<Category> findAll();

    Category findById(int id);

    Category findByUrl(String url);

}
