package isha.ishop.services;

import isha.ishop.entity.Category;
import isha.ishop.entity.Subcategory;
import isha.ishop.entity.Producer;
import isha.ishop.entity.Product;

import java.util.List;

public interface ProductService {

    public Product findProductById(Long id);

    public List<Subcategory> findAllSubCategory();

    public List<Category> findAllCategory();

    public List<Producer> findAllProducer();



}
