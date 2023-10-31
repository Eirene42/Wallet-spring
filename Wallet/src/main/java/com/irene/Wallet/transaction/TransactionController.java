package com.irene.Wallet.transaction;

import com.irene.Wallet.user.User;
import com.irene.Wallet.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public List<Transaction> getAllTransactionsByUser() {
        User userFound = this.userRepository.findByEmail("irene.maniou@hotmail.com");

        if (userFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        return this.transactionRepository.findByUser(userFound);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        User userFound = this.userRepository.findByEmail("irene.maniou@hotmail.com");

        if (userFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        } else {
            Date this_date = new Date();
            transaction.setCreatedAt(this_date);
            transaction.setUpdatedAt(this_date);
            transaction.setUser(userFound);
        }
        return new ResponseEntity<>(this.transactionRepository.save(transaction), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable int id) {
        Transaction transactionToDelete = this.transactionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found to delete"));

        this.transactionRepository.delete(transactionToDelete);

        return ResponseEntity.ok(transactionToDelete);
    }
}
