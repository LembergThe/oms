package com.softserveinc.edu.oms.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.web.user.model.UserModel;

@Controller
@RequestMapping("editUser.htm")
public class EditUserController extends AbstractFormUserController {

	private static final String USER_ID = "userID";

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String prepareForm(final ModelMap modelMap,
			final HttpServletRequest request) {
		String param = request.getParameter(USER_ID);
		Integer id;

		if (param == null) {
			id = (Integer) request.getSession(true).getAttribute(USER_ID);
		} else {
			id = Integer.parseInt(param);
			request.getSession(true).setAttribute(USER_ID, id);
		}

		User user = userService.findByID(id);

		UserModel userModel = createUserModel(user);

		modelMap.addAttribute("userModel", userModel);

		return "userForm";
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("userModel") final UserModel userModel,
			final BindingResult result) {
		addEditUserValidator.validate(userModel, result);

		if (result.hasErrors()) {
			return "userForm";
		}

		User user = createUser(userModel);
		user.setId(Integer.parseInt(userModel.getId()));

		userService.insertOrUpdate(user);

		return "redirect:users.htm";
	}

	private UserModel createUserModel(final User user) {
		UserModel userModel = new UserModel();

		userModel.setId(user.getId().toString());
		userModel.setLogin(user.getLogin());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setEmail(user.getEmail());
		userModel.setPassword(user.getPassword());
		userModel.setConfirmPassword(user.getPassword());
		userModel.setRegionID(user.getRegion().getId().toString());
		userModel.setRoleID(user.getRole().getId().toString());

		return userModel;
	}
}
