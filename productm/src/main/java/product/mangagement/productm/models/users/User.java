package product.mangagement.productm.models.users;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "managementUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name; 
    @Column(unique = true)
    String email;

    @JsonIgnore
    String hashPassword;
    
    String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    UserVerification verifyCationEntry;

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
    public String getRole() {
        return role;
    }

    public long getId() {
        return id;
    }
    public User() {};

    public User(String name, String email, String hashPassword, String role) {
        this.name = name; 
        this.email = email;
        this.hashPassword = hashPassword;
        this.role = role;
    }
}
