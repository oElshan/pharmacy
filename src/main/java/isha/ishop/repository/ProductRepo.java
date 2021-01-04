package isha.ishop.repository;

import isha.ishop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    @Query(value = "SELECT max(price) FROM ishop.product as p WHERE  p.id_subcategory=?",
            nativeQuery = true)
     BigDecimal maxPriceBySubCategory(long id);

    @Query(value = "SELECT min(price) FROM ishop.product as p WHERE  p.id_subcategory=?",
            nativeQuery = true)
    BigDecimal minPriceBySubCategory(long id);

    List <Product> findByNameContaining(String name,Pageable pageable);

    List <Product> findByNameContaining(String name);
    List <Product> findDistinctByNameContaining(String name);

    //    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% ")
    @Query(value = "SELECT * FROM ishop.product p WHERE p.name LIKE %?1%",
            countQuery = "SELECT count(*) FROM ishop.product p WHERE p.name LIKE %?1%",
            nativeQuery = true)
    Page<Product> searchByNameLike(String name, Pageable pageable);

    @Query(value = "SELECT max(price) FROM ishop.product p WHERE p.name LIKE %?1%",
            nativeQuery = true)
    BigDecimal searchMaxPriceProductByNameLike(String name);

    @Query(value = "SELECT min(price) FROM ishop.product p WHERE p.name LIKE %?1%",
            nativeQuery = true)
    BigDecimal searchMinPriceProductByNameLike(String name);

    Product getById(long id);

    Page<Product> findBySpecCategory_Id(int id, Pageable pageable);

    Page<Product> findBySubcategory_Id(long id, Pageable pageable);

    @Query(value = "SELECT * FROM ishop.product as p right join ishop.subcategory s on p.id_subcategory=s.id left join ishop.category c on s.id_category = c.id where c.id =?",
            countQuery = "SELECT count(*) FROM ishop.product as p right join ishop.subcategory s on p.id_subcategory=s.id left join ishop.category c on s.id_category = c.id where c.id =?",
            nativeQuery = true)
    Page<Product> findByCategory_Id(long id, Pageable pageable);

    Page<Product> findBySubcategory_Name(String subCategory, Pageable pageable);

}
