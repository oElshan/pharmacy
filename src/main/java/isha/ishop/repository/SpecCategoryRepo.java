package isha.ishop.repository;

import isha.ishop.entity.SpecCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecCategoryRepo extends JpaRepository<SpecCategory, Integer> {

    List<SpecCategory> findAll();

    SpecCategory findByName(String specCategory);

}
