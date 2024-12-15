package product.mangagement.productm.repository.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import product.mangagement.productm.models.products.Product;

public interface ProductRespository extends JpaRepository<Product, Long> {

    public Optional<Product> findByName(String name);

    public Page<Product> findByCategoryId(Integer id, Pageable page);

    
}
