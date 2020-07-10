package isha.ishop.repository;

import isha.ishop.entity.Account;
import isha.ishop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    Product getById(long id);

}
