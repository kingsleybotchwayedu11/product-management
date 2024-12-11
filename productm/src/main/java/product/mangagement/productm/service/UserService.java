package product.mangagement.productm.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import  product.mangagement.productm.models.User;

import org.springframework.stereotype.Service;

import product.mangagement.productm.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userepo;

    public User addUser(User user){
        //save user to the database
        var savedUser = findByEmail(user.getEmail());
        return !savedUser.isPresent()  ? userepo.save(user) : null;
    }

    public Optional<User> findByEmail(String email){
        //find user by email
        return userepo.findByEmail(email);
    }

    //return the user
    public User getUser(long id) {
        return (userepo.findById(id)).get();
    }
    

}
