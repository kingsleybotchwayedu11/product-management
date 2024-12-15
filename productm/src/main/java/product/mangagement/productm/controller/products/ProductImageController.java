package product.mangagement.productm.controller.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import product.mangagement.productm.DTO.products.ImageDto;
import product.mangagement.productm.DTO.products.UpdateImageDto;
import product.mangagement.productm.exceptions.NotFoundExceptions;
import product.mangagement.productm.exceptions.WrongParameterValueException;
import product.mangagement.productm.models.products.Image;
import product.mangagement.productm.models.products.Product;
import product.mangagement.productm.service.product.ProductImageService;
import product.mangagement.productm.service.product.ProductService;
import product.mangagement.productm.utils.Bst.CategoryBST;

@RestController
public class ProductImageController {
    @Autowired
    ProductImageService imageService;
    @Autowired
    ProductService proService;

    @GetMapping("/product/{productId}/images")
    public List<Image> getProductImage(@PathVariable Long productId){
        return imageService.getProductImages(productId);
    }

    @PostMapping("/product/image")
    public Product addVariant(@RequestBody @Valid ImageDto imageDto){
        //check if product id is given
        if(imageDto.getProductId() == null)
            throw new WrongParameterValueException("productId is required");

        var product = proService.getProductWithId(imageDto.getProductId());

        if(product == null)
            throw new EntityNotFoundException("Cant find Product with Id");
        imageService.addProductImage(imageDto.mapToImage(product));
        return proService.getProductWithId(product.getId());
    }

    @PutMapping("/product/image")
    public Product updateVariant(@RequestBody @Valid UpdateImageDto updateDto){
        //find product
        if(updateDto.getProductId() == null)
            throw new WrongParameterValueException("productId is required");

        var product = proService.getProductWithId(updateDto.getProductId());

        if(product == null)
            throw new EntityNotFoundException("Cant find Product with Id");
       
        imageService.UpdateImage(updateDto, product);

        return proService.getProductWithId(product.getId());
    }

    @DeleteMapping("/product/{productId}/image/{imageId}")
    public Product deleteVariant(@PathVariable Long productId, @PathVariable Long imageId){
        
        var product = proService.getProductWithId(productId);
        if(product == null)
            throw new EntityNotFoundException("Cant find Product with Id");
        imageService.deleteImage(imageId);
        return proService.getProductWithId(productId);
    }



    @GetMapping("/product/image/{imageId}")
    public Image getVariant(@PathVariable Long imageId){
        var image = imageService.getImage(imageId);
        if(image.isEmpty())
            throw new NotFoundExceptions("Cant find Variants");
        return image.get();
    }
}
