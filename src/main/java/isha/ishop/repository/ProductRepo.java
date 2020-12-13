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

    Page<Product> findBySpecCategory_Id(int id, Pageable pageable);

    Page<Product> findBySubcategory_Id(long id, Pageable pageable);

    @Query(value = "SELECT * FROM ishop.product as p right join ishop.subcategory s on p.id_subcategory=s.id left join ishop.category c on s.id_category = c.id where c.id =?",
            countQuery = "SELECT count(*) FROM ishop.product as p right join ishop.subcategory s on p.id_subcategory=s.id left join ishop.category c on s.id_category = c.id where c.id =?",
            nativeQuery = true)
    Page<Product> findByCategory_Id(long id, Pageable pageable);

    Page<Product> findBySubcategory_Name(String subCategory, Pageable pageable);

}
