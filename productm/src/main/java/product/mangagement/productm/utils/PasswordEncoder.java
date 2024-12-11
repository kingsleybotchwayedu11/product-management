package product.mangagement.productm.utils;

import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    public   String  encodePassword(String raw_password) {
        return new BCryptPasswordEncoder().encode(raw_password);
    }

    public  boolean decode(String raw, String hash) {
        return new BCryptPasswordEncoder().matches(raw, hash);
    }
}
