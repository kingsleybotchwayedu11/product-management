package product.mangagement.productm.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.mangagement.productm.DTO.products.UpdateImageDto;
import product.mangagement.productm.DTO.products.UpdateVariantDto;
import product.mangagement.productm.exceptions.NotFoundExceptions;
import product.mangagement.productm.exceptions.WrongParameterValueException;
import product.mangagement.productm.models.products.Image;
import product.mangagement.productm.models.products.Product;
import product.mangagement.productm.models.products.Variants;
import product.mangagement.productm.repository.product.VariantRepository;

@Service
public class VariantService {

   @Autowired
   VariantRepository variantRepo;

   public Variants addVariants(Variants vt) {
    return variantRepo.save(vt);
   }

   public List<Variants> getProductVariants(Long id) {
      return variantRepo.findByProductId(id);
   }
   
   public Optional<Variants> getVariant(String id){
      return variantRepo.findById(id);
   }
   
   public void deleteVariant(String id) {
        //find image
        var productVariant = variantRepo.findById(id);
        if(productVariant.isEmpty())
            throw new NotFoundExceptions("Cant find variant with id");
        variantRepo.delete(productVariant.get());
    }

    public void updateVariant(UpdateVariantDto updatedVariantData, Product product) {
        //check if
        if(updatedVariantData.getAction() == null)
            throw new WrongParameterValueException("To update variants action is required");
        if(updatedVariantData.getAction().toUpperCase().equals("ADD")) {
            //throw and error
            if(updatedVariantData.getPrice() <= 0 || updatedVariantData.getDescription() == null || updatedVariantData.getQuantity() == null || updatedVariantData.getName() == null)
                throw new WrongParameterValueException("For Action Add of Variant, name, quantity, description price are required");
            //set up image update
            var productVariant = new Variants();
            productVariant.setPrice(updatedVariantData.getPrice());
            productVariant.setName(updatedVariantData.getName());
            productVariant.setDescription(updatedVariantData.getDescription());
            productVariant.setQuantiy(updatedVariantData.getQuantity());
            productVariant.setProductId(product.getId());
            variantRepo.save(productVariant);
            return;
            }

         if(updatedVariantData.getAction().toUpperCase().equals("DELETE")) {
                if(updatedVariantData.getId() == null)
                    throw new WrongParameterValueException("To delete variant Id is required");
                deleteVariant(updatedVariantData.getId());
                return ;
         }

         if(updatedVariantData.getAction().toUpperCase().equals("UPDATE")) {
                if(updatedVariantData.getId() == null)
                    throw new WrongParameterValueException("Action Update Variants requires Id");
                var productVariant = variantRepo.findById(updatedVariantData.getId());
                    if(productVariant.isEmpty())
                        throw new NotFoundExceptions("Cant find variants with id");
                //check to find the fields given
                if(updatedVariantData.getName() != null)
                    productVariant.get().setName(updatedVariantData.getName());
                if(updatedVariantData.getDescription() != null)
                    productVariant.get().setDescription(updatedVariantData.getDescription());
                if(updatedVariantData.getPrice() > 0)
                    productVariant.get().setPrice(updatedVariantData.getPrice());
                if(updatedVariantData.getAvailable() != null)
                    productVariant.get().setAvailable(updatedVariantData.getAvailable());
                
                variantRepo.save(productVariant.get());
                return ;
            }
    }


}
