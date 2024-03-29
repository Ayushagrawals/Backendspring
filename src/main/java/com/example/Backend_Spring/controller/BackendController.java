package com.example.Backend_Spring.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend_Spring.Exception.CustomException;
import com.example.Backend_Spring.Model.Users;
import com.example.Backend_Spring.Repository.UserInterface;
import com.example.Backend_Spring.service.UserService;

@RestController
@RequestMapping("/users")
public class BackendController {
	
@Autowired	
UserService userService;
@org.springframework.web.bind.annotation.PostMapping("/saveUser")
public ResponseEntity<?> PostMapping(@RequestBody ConcurrentHashMap<String,Object> user)
	{
		try {
			Users users=userService.saveUser(user);
			if(users==null) {
			CustomException ce=new CustomException();
			//return new ResponseEntity<CustomException>(ce,"Error in saving into DB");
			throw new CustomException();
			
			}
			else {
				return new ResponseEntity<Users>(users,HttpStatus.OK);
			}
			//return new ResponseEntity<Users>(users,HttpStatus.OK);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Exception>(e,HttpStatus.INTERNAL_SERVER_ERROR);		}
	}
@org.springframework.web.bind.annotation.GetMapping("/getUser")
public ResponseEntity<?> GetMapping()
{
	List<Users> users=userService.findUsers();
	return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
}
}
