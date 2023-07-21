package user;

import java.util.Optional;
import java.util.stream.Stream;

public class UserService {
    private static final UserService userService= new UserService();


    private final UserRepository userRepository= UserRepository.getInstance();


    public  boolean creat(User user){
        Optional<User> exitingUser = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (exitingUser.isPresent()){
            return false;
        }else {
            exitingUser.stream().filter(user1 -> user1.getPassword().equals(user.getPassword()));
            userRepository.add(user);
            return true;
        }
    }


    public static  UserService getInstance(){
        return userService;
    }


    public User signIn(String password, String phoneNumber) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUser.isEmpty()){
            return null;
        }else {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)){
                return user;
            }else {
                return null;
            }
        }

    }
}
