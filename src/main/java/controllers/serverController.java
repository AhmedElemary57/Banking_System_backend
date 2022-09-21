package controllers;

import Database.UserServices;
import models.Transaction;
import models.User;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import Database.TransactionServices;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
public class serverController {

    @PostMapping("/addTransaction")
    @ResponseBody
    public ResponseEntity<Boolean> addTransaction(@RequestBody Transaction transaction) {
        return TransactionServices.makeTransactionIfPossible(transaction);
    }

    @GetMapping("/getTransactions")
    public ResponseEntity<Transaction[]> getTransactions(@RequestParam String userEmail) {
        System.out.println("transactions of " + userEmail);
        return new ResponseEntity<>(TransactionServices.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<User[]> getAllUsers(@RequestParam String userEmail) {
        System.out.println("get all users  " + userEmail);
        return new ResponseEntity<>(UserServices.getAllUsers(), HttpStatus.OK);
    }


}
  