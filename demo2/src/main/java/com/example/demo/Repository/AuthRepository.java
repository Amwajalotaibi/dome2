package com.example.demo.Repository;

import com.example.demo.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUserByUsername(String username);
}
