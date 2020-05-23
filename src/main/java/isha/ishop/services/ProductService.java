package isha.ishop.services;

import isha.ishop.entity.Subcategory;
import isha.ishop.entity.Producer;
import isha.ishop.entity.Product;

import java.util.List;

public interface ProductService {
    public Product findProductById(Long id);

    public List<Subcategory> findAllCategory();

    public List<Producer> findAllProducer();

}
