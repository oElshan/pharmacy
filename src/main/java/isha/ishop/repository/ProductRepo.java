package isha.ishop.repository;

import isha.ishop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    List <Product> findByNameContaining(String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> searchByNameLike(@Param("name") String name);


    Product getById(long id);


}
