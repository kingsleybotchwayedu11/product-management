package product.mangagement.productm.controller.products;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import product.mangagement.productm.DTO.products.CategoryDto;
import product.mangagement.productm.models.products.Category;
import product.mangagement.productm.service.product.CategoryService;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/product/category")
    public ResponseEntity<HashMap<String, String>> addCategories(@RequestBody @Valid CategoryDto categoryDto) {
        Category category = categoryService.saveCategory(categoryDto.mapToCategory());
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Category saved");
        response.put("category_id", category.getId() + "");
        response.put("name", category.getName());
        return ResponseEntity.ok(response);
         
    }

    @GetMapping("/product/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
         
    }

    @GetMapping("/product/category/{id}")
    public ResponseEntity<HashMap<String, String>> getCategory( @PathVariable String id) {
        var respones = new HashMap<String, String>();
        try {
        Optional<Category> category = categoryService.findCategory(Integer.parseInt(id));
        if(category.isPresent()) {
            respones.put("name", category.get().getName());
            respones.put("id", category.get().getId() + "");
            return ResponseEntity.ok(respones);
            } else {
            respones.put("message", "Cant find Cateogory with id");
            return  new ResponseEntity<HashMap<String,String>>(respones, HttpStatus.BAD_REQUEST);
            }
        }catch(NumberFormatException ex) {
            respones.put("message", "id cant only be integers");
            return  new ResponseEntity<HashMap<String,String>>(respones, HttpStatus.BAD_REQUEST);
        }  
    }

    @DeleteMapping("/product/category/{id}")
    public ResponseEntity<HashMap<String, String>> deleteCategory( @PathVariable String id) {
        var respones = new HashMap<String, String>();
        try {
        boolean deleted = categoryService.deleteCategory(Integer.parseInt(id));
        if(deleted) {
            respones.put("message", "Category deleted");
            return ResponseEntity.ok(respones);
            } else {
            respones.put("message", "Cant find Category with id ");
            return  new ResponseEntity<HashMap<String,String>>(respones, HttpStatus.BAD_REQUEST);
            }
        }catch(NumberFormatException ex) {
            respones.put("message", "id cant only be integers");
            return  new ResponseEntity<HashMap<String,String>>(respones, HttpStatus.BAD_REQUEST);
        }  
    }

    
}
