package com.example.Backend_Spring;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Backend_Spring.Exception.CustomException;
import com.example.Backend_Spring.Model.Users;
import com.example.Backend_Spring.Repository.UserInterface;
import com.example.Backend_Spring.Utility.StringUtil;
import com.example.Backend_Spring.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@PrepareForTest({StringUtil.class})
class BackendSpringApplicationTests {
	@MockBean
	private UserInterface userInterface;
	@Autowired
	private UserService userService;	
	@Test
	public void getUserTest()
	{
		when(userInterface.findAll()).thenReturn(Stream.of(new Users("Ayush",27)).collect(Collectors.toList()));
		assertEquals(1,userService.findUsers().size());
	}
	/*@Test
	public void saveUser() throws CustomException
	{
		Users user=new Users("Abcd",28);
		String name=user.getName();
		int age=user.getAge();
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("name", name);
		m.put("age", age);
		when(userInterface.save(user)).thenReturn(user);
		assertEquals(user,userService.saveUser((ConcurrentHashMap<String, Object>) m));
	}*/
	@Test
	public void deleteUserTest()
	{
		Users user=new Users("Abcd",28);
		userService.deleteUser(user);
		verify(userInterface,times(1)).delete(user);
	}
	
	/*@Test
	public void testString()
	{
		PowerMockito.mockStatic(StringUtil.class);
		PowerMockito.when(StringUtil.getString()).thenReturn("String");
		assertEquals(userService.getStringUtil(),"String");
	}*/

	@Test
	void contextLoads() {
	}

}
