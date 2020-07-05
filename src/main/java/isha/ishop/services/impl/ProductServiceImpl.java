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
import org.springframework.data.domain.PageRequest;
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

    /**
     * метод возвращает список продуктов , передаются праметры
     * @param page номер старницы
     * @param limit скольок будет данных на странице
     * этот обьект используется методом для того чтобы запросить данный по лимиту , каждая страничка это опредленый лимит данных
     * @return
     */
    @Override
    public List<Product> listAllProducts(int page, int limit) {
        return productRepo.findAll(PageRequest.of(page - 1, limit)).getContent();
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
