package isha.store.repository;

import isha.store.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepo extends JpaRepository<Subcategory, Long> {

    List<Subcategory> findAll();

    Subcategory findByName(String category);
    Subcategory findByUrl(String category);
}
