package com.socialvista.service;

import com.socialvista.model.Reel;
import com.socialvista.model.User;
import com.socialvista.repository.ReelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
