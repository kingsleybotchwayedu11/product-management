package product.mangagement.productm.controller.products;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import product.mangagement.productm.DTO.products.ImageDto;
import product.mangagement.productm.DTO.products.ProductDto;
import product.mangagement.productm.DTO.products.UpdateProductDto;
import product.mangagement.productm.exceptions.EntityAlreadyExistException;
import product.mangagement.productm.exceptions.NotFoundExceptions;
import product.mangagement.productm.exceptions.WrongParameterValueException;
import product.mangagement.productm.models.products.Category;
import product.mangagement.productm.models.products.Product;
import product.mangagement.productm.models.products.Variants;
import product.mangagement.productm.repository.product.ProductRespository;
import product.mangagement.productm.service.product.CategoryService;
import product.mangagement.productm.service.product.ProductImageService;
import product.mangagement.productm.service.product.ProductService;
import product.mangagement.productm.service.product.VariantService;
import product.mangagement.productm.utils.Bst.CategoryBST;
import product.mangagement.productm.utils.Bst.CategoryNode;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    VariantService variantService;
    @Autowired
    CategoryBST categoriesTress;

        
    @PostMapping("/product/item")
    public ResponseEntity<HashMap<String, String>> addProduct(@RequestBody @Valid ProductDto productData){
        //check if product is saved
        var product = productService.findByName(productData.getName());
        if(product.isPresent())
            throw new EntityAlreadyExistException("Product already exist");
        //check if the category is already saved
        Optional<Category> category = categoryService.findCategory(productData.getCategoryId());
        if(!category.isPresent()) 
            throw new NotFoundExceptions("Category not found");
        
        Product pt = productService.addProduct(productData.mapToProduct(category.get())); // product
        //add check to see if images where given
        if(productData.getImages() != null || productData.getVariants() != null) {
            int maxLength  = (productData.getImages().size() > productData.getImages().size()) ? productData.getImages().size() : productData.getImages().size();
            for(int i =0; i < maxLength; i++){
                if(i < productData.getImages().size())
                    productImageService.addProductImage(productData.getImages().get(i).mapToImage(pt));
                if(i < productData.getVariants().size())
                    variantService.addVariants(productData.getVariants().get(i).mapToVariant(pt.getId()));
            }
        }
        var report = new HashMap<String, String>();
        report.put("message", "item saved");
        report.put("productId", pt.getId().toString());
        return ResponseEntity.ok(report);
    }

    @GetMapping("/product/item")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        if(size < 0 || page < 0)
            throw new WrongParameterValueException("if size is given it cant be less than 1, if page is  cant be a negative number");
        
            Pageable pageQuery = PageRequest.of(page, size);
        //get page content and set the variants
        var pageProducts = productService.getProducts(pageQuery);

        for(Product product : pageProducts.getContent()){
            List<Variants> variants = variantService.getProductVariants(product.getId());
            product.setVariants(variants);
        }
        return pageProducts;
    }

    @GetMapping("/product/item/{id}")
    public Product getProduct(@PathVariable Long id){
           var product = productService.getProductWithId(id);
           if(product == null)
                throw new NotFoundExceptions("Cant find product with id");
           return product; 
    }


    @DeleteMapping("/product/item/{id}")
    public String deleteProductItem(@PathVariable Long id) {
        //tries to delete product
        boolean deleted = productService.deleteProduct(id);
        if(!deleted)
            throw new NotFoundExceptions("Cant find product with such Id");
        return "Product deleted";
        }

        @PutMapping("/product/item/{id}")
        public Product updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDto updateData) {
            //check if product exist
            return productService.updateProduct(updateData, id);
        }
     
    @GetMapping("/products/categories/{categoryId}") 
    public Page<Product> findProductCategory(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue="10") int size, @PathVariable Integer categoryId){
        if(size < 0 || page < 0)
            throw new WrongParameterValueException("if size is given it cant be less than 1, if page is  cant be a negative number");
        //check to see if page is in tree
        var cachedCategoryPage = categoriesTress.getPageItems(categoryId, page);
        if(cachedCategoryPage != null)
            return cachedCategoryPage;
        
        Pageable pageQuery = PageRequest.of(page, size);

        var pageProducts = productService.getProductByCategories(categoryId,pageQuery);

        for(Product product : pageProducts.getContent()){
            List<Variants> variants = variantService.getProductVariants(product.getId());
            product.setVariants(variants);
        }
        //search to see if categories page is  cached
        if(pageProducts.getContent().size() != 0) {
            CategoryNode categoryNode = categoriesTress.search(categoryId);
            if(categoryNode != null)
                categoryNode.addPage(pageProducts, page);
            else {
            //insert category cached
                categoriesTress.insert(categoryId, page, pageProducts);
            }
        }
        return pageProducts;
    }

}
