package com.aksoft.equities.controller;

import com.aksoft.equities.entity.User;
import com.aksoft.equities.service.UserService;
import com.aksoft.equities.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return service.saveUsers(users);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @GetMapping("/userByCellphone/{cellPhone}")
    public User findUserByCellPhone(@PathVariable String cellPhone) {
        return service.getUserByCellphone(cellPhone);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                service.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        } else {
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }
}
