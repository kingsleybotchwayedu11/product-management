package product.mangagement.productm.DTO.products;

import org.aspectj.lang.annotation.RequiredTypes;

import jakarta.validation.constraints.NotNull;
import product.mangagement.productm.models.products.Image;
import product.mangagement.productm.models.products.Product;

public class ImageDto {
    @NotNull(message = "image url is required")
    private String image_url;

    private String tumbnail_url;

    private boolean isPrimary;

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    Image mapToImage(Product pr) {
        var image = new Image();
        image.setProduct(pr);
        image.setImage_url(image_url);
        image.setTumbnail_url(tumbnail_url);
        return image;
    }

}
