package com.softserveinc.edu.oms.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserveinc.edu.oms.service.interfaces.IUserService;
import com.softserveinc.edu.oms.web.user.util.UserAvailability;
import com.softserveinc.edu.oms.web.user.util.ValidatorRegex;
import com.softserveinc.edu.oms.web.util.StringWrapper;

@Controller
public class AjaxValidatorController {
	protected IUserService userService;

	@RequestMapping(value = "availability.htm")
	public @ResponseBody
	UserAvailability getAvailability(final String name) {
		UserAvailability availability = new UserAvailability();
		if (userService.findByLogin(name) == null) {
			availability.setAvailable(true);
		} else {
			availability.setAvailable(false);
		}

		return availability;
	}

	@RequestMapping(value = "emailRegex.htm")
	public @ResponseBody
	StringWrapper getEmailRegex() {
		StringWrapper wrapper = new StringWrapper();

		wrapper.setString(ValidatorRegex.EMAIL_REGEX);

		return wrapper;
	}

	@RequestMapping(value = "noDigitsRegex.htm")
	public @ResponseBody
	StringWrapper getNoDigitsRegex() {
		StringWrapper wrapper = new StringWrapper();

		wrapper.setString(ValidatorRegex.NO_DIGITS_REGEX);

		return wrapper;
	}

	@Autowired
	public void setUserService(final IUserService userService) {
		this.userService = userService;
	}
}
