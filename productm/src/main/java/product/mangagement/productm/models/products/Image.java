package product.mangagement.productm.models.products;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    boolean isPrimary ;

    private String tumbnail_url;
    
    @Column(nullable = false)
    private String image_url;

    public String getTumbnail_url() {
        return tumbnail_url;
    }

    public void setTumbnail_url(String tumbnail_url) {
        this.tumbnail_url = tumbnail_url;
    }

    public Image() {
        this.isPrimary = false;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
    public Product getProduct() {
        return product;
    }
    public Image(String url, Product pt) {
        this.product = pt;
        this.image_url = url;
        this.isPrimary = true;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public String getImage_url() {
        return image_url;
    }
}
