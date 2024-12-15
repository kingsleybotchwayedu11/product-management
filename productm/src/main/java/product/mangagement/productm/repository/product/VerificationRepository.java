package product.mangagement.productm.repository.product;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import product.mangagement.productm.models.users.UserVerification;

@Repository
public interface VerificationRepository  extends JpaRepository<UserVerification, Long> {
    public Optional<UserVerification> findByVerificationCode(String vCode);  
}; 