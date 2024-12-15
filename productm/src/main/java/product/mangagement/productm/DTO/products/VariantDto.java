package product.mangagement.productm.DTO.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import product.mangagement.productm.models.products.Variants;

public class VariantDto {
    @NotNull(message = "Price for Dto cant be null")
    @Positive(message = "Price for variant cant be zero")
    private Double price;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity cant be negative")
    private Integer quantity;
    
    @NotNull(message = "give a name for variant, example 55 inches, 22 inches") 
    @NotBlank(message = "give a name for variant, example 55 inches, 22 inches")
    private String name;

    @NotNull(message = "description cant be blank") 
    @NotBlank(message = "describe the")
    private String description;

    Long productId;

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public VariantDto(double price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
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
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    //for update purpose
    Long id;
    String action;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }

    public Variants mapToVariant(Long productId) {
        Variants variants = new Variants();
        variants.setName(name);
        variants.setDescription(description);
        variants.setProductId(productId);
        variants.setPrice(price);
        variants.setQuantiy(quantity);
        return variants;
    }


}
