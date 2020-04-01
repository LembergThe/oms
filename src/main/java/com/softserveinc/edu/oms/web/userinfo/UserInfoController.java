package com.softserveinc.edu.oms.web.userinfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.orderitem.util.SessionExplorer;

@Controller
public class UserInfoController {

	private static final String USER = "user";

	@RequestMapping(value = "userInfo.htm")
	public String userInfo(final ModelMap map) {

		User user = SessionExplorer.getLoggedUser();

		map.put(USER, user);
		return "userinfo";
	}
}
