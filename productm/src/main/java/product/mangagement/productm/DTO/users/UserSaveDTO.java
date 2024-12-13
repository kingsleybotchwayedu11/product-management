package product.mangagement.productm.DTO.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import product.mangagement.productm.models.users.User;
import product.mangagement.productm.utils.*;
public class UserSaveDTO {
    @Valid

    @NotBlank(message = "cant be blank")
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password can't be blank")
    private String password;

    @Email(message = "Email must be valid")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Role is required")
    @NotBlank(message = "Role is required")
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User mapToUser(PasswordEncoder passwordEncoder) {
        return new User(name, email, passwordEncoder.encodePassword(password), role);
    }

    public UserSaveDTO(){}
}
