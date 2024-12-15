package product.mangagement.productm.service.user;
import  product.mangagement.productm.models.users.UserPrincipal;
import product.mangagement.productm.models.users.UserVerification;
import product.mangagement.productm.repository.product.VerificationRepository;
import product.mangagement.productm.repository.user.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import  product.mangagement.productm.models.users.User;

import org.springframework.stereotype.Service;

@Service
@Primary
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userepo;
    @Autowired
    VerificationRepository userVerification;

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

    public void addVerificationEntry(UserVerification vt) {
        userVerification.save(vt);
    }
    
    public void deleteVerification(UserVerification vt) {
        userVerification.delete(vt);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userepo.findByEmail(username);
        System.out.println(user.get().getHashPassword());
        if(user.isPresent())
            return new UserPrincipal(user.get());
        throw new UsernameNotFoundException("user not found");
    }

    public Optional<UserVerification> getUserVerification(String vId) {
        return userVerification.findByVerificationCode(vId);
    }
}
