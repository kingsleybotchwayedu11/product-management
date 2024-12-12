package product.mangagement.productm.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import product.mangagement.productm.models.*;
import product.mangagement.productm.DTO.LoginDto;
import product.mangagement.productm.DTO.UserSaveDTO;
import product.mangagement.productm.service.UserService;
import product.mangagement.productm.utils.JwtUtil;
import product.mangagement.productm.utils.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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
           throw new RuntimeException("User with the same email already saved");
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
                throw new RuntimeException("Wrong password");
                //add login token and send report
            report.put("token", new JwtUtil().generateToken(user.get().getEmail())) ;//new JwtUtil().generateToken(user.get().getEmail()));
            return report;
    }

    @GetMapping("/test")
    public String getMethodName() {
        return "Kofi Ama";
    }
    
    
    
    
}