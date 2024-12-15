package product.mangagement.productm.repository.product;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import product.mangagement.productm.models.products.Variants;

public interface VariantRepository extends MongoRepository<Variants, String > {
    public List<Variants> findByProductId(Long productId);
}
