package product.mangagement.productm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import product.mangagement.productm.models.*;

import product.mangagement.productm.DTO.UserSaveDTO;
import product.mangagement.productm.service.UserService;
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
           throw new RuntimeException("User with the same email already saved");
        return user;
    }
    
    
}