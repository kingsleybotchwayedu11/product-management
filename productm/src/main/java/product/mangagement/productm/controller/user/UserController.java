package product.mangagement.productm.controller.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import product.mangagement.productm.models.users.User;
import product.mangagement.productm.models.users.UserVerification;
import product.mangagement.productm.service.user.UserService;
import product.mangagement.productm.DTO.users.LoginDto;
import product.mangagement.productm.DTO.users.RequestVerificationDto;
import product.mangagement.productm.DTO.users.UserSaveDTO;
import product.mangagement.productm.DTO.users.VerificationEntryDto;
import product.mangagement.productm.exceptions.EntityAlreadyExistException;
import product.mangagement.productm.exceptions.NotFoundExceptions;
import product.mangagement.productm.exceptions.WrongParameterValueException;
import product.mangagement.productm.utils.JwtUtil;
import product.mangagement.productm.utils.PasswordEncoder;


@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/user")
    public User saveUser(@RequestBody @Valid UserSaveDTO userDetails) {
        //check to see if the ueser is already saved
        var user = userService.addUser(userDetails.mapToUser(encoder));
        if(user == null)
           throw new EntityAlreadyExistException("User with the same email already saved");
        HashMap<String, String> report = new HashMap<>();
        return user;
    }
    
    @PostMapping("/user/login")
    public HashMap<String, String> loginUser(@RequestBody @Valid LoginDto loginDetails) {
            var report = new HashMap<String, String>(); // to return if succesfull
            
            Optional<User> user = userService.findByEmail(loginDetails.getEmail());
            if(!user.isPresent())
                throw new RuntimeException("User not found");
              //check if password is right
            if(!encoder.decode(loginDetails.getPassword(), user.get().getHashPassword()))
                throw new WrongParameterValueException("Wrong password");
                //add login token and send report
            report.put("token", new JwtUtil().generateToken(user.get().getEmail())) ;//new JwtUtil().generateToken(user.get().getEmail()));
            return report;
    }    
    
    
    @PostMapping("/user/password-reset")
    public String requestPasswordReset(@RequestBody @Valid RequestVerificationDto dto) {
        //check to see if user is exist
        var user = userService.findByEmail(dto.getEmail());
        if(user.isEmpty())
            throw new NotFoundExceptions("User with the given email, is not registered");
        //generate verification entry and send response
        var verificationEntry = new UserVerification();
        verificationEntry.setIssuedDate(LocalDateTime.now());
        verificationEntry.setVerificationCode(UUID.randomUUID().toString());
        verificationEntry.setUser(user.get());
        userService.addVerificationEntry(verificationEntry);
        return "VerificationCode: " + verificationEntry.getVerificationCode();
    }

    @PostMapping("/user/change-password")
    public String changePassword(@RequestBody @Valid VerificationEntryDto verificationDetails) {
        //check to see if user is exist
        var verification = userService.getUserVerification(verificationDetails.getVerificationCode());
        if(verification.isEmpty())
            throw new EntityNotFoundException("Wrong Verification Code");
        //generate verification entry and send response
        //check if the verification code has expired
        if(!verification.get().getIssuedDate().isBefore(LocalDateTime.now().plusDays(1)))
                throw new WrongParameterValueException("Verification Code Expired");
        verification.get().getUser().setHashPassword(encoder.encodePassword(verificationDetails.getNewPassword()));
        userService.addUser(verification.get().getUser());
        userService.deleteVerification(verification.get());
        return "password Successfully  changed";
    }

}