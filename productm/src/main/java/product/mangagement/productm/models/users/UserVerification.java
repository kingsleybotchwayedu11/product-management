package product.mangagement.productm.models.users;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class UserVerification {
        @Id
        @GeneratedValue
        private Integer id;

        @OneToOne
        @JoinColumn(name="userId", referencedColumnName = "id")
        private User user;

        @Column(nullable = false, unique = false)
        private String verificationCode;

        LocalDateTime issuedDate;

        public void setVerificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
        }

        public String getVerificationCode() {
            return verificationCode;
        }

        public void setIssuedDate(LocalDateTime issuedDate) {
            this.issuedDate = issuedDate;
        }

        public LocalDateTime getIssuedDate() {
            return issuedDate;
        }

        public void setUser(User user) {
            this.user = user;
        }


        public User getUser() {
            return user;
        }


}
