package product.mangagement.productm.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.mangagement.productm.DTO.products.UpdateImageDto;
import product.mangagement.productm.exceptions.NotFoundExceptions;
import product.mangagement.productm.exceptions.WrongParameterValueException;
import product.mangagement.productm.models.products.Image;
import product.mangagement.productm.models.products.Product;
import product.mangagement.productm.repository.product.ImagesRepository;
import java.util.*;

@Service
public class ProductImageService {
    @Autowired
    ImagesRepository imageRepo;

    public Image addProductImage(Image productImage){
        return imageRepo.save(productImage);
    }

    public void deleteImage(Long id) {
        //find image
        var productImage = imageRepo.findById(id);
        if(productImage.isEmpty())
            throw new NotFoundExceptions("Cant find product with id");
        imageRepo.delete(productImage.get());

    }

    public void UpdateImage(UpdateImageDto updateImageData, Product product) {
        if(updateImageData.getAction() == null)
            throw new WrongParameterValueException("To update variants action is required");
        //check if 
        if(updateImageData.getAction().toUpperCase().equals("ADD")) {
            //throw and error
            if(updateImageData.getImageUrl() == null)
                throw new WrongParameterValueException("For Action Add of Image, the image url is required");
            //set up image update
            var productImage = new Image();
            productImage.setImage_url(updateImageData.getImageUrl());
            productImage.setProduct(product);
            productImage.setTumbnail_url(updateImageData.getImageUrl());
            imageRepo.save(productImage);
            return;
            }

            if(updateImageData.getAction().toUpperCase().equals("DELETE")) {
                if(updateImageData.getId() == null)
                    throw new WrongParameterValueException("Action Delete Image Id is required");
                deleteImage(updateImageData.getId());
                return ;
            }

            if(updateImageData.getAction().toUpperCase().equals("UPDATE")) {
                if(updateImageData.getId() == null)
                    throw new WrongParameterValueException("Action Update Image Id is required");
                var productImage = imageRepo.findById(updateImageData.getId());
                    if(productImage.isEmpty())
                        throw new NotFoundExceptions("Cant find product with id");
                //check to find the fields given
                if(updateImageData.getImageUrl() != null)
                    productImage.get().setImage_url(updateImageData.getImageUrl());
                if(updateImageData.getPrimary() != null)
                    productImage.get().setIsPrimary(updateImageData.getPrimary());
                if(updateImageData.getThumbNailUrl() != null)
                    productImage.get().setTumbnail_url(updateImageData.getThumbNailUrl());
                
                imageRepo.save(productImage.get());
                return ;
            }
    }

    public List<Image> getProductImages(Long Id) {
        return imageRepo.findByProductId(Id);
    }

    public Optional<Image> getImage(Long id) {
        return imageRepo.findById(id);
    }
   
}
