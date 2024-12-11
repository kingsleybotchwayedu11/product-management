package product.mangagement.productm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "managementUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name; 
    @Column(unique = true)
    String email;
    String hashPassword;
    String role;

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
    public String getHashPassword() {
        return hashPassword;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public User() {};

    public User(String name, String email, String hashPassword, String role) {
        this.name = name; 
        this.email = email;
        this.hashPassword = hashPassword;
        this.role = role;
    }
}
