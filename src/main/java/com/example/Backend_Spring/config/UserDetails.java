package com.example.Backend_Spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Backend_Spring.Model.Users;
import com.example.Backend_Spring.Repository.UserInterface;
//@Service
public class UserDetails  {//implements UserDetailsService{

	/*@Autowired
	UserInterface userInterface;
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email)
			 {
		// TODO Auto-generated method stub
        Users user = userInterface.findByEmail(email);
        System.out.println("Email is "+user.getEmail());
        List<GrantedAuthority> lauthority;
        lauthority=new ArrayList<GrantedAuthority>();
        lauthority.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getName(),user.getPassword(),lauthority);
	}*/

}
