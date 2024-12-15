package product.mangagement.productm.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import product.mangagement.productm.models.products.Image;
import java.util.*;

public interface ImagesRepository extends JpaRepository<Image, Long> {
    public List<Image> findByProductId(Long id);
} 