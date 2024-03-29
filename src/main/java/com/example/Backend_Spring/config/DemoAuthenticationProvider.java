package com.example.Backend_Spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Backend_Spring.Model.Users;
import com.example.Backend_Spring.Repository.UserInterface;


@Component
public class DemoAuthenticationProvider implements AuthenticationProvider{
	
	UserInterface userRepo;
	
	Users users=new Users();
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
    public DemoAuthenticationProvider(UserInterface userRepo) {
        this.userRepo = userRepo;
    }


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String name=authentication.getName();
		String pwd=authentication.getCredentials().toString();
		List<Users> users=(List<Users>) userRepo.findByEmail(name);
        List<GrantedAuthority> lauthority=new ArrayList<GrantedAuthority>();

		if(users.size()>0)
		{
			if(passwordEncoder.matches(pwd, users.get(0).getPassword()))
			{
		        lauthority=new ArrayList<GrantedAuthority>();
		        lauthority.add(new SimpleGrantedAuthority(users.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(name,pwd,lauthority);
				

			}
			else {
				throw new BadCredentialsException("Password galat hai");
			}
		}
		else {
			throw new BadCredentialsException("User hai hi nahi register karo");

		}
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
