package com.zz;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.zz.model.Deparment;
import com.zz.model.Role;
import com.zz.model.User;
import com.zz.repository.DepartmentRepository;
import com.zz.repository.RoleRepository;
import com.zz.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Before
	public void initData(){
		userRepository.deleteAll();
		roleRepository.deleteAll();
		departmentRepository.deleteAll();
		
		Deparment deparment = new Deparment();
		deparment.setName("开发部");
		departmentRepository.save(deparment);
		Assert.notNull(deparment.getId(),"deparment.id is not null");
		
		Role role = new Role();
		role.setName("admin");
		roleRepository.save(role);
		Assert.notNull(role.getId(), "role.id is not null");
		
		User user = new User();
		user.setName("admin");
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		user.setPassword(bpe.encode("admin123"));
		user.setCreateDate(new Date());
		user.setDeparment(deparment);
		user.setEmail("kknd9819@qq.com");
		user.setSex(1);
		userRepository.save(user);
		Assert.notNull(user.getId(), "user.id is not null");
	}
	

	@Test
	public void contextLoads() {
		User user = userRepository.findByName("admin");
		Assert.notNull(user,"user is not null");
		
		List<Role> roles = roleRepository.findAll();
		user.setRoles(roles);
		userRepository.save(user);
	}

}
