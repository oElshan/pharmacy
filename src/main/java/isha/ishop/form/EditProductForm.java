package isha.ishop.form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class EditProductForm {

    private Long id;
    @NotEmpty(message = "Пустое поле! Введите имя клиента!")
    @Size(max = 50,message = "max size  = 50  ")
    private String productName;
    private String producer;
    @NotNull(message = "Пустое поле! Введите цену")
    private BigDecimal price;
    private String description;
    private String dateCreation;
    private String category;
    private String specCategory;
    private String visible;
    private MultipartFile photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String date) {
        this.dateCreation = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecCategory() {
        return specCategory;
    }

    public void setSpecCategory(String specCategory) {
        this.specCategory = specCategory;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }


    @Override
    public String toString() {
        return "EditProductForm{" +
                "productName='" + productName + '\'' +
                ", producer='" + producer + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", category='" + category + '\'' +
                ", specCategory='" + specCategory + '\'' +
                ", visible='" + visible + '\'' +
                ", photo=" + photo +
                '}';
    }
}
