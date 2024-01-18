package com.socialvista.controller;

import com.socialvista.model.Reel;
import com.socialvista.model.User;
import com.socialvista.service.ReelService;
import com.socialvista.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReelController {

    private final ReelService reelService;
    private final UserService userService;

    @PostMapping("/api/reel/create")
    public Reel createReel(@RequestBody Reel reel,
                           @RequestHeader ("Authorization")String jwt){
        User reqUser=userService.findUserByToken(jwt);
        Reel createReel=reelService.createReel(reel,reqUser);
        return createReel;
    }

    @GetMapping("/api/reels")
    public List<Reel> allReels(){
        List<Reel> createdReel=reelService.findAllReel();
        return createdReel;
    }

    @GetMapping("/api/reel/{userId}")
    public List<Reel> reelOfUser(@PathVariable Integer userId) throws Exception {
        List<Reel> createdReel=reelService.findUsersReel(userId);
        return createdReel;
    }
}
