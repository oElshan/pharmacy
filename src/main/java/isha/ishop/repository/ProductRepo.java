package isha.ishop.repository;

import isha.ishop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    List <Product> findByNameContaining(String name,Pageable pageable);

    //    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% ")
    @Query(value = "SELECT * FROM ishop.product p WHERE p.name LIKE %?1%",
            countQuery = "SELECT count(*) FROM ishop.product p WHERE p.name LIKE %?1%",
            nativeQuery = true)
    Page<Product> searchByNameLike(String name, Pageable pageable);


    Product getById(long id);

    Page<Product> findBySpecCategory_Id(Integer id, Pageable pageable);

    Page<Product> findBySubcategory_Name(String subCategory, Pageable pageable);

}
