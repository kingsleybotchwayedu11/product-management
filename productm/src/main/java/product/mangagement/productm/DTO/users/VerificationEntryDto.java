package product.mangagement.productm.DTO.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VerificationEntryDto {
   
    @NotNull(message = "Verification Code cant be null")
    @NotBlank(message = "Verification Code is required")
    String verificationCode;

    @NotNull(message = "New Password Required")
    @NotNull(message = "New Password")
    String newPassword;

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
