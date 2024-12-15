package product.mangagement.productm.DTO.products;
import jakarta.validation.constraints.NotNull;
import product.mangagement.productm.models.products.Product;

import java.util.List;

public class UpdateProductDto {

    private Boolean available;
    private String  description;
    private String  name;
    private Integer categoryId;

    private List<UpdateImageDto> images;

    private List<UpdateVariantDto> variants;

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<UpdateImageDto> getImages() {
        return images;
    }
    public List<UpdateVariantDto> getVariants() {
        return variants;
    }
    public void setImages(List<UpdateImageDto> images) {
        this.images = images;
    }
    public void setVariants(List<UpdateVariantDto> variants) {
        this.variants = variants;
    }

    public void updateProduct(Product pt) {

        if (available != null) 
            pt.setAvailable(available);
        
        if(description != null)
            pt.setDescription(description);

        if(name != null)
            pt.setName(name);
    }

}
