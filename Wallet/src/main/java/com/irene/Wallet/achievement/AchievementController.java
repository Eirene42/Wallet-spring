package com.irene.Wallet.achievement;

import com.irene.Wallet.user.User;
import com.irene.Wallet.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/achievements")
public class AchievementController {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Achievement> getAchievementsByUser() {
        User user = this.userRepository.findByEmail("irene.maniou@hotmail.com");

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        return this.achievementRepository.findAllByUser(user);
    }
}
