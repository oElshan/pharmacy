package isha.store.repository;

import isha.store.entity.SpecCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecCategoryRepo extends JpaRepository<SpecCategory, Integer> {

    List<SpecCategory> findAll();

    SpecCategory findByName(String specCategory);

}
