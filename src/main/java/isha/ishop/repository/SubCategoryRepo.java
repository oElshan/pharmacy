package isha.ishop.repository;

import isha.ishop.entity.Subcategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubCategoryRepo extends CrudRepository<Subcategory, Long> {

    List<Subcategory> findAll();

}