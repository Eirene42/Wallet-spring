package com.irene.Wallet.achievement;

import com.irene.Wallet.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
    List<Achievement> findAllByUser(User user);
}
