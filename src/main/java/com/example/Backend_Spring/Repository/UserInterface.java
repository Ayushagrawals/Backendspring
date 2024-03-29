package com.example.Backend_Spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Backend_Spring.Model.Users;
@Repository
public interface UserInterface extends JpaRepository<Users,Integer>{
List<Users> findByEmail(String email);
}
