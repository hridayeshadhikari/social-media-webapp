package com.socialvista.service;

import com.socialvista.Exceptions.UserException;
import com.socialvista.model.Reel;
import com.socialvista.model.User;
import com.socialvista.repository.ReelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReelServiceImpl implements ReelService{

    private final ReelRepository reelRepository;
    private final UserService userService;
    @Override
    public Reel createReel(Reel reel, User user) {
        Reel createdReel=new Reel();
        createdReel.setCaption(reel.getCaption());
        createdReel.setVideo(reel.getVideo());
        createdReel.setUser(user);
        return reelRepository.save(createdReel);
    }

    @Override
    public List<Reel> findAllReel() {
        return reelRepository.findAll();
    }

    @Override
    public List<Reel> findUsersReel(Integer userId) throws Exception {
        userService.findUserById(userId);

        return reelRepository.findByUserId(userId);
    }

    @Override
    public Reel findById(Integer id) throws Exception {
        Optional<Reel> reel=reelRepository.findById(id);
        if(reel.isEmpty()){
            throw new Exception("no reel found with this id"+id);
        }
        return reel.get();
    }


    @Override
    public Reel likeTheReel(Integer reelId ,Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Reel reel=findById(reelId);
        if(reel.getLiked().contains(user)){
            reel.getLiked().remove(user);
        }
        else{
            reel.getLiked().add(user);
        }
        return reelRepository.save(reel);
    }

}
