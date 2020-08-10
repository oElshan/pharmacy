package isha.ishop.services;

import isha.ishop.entity.*;
import isha.ishop.form.EditProductForm;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public Product findProductById(Long id);

    public List <Product> findProductByNameLike(String name);

    public List<Subcategory> findAllSubCategory();

    public List<Category> findAllCategory();

    public List<Producer> findAllProducer();

    public List<Product> listAllProducts(int page, int limit);

    public List<SpecCategory> listAllSpecCategory();

    Product editProduct(EditProductForm editProductForm) throws IOException;





}
