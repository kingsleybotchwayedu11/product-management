package product.mangagement.productm.DTO.products;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import product.mangagement.productm.models.products.Category;
import product.mangagement.productm.models.products.Product;

public class ResponseProduct {
    private int categoryId;
    

    private String name;

    private String description;

    List<VariantDto> variants;

    @NotNull(message = "variants are required")
    List<ImageDto> images;

    public int getCategoryId() {
        return categoryId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<ImageDto> getImages() {
        return images;
    }
    // this is to get product id
    public Product mapToProduct(Category cat) {
        Product pt = new Product();
        pt.setDescription(description);
        pt.setName(name);
        pt.setCategory(cat);
        return pt;
    }
    public List<VariantDto> getVariants() {
        return variants;
    }

}
