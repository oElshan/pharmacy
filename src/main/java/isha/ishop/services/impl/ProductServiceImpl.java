package isha.ishop.services.impl;

import isha.ishop.entity.Category;
import isha.ishop.entity.Subcategory;
import isha.ishop.entity.Producer;
import isha.ishop.entity.Product;
import isha.ishop.repository.CategoryRepo;
import isha.ishop.repository.SubCategoryRepo;
import isha.ishop.repository.ProducerRepo;
import isha.ishop.repository.ProductRepo;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProducerRepo producerRepo;

    @Autowired
    SubCategoryRepo subCategoryRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public Product findProductById(Long id) {
        return productRepo.findProductById(id);
    }

    @Override
    public List<Subcategory> findAllSubCategory() {
        return subCategoryRepo.findAll();
    }

    @Override
    public List<Producer> findAllProducer() {
        return producerRepo.findAll();
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepo.findAll();
    }
}
