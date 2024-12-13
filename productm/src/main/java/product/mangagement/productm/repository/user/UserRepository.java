package product.mangagement.productm.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import product.mangagement.productm.models.users.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
