package product.mangagement.productm.DTO.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateImageDto {
    String action;

    Long id;
    String imageUrl;
    Long productId;
    String thumbNailUrl;
    Boolean primary;

    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setThumbNailUrl(String thumbNailUrl) {
        this.thumbNailUrl = thumbNailUrl;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }
    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }
    public Boolean getPrimary() {
        return primary;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getProductId() {
        return productId;
    }
}
