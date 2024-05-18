package com.socialvista.service;
import com.socialvista.Exceptions.UserException;
import com.socialvista.model.User;
import com.socialvista.repository.UserRepository;
import com.socialvista.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        User createUser=new User();
        createUser.setEmail(user.getEmail());
        createUser.setPassword(user.getPassword());
        createUser.setFirstName(user.getFirstName());
        createUser.setLastName(user.getLastName());
        createUser.setId(user.getId());

        User saveUser=userRepository.save(createUser);
        return saveUser;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) throws UserException {
        Optional<User> theUser=userRepository.findById(id);
        if(theUser.isPresent()){
            return theUser.get();
        }else {
            throw new UserException("user not found with id "+id);
        }

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser=findUserById(reqUserId);
        User user2=findUserById(userId2);
        if(reqUser!=user2){
            System.out.println("======="+user2.getFollowers().contains(reqUserId));
            //already followed the user2
            if(user2.getFollowers().contains(reqUserId)){
                user2.getFollowers().remove(reqUserId);
                reqUser.getFollowing().remove(userId2);
                userRepository.save(reqUser);
                userRepository.save(user2);

            }else {
                reqUser.getFollowing().add(userId2);
                user2.getFollowers().add(reqUserId);
            userRepository.save(reqUser);
            userRepository.save(user2);}
        }else
        {
            throw new UserException("cant follow to self!");
        }
        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer id) throws UserException {
        Optional<User> user1=userRepository.findById(id);
        if(user1.isEmpty()){
            throw new UserException("user not found with this id"+id);
        }
        User updatedUser=user1.get();
        if(user.getFirstName()!=null){
            updatedUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            updatedUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null){
            updatedUser.setEmail(user.getEmail());
        }
        if (user.getGender()!=null){
            updatedUser.setGender(user.getGender());
        }
        User updateUser=userRepository.save(updatedUser);
        return updateUser;
    }





    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByToken(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user=userRepository.findByEmail(email);
        return user;
    }
}
