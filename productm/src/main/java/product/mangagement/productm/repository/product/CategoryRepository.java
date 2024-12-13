package product.mangagement.productm.repository.product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import product.mangagement.productm.models.products.Category;
import product.mangagement.productm.models.products.Product;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
}