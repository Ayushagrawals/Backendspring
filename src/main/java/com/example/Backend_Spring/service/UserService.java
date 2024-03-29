package com.example.Backend_Spring.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Backend_Spring.Exception.CustomException;
import com.example.Backend_Spring.Model.Users;
import com.example.Backend_Spring.Repository.UserInterface;
import com.example.Backend_Spring.Utility.StringUtil;

import java.util.List;

@Service
public class UserService {

    private final UserInterface userRepo;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserInterface userRepo) {
        this.userRepo = userRepo;
    }

    public Users saveUser(ConcurrentHashMap<String, Object> m) throws CustomException {
    	 Users user = new Users(); 
    	try {
       // Create a new instance for each save operation

        int age = (int) m.get("age");
        user.setAge(age);
        user.setName(m.get("name").toString());
        String encodedPwd=passwordEncoder.encode(m.get("password").toString());
        user.setPassword(encodedPwd);
        user.setRole(m.get("role").toString());
        user.setEmail(m.get("email").toString());
       user= userRepo.save(user);
       System.out.println("checking "+user.getEmail()+" "+user.getPassword());
    	}
    	catch(Exception e)
    	{
    		throw new CustomException();
    	}
		return user;
    }
    public List<Users> findUsers()
    {
    	return userRepo.findAll();
    }
    public void deleteUser(Users user)
    {
    	userRepo.delete(user);
    }
    public String getStringUtil()
    {
    	return StringUtil.getString();
    }
}
