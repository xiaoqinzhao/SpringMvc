package com.nowcoder;

import com.nowcoder.model.LoginTicket;
import com.nowcoder.service.LikeService;
import com.nowcoder.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sun.security.krb5.internal.Ticket;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WendaApplication.class)
@WebAppConfiguration
public class WendaApplicationTests {

	@Autowired
	UserService userService;
	@Test
	public void contextLoads() {
	}

	@Test
	public void UserServiceTest(){

		Assert.assertEquals(userService.getUser(2).getName(),userService.getUser(2).getName());
	}
}
