package product.mangagement.productm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import product.mangagement.productm.models.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
