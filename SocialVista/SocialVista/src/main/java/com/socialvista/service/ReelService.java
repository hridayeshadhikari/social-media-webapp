package com.socialvista.service;

import com.socialvista.Exceptions.UserException;
import com.socialvista.model.Reel;
import com.socialvista.model.User;

import java.util.List;

public interface ReelService {

    public Reel createReel(Reel reel , User user);
    public List<Reel> findAllReel();
    public List<Reel> findUsersReel(Integer userId) throws Exception;

    Reel findById(Integer id) throws Exception;
    Reel likeTheReel(Integer reelId,Integer userId) throws Exception;

}
