package isha.ishop.repository;

import isha.ishop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    @Query(value = "SELECT max(price) FROM ishop.product as p WHERE  p.id_subcategory=?",
            nativeQuery = true)
     BigDecimal maxPriceBySubCategory(long id);

    @Query(value = "SELECT min(price),max(price) FROM ishop.product as p JOIN ishop.subcategory s on p.id_subcategory=s.id WHERE  s.url=?",
            nativeQuery = true)
   List<BigDecimal[]> minMaxPriceBySubCategory(String url);

    List <Product> findByNameContaining(String name,Pageable pageable);

    List <Product> findByNameContaining(String name);

    List <Product> findDistinctByNameContaining(String name);

    //    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% ")
    @Query(value = "SELECT * FROM ishop.product p WHERE p.name LIKE %?1%",
            countQuery = "SELECT count(*) FROM ishop.product p WHERE p.name LIKE %?1%",
            nativeQuery = true)
    Page<Product> findByNameLike(String name, Pageable pageable);

    Page<Product> findByNameContainingAndPriceBetween(String name,BigDecimal piceMin,BigDecimal priceMax, Pageable pageable);

    Page<Product> findByNameContainingAndProducer_IdIn(String name, Set<Long> producerId, Pageable pageable);

   Page<Product> findByNameContainingAndPriceBetweenAndProducer_IdIn(String name, BigDecimal piceMin, BigDecimal priceMax, Set<Long> producerId, Pageable pageable);




    @Query(value = "SELECT max(price) FROM ishop.product p WHERE p.name LIKE %?1%",
            nativeQuery = true)
    BigDecimal searchMaxPriceProductByNameLike(String name);

    @Query(value = "SELECT min(price),max(price) FROM ishop.product p WHERE p.name LIKE %?1%",
            nativeQuery = true)
    List<BigDecimal[]> searchMinMaxPriceProductByNameLike(String name);

    Product getById(long id);

    Page<Product> findBySpecCategory_Id(int id, Pageable pageable);

    Page<Product> findBySubcategory_Id(long id, Pageable pageable);

    Page<Product> findBySubcategory_Url(String name, Pageable pageable);

    List<Product> findAllBySubcategory_Url(String name);

    @Query(value = "SELECT * FROM ishop.product as p right join ishop.subcategory s on p.id_subcategory=s.id left join ishop.category c on s.id_category = c.id where c.id =?",
            countQuery = "SELECT count(*) FROM ishop.product as p right join ishop.subcategory s on p.id_subcategory=s.id left join ishop.category c on s.id_category = c.id where c.id =?",
            nativeQuery = true)
    Page<Product> findByCategory_Id(long id, Pageable pageable);


    Page<Product> findBySubcategory_IdAndPriceBetweenAndProducer_IdIn(long id, BigDecimal min, BigDecimal max, Set<Long> producerId , Pageable pageable);

    Page<Product> findBySubcategory_IdAndPriceBetween(long id, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}
