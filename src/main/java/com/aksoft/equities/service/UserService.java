package com.aksoft.equities.service;

import com.aksoft.equities.entity.User;
import com.aksoft.equities.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return repository.saveAll(users);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public User getUserByCellphone(String cellphone) {
        return repository.findByCellPhone(cellphone);
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "User Deleted Successfully with Id: " + id;
    }

    public User updateUser(User user) {
        User oldUser = getUserById(user.getUserId());
        oldUser.setAddress(user.getAddress());
        oldUser.setCellPhone(user.getCellPhone());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmailId(user.getEmailId());
        oldUser.setCity(user.getCity());
        return saveUser(oldUser);
    }

}