package product.mangagement.productm.DTO.products;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import product.mangagement.productm.models.products.Category;

public class CategoryDto {

    @NotNull(message = "category name is required")
    @NotBlank(message = "Category is required")
    String name;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public Category mapToCategory() {
        var category = new Category();
        category.setName(name);
        return category;
    }
}