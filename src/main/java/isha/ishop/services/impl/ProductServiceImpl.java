package isha.ishop.services.impl;

import isha.ishop.entity.*;
import isha.ishop.form.EditProductForm;
import isha.ishop.repository.*;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


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
    public List <Product> findProductByNameLike(String name) {
       List <Product> products = productRepo.findByNameContaining(name);
        return products;
    }

    @Override
    @Transactional
    public Product editProduct(EditProductForm editProductForm)  {


        Product product =  productRepo.findProductById(editProductForm.getId());
        product.setName(editProductForm.getProductName());
        product.setPrice(editProductForm.getPrice());
        product.setDescription(editProductForm.getDescription());
        SpecCategory specCategory = specCategoryRepo.findByName(editProductForm.getSpecCategory());
        product.setSpecCategory(specCategory);
        Subcategory subcategory = subCategoryRepo.findByName(editProductForm.getCategory());
        product.setSubcategory(subcategory);
        product.setVisible(editProductForm.getVisible());

        MultipartFile file = editProductForm.getPhoto();
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
}
