package product.mangagement.productm.models.products;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Document
public class Variants {
    @Id
    @GeneratedValue
    private String id;

    private String name;
    private double price;
    private String description;
    private boolean available;
    private Long product_id;
    private int quantiy;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public Long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
    public Variants() {
        this.available = true;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    
    public Variants(String name, String description,  Long product_id) {
        this.available = true;
        this.name = name;
        this.description= description;
        this.product_id = product_id;
    }
}
