package product.mangagement.productm.DTO.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import product.mangagement.productm.models.products.Image;
import product.mangagement.productm.models.products.Product;

public class ImageDto {
    @NotNull(message = "image url is required")
    @NotBlank(message = "image url cant be blank")
    private String image_url;

    private String tumbnail_url;

    private boolean primary;

    private Long productId;

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setPrimary(boolean isPrimary) {
        this.primary = isPrimary;
    }

    public void setTumbnail_url(String tumbnail_url) {
        this.tumbnail_url = tumbnail_url;
    }
    public Image mapToImage(Product pr) {
        var image = new Image();
        image.setProduct(pr);
        image.setImage_url(image_url);
        image.setTumbnail_url(tumbnail_url);
        image.setIsPrimary(primary);
        return image;
    }

}
