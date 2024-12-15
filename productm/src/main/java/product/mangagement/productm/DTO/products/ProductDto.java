package product.mangagement.productm.DTO.products;

import com.mongodb.lang.NonNull;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import product.mangagement.productm.models.products.Category;
import product.mangagement.productm.models.products.Image;
import product.mangagement.productm.models.products.Product;

import java.util.List;

public class ProductDto {

    @NotNull(message = "Category id required")
    private int categoryId;
    
    @NotNull(message = "Name cant be blank")
    @NotBlank(message = "Name of product can be blank")
    private String name;

    @NotNull(message="Description of product is required")
    @NotBlank(message = "Description cant be blank")
    private String description;

    @NotNull(message = "variants are required")
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
