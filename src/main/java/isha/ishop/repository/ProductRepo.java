package isha.ishop.repository;

import isha.ishop.entity.Account;
import isha.ishop.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {

    Product findProductById(Long id);

}
