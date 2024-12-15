package product.mangagement.productm.controller.products;

import javax.swing.text.html.parser.Entity;

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
import product.mangagement.productm.DTO.products.UpdateVariantDto;
import product.mangagement.productm.DTO.products.VariantDto;
import product.mangagement.productm.exceptions.NotFoundExceptions;
import product.mangagement.productm.exceptions.WrongParameterValueException;
import product.mangagement.productm.models.products.Product;
import product.mangagement.productm.models.products.Variants;
import product.mangagement.productm.service.product.ProductService;
import product.mangagement.productm.service.product.VariantService;
import java.util.List;
import java.util.Optional;

@RestController
public class VariantController {
    @Autowired
    public ProductService proService;

    @Autowired
    public VariantService variantService;

    @PostMapping("/product/variant")
    public Product addVariant(@RequestBody @Valid VariantDto variantDto){
        //check if product id is given
        if(variantDto.getProductId() == null)
            throw new WrongParameterValueException("productId is required");

        var product = proService.getProductWithId(variantDto.getProductId());

        if(product == null)
            throw new EntityNotFoundException("Cant find Product with Id");
        variantService.addVariants(variantDto.mapToVariant(product.getId()));
        return proService.getProductWithId(product.getId());
    }

    @PutMapping("/product/variant")
    public Product updateVariant(@RequestBody @Valid UpdateVariantDto updateDto){
        //find product
        if(updateDto.getProductId() == null)
            throw new WrongParameterValueException("productId is required");

        var product = proService.getProductWithId(updateDto.getProductId());

        if(product == null)
            throw new EntityNotFoundException("Cant find Product with Id");
        updateDto.setAction("update");

        variantService.updateVariant(updateDto, product);

        return proService.getProductWithId(product.getId());
    }

    @DeleteMapping("/product/{productId}/variant/{variantId}")
    public Product deleteVariant(@PathVariable Long productId, @PathVariable String variantId){
        
        var product = proService.getProductWithId(productId);
        if(product == null)
            throw new EntityNotFoundException("Cant find Product with Id");
        variantService.deleteVariant(variantId);
        return proService.getProductWithId(productId);
    }


    @GetMapping("/product/{productId}/variants")
    public List<Variants> getProductVariants(@PathVariable Long productId){
        return variantService.getProductVariants(productId);
    }


    @GetMapping("/product/variants/{variantId}")
    public Variants getVariant(@PathVariable String variantId){
        var variants = variantService.getVariant(variantId);
        if(variants.isEmpty())
            throw new NotFoundExceptions("Cant find Variants");
        return variants.get();
    }
}
