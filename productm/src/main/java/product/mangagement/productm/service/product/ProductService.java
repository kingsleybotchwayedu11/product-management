package product.mangagement.productm.service.product;

import java.util.Optional;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import product.mangagement.productm.DTO.products.UpdateImageDto;
import product.mangagement.productm.DTO.products.UpdateProductDto;
import product.mangagement.productm.DTO.products.UpdateVariantDto;
import product.mangagement.productm.exceptions.NotFoundExceptions;
import product.mangagement.productm.models.products.Product;
import product.mangagement.productm.repository.product.CategoryRepository;
import product.mangagement.productm.repository.product.ImagesRepository;
import product.mangagement.productm.repository.product.ProductRespository;
import product.mangagement.productm.repository.product.VariantRepository;
import java.util.List;

@Service
public class ProductService {
    

  @Autowired
  VariantRepository variantRepo;
  
  @Autowired
  ImagesRepository imageRepo;

  @Autowired
  ProductRespository proRepo;

  @Autowired
  CategoryRepository categoryRepo;

  @Autowired
  ProductImageService productImageService;
  @Autowired
  VariantService variantService;


 public Product addProduct(Product pt){
    return proRepo.save(pt);
 }

 public Page<Product> getProducts(Pageable page){
    return proRepo.findAll(page);
 }

 public Product getProductWithId(Long id){
   var product = proRepo.findById(id);
   //find the product
   if(product.isEmpty())
      return null;
   var variants = variantRepo.findByProductId(product.get().getId());
   product.get().setVariants(variants);
   return product.get();
}

 public Optional<Product> findByName(String name) {
      return proRepo.findByName(name);
 }

 public boolean deleteProduct(Long id) {
   var product = proRepo.findById(id);
   if(product.isEmpty())
      return false;
   else {
      proRepo.delete(product.get());
      return true;
   }
   
 }

 public Product updateProduct(UpdateProductDto updateDetails, Long id) {
      var product = proRepo.findById(id);
      if(product.isEmpty())
         throw new NotFoundExceptions("Product to update not found");
      
      if(updateDetails.getCategoryId() != null) {
            //find the category
            var category  = categoryRepo.findById(updateDetails.getCategoryId());
            if(category.isEmpty())
               throw new NotFoundExceptions("Cant find category with this id");
            product.get().setCategory(category.get());
      }
      //else update according
      if(updateDetails.getAvailable() != null) 
         product.get().setAvailable(updateDetails.getAvailable());
      
      if(updateDetails.getName() != null) 
       product.get().setName(updateDetails.getName());

      if(updateDetails.getDescription() != null) 
       product.get().setDescription((updateDetails.getDescription()));

      if (updateDetails.getImages() != null && updateDetails.getImages().size() != 0){
         for(UpdateImageDto updateImageDetails : updateDetails.getImages())
            productImageService.UpdateImage(updateImageDetails, product.get());
      }

      if (updateDetails.getVariants() != null && updateDetails.getVariants().size() != 0){
         for(UpdateVariantDto updateImageDetails : updateDetails.getVariants())
            variantService.updateVariant(updateImageDetails, product.get());
      }
      Product pro = proRepo.save(product.get());
      pro.setVariants(variantService.getProductVariants(pro.getId()));
      return pro;
     
  } 

public Page<Product> getProductByCategories(Integer categoryId,Pageable page){
   return proRepo.findByCategoryId(categoryId, page);
}
}
