package com.irene.Wallet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("users")
public class UserController {

    String email = "irene.maniou@hotmail.com";
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @GetMapping("/user")
    public User findUser() {
        return this.userRepository.findByEmail(email);
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userToUpdate = this.userRepository.findByEmail(email);

        if (userToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found to update");
        }

        userToUpdate.setAge(user.getAge());
        userToUpdate.setJob(user.getJob());

        Date this_date = new Date();
        user.setUpdatedAt(this_date);

        return new ResponseEntity<>(this.userRepository.save(userToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User userToDelete = this.userRepository.findByEmail(email);

        if (userToDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found to delete");
        }
        this.userRepository.delete(userToDelete);

        return ResponseEntity.ok(userToDelete);
    }
}
