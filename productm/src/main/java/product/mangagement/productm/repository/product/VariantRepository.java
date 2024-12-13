package product.mangagement.productm.repository.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import product.mangagement.productm.models.products.Variants;

public interface VariantRepository extends MongoRepository<Variants, String > {}
