package isha.ishop.services.impl;

import isha.ishop.entity.*;
import isha.ishop.form.EditProductForm;
import isha.ishop.form.NewProductForm;
import isha.ishop.repository.*;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
public class ProductServiceImpl implements   ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProducerRepo producerRepo;

    @Autowired
    SubCategoryRepo subCategoryRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    SpecCategoryRepo specCategoryRepo;
    @Autowired
    Environment env;


    @Override
    public List<SpecCategory> listAllSpecCategory() {
        return specCategoryRepo.findAll();
    }
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

    @Override
    public Page<Product> findProductByNameLike(String name, int page, int limit) {
//       List <Product> products = productRepo.findByNameContaining(name);
       Page <Product> products = productRepo.searchByNameLike(name,PageRequest.of(page-1,limit));
        return products;
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return productRepo.findByNameContaining(name, PageRequest.of(1,5));
    }

    @Override
    @Transactional
    public Product editProduct(EditProductForm editProductForm)  {
        Product product =  productRepo.findProductById(editProductForm.getId());
        product.setName(editProductForm.getProductName());
        product.setPrice(new BigDecimal(editProductForm.getPrice()));
        product.setDescription(editProductForm.getDescription());
        SpecCategory specCategory = specCategoryRepo.findByName(editProductForm.getSpecCategory());
        product.setSpecCategory(specCategory);
        Subcategory subcategory = subCategoryRepo.findByName(editProductForm.getCategory());
        product.setSubcategory(subcategory);
        product.setVisible(editProductForm.getVisible());

        MultipartFile file = editProductForm.getPhoto();
        uploadImgProduct(file, product);

        Producer producer = producerRepo.findByName(editProductForm.getProducer());
        if (producer == null) {
            producer = new Producer();
            producer.setName(editProductForm.getProducer());

        }
        producer.setProducts(new ArrayList<Product>(Arrays.asList(product)));
        product.setProducer(producer);
        productRepo.save(product);
        return product;
    }


    @Transactional
    @Override

    public Product createProduct(NewProductForm productForm) {

        Product product = new Product();
        product.setName(productForm.getProductName());
        product.setDescription(productForm.getDescription());
        product.setPrice(new BigDecimal(productForm.getPrice()));
        Subcategory subcategory = subCategoryRepo.findByName(productForm.getCategory());
        product.setSubcategory(subcategory);
        SpecCategory specCategory = specCategoryRepo.findByName(productForm.getSpecCategory());
        product.setSpecCategory(specCategory);
        product.setVisible(productForm.getVisible());

        MultipartFile file = productForm.getPhoto();
        uploadImgProduct(file, product);

        Producer producer = producerRepo.findByName(productForm.getProducer());
        if (producer == null) {
            producer = new Producer();
            producer.setName(productForm.getProducer());

        }
        producer.setProducts(new ArrayList<Product>(Arrays.asList(product)));
        product.setProducer(producer);
        productRepo.save(product);
        return product;

    }


    private void uploadImgProduct(MultipartFile file, Product product) {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(env.getProperty("upload.path"));
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            System.out.println("resultFilename-------"+resultFilename);

            try {
                file.transferTo(new File(env.getProperty("upload.path") + "/" + resultFilename));
            } catch (IOException e) {
                System.out.println("error save file to disk");
                e.printStackTrace();
            }
            product.setImgLink(resultFilename);
        }
    }
}
