package product.mangagement.productm.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import product.mangagement.productm.models.products.Product;

public interface ProductRespository extends JpaRepository<Product, Long> {}
