package product.mangagement.productm.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import product.mangagement.productm.models.products.Category;
import product.mangagement.productm.repository.product.CategoryRepository;

@Service
public class CategoryService {
   @Autowired
   CategoryRepository categoryRepo;


   public Category saveCategory(Category category){
    return categoryRepo.save(category);
   }

   public List<Category> getCategories() {
        return categoryRepo.findAll();
   }

   public Optional<Category> findCategory(int id) {
    return categoryRepo.findById(id);
    }

    public boolean deleteCategory(int id) {
        Optional<Category> cat = categoryRepo.findById(id);
        if(cat.isEmpty())
            return false;
        categoryRepo.delete(cat.get());
        return true;
        }
}
