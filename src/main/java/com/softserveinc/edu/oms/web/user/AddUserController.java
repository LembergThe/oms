package com.softserveinc.edu.oms.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.user.model.UserModel;

@Controller
public class AddUserController extends AbstractFormUserController {

	@Override
	@RequestMapping(value = "addUser.htm", method = RequestMethod.GET)
	public String prepareForm(final ModelMap modelMap,
			final HttpServletRequest request) {
		UserModel userModel = new UserModel();

		Integer id = roleService.getCustomerRole().getId();
		userModel.setRoleID(id.toString());

		modelMap.addAttribute("userModel", userModel);

		return "userForm";
	}

	@Override
	@RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
	public String onSubmit(final UserModel userModel, final BindingResult result) {
		addEditUserValidator.validate(userModel, result);

		if (result.hasErrors()) {
			return "userForm";
		}

		User user = createUser(userModel);

		userService.insertOrUpdate(user);

		return "redirect:users.htm";
	}
}
