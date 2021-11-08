package com.aksoft.equities.repository;

import com.aksoft.equities.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByCellPhone(String cellphone);
}
