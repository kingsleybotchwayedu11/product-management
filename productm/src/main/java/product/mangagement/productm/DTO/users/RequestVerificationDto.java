package product.mangagement.productm.DTO.users;

import jakarta.validation.constraints.Email;

public class RequestVerificationDto {
    @Email
    String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
