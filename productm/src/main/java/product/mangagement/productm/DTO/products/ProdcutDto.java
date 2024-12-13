package product.mangagement.productm.DTO.products;

import com.mongodb.lang.NonNull;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ProdcutDto {

    @NotNull(message = "Category id required")
    @NotBlank(message = "Category id requiured")
    private int categoryId;
    
    @NotNull(message = "Name cant be blankk")
    @NotBlank(message = "Name of product can be blanke")
    private String name;

    @NotNull(message="Description of product is required")
    @NotBlank(message = "Description cant be blank")
    private String description;

    List<VariantDto> variants;

    List<ImageDto> images;
}
