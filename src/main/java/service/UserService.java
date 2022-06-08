package service;

import entity.User;
import model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registration(UserModel userModel);
    void saveVerificationTokenForUser(String token,User user);
}
