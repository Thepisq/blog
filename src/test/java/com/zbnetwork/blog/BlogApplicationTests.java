package com.zbnetwork.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void contextLoads() {
		//打印BCrypt加密后的值
		String pass = "admin";
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		String hashPass = bcryptPasswordEncoder.encode(pass);
		System.out.println(hashPass);
	}

}
