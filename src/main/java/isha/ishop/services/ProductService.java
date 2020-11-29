package isha.ishop.services;

import isha.ishop.entity.*;
import isha.ishop.form.EditProductForm;
import isha.ishop.form.NewProductForm;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public Product findProductById(Long id);

    List <Product> findByNameContaining(String name);

    public Page<Product> findProductByNameLike(String name, int page, int limit);

    public List<Subcategory> findAllSubCategory();

    public List<Category> findAllCategory();

    public List<Producer> findAllProducer();

    public List<Product> listAllProducts(int page, int limit);

    public List<SpecCategory> listAllSpecCategory();

    Product editProduct(EditProductForm editProductForm) throws IOException;

    Product createProduct(NewProductForm productForm);

     List<Product> listAllProductsForSpecCategory(int id, int page, int limit);

    Page<Product> findAllProductBySubCategoryName(String subCategory,int page, int limit);







    }
