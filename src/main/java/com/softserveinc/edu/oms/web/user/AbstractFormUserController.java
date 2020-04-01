package com.softserveinc.edu.oms.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.softserveinc.edu.oms.domain.entities.Region;
import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.service.interfaces.IRegionService;
import com.softserveinc.edu.oms.service.interfaces.IRoleService;
import com.softserveinc.edu.oms.service.interfaces.IUserService;
import com.softserveinc.edu.oms.web.user.model.UserModel;

public abstract class AbstractFormUserController {
	protected IUserService userService;
	protected IRoleService roleService;
	protected IRegionService regionService;

	protected AddEditUserValidator addEditUserValidator;

	public abstract String prepareForm(final ModelMap modelMap,
			final HttpServletRequest request);

	public abstract String onSubmit(
			@ModelAttribute("userModel") final UserModel userModel,
			final BindingResult result);

	@ModelAttribute("roles")
	public List<Role> populateRoles() {
		return roleService.findAll();
	}

	@ModelAttribute("regions")
	public List<Region> populateRegions() {
		return regionService.findAll();
	}

	protected User createUser(final UserModel userModel) {
		User user = new User();

		user.setLogin(userModel.getLogin());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setPassword(userModel.getPassword());
		user.setEmail(userModel.getEmail());

		Integer roleID = Integer.parseInt(userModel.getRoleID());
		user.setRole(roleService.findByID(roleID));

		Integer regionID = Integer.parseInt(userModel.getRegionID());
		user.setRegion(regionService.findByID(regionID));

		user.setCustomerType(null);
		user.setBalance(0.0);
		return user;
	}

	@Autowired
	public void setAddEditUserValidator(
			final AddEditUserValidator addEditUserValidator) {
		this.addEditUserValidator = addEditUserValidator;
	}

	@Autowired
	public void setUserService(final IUserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(final IRoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setRegionService(final IRegionService regionService) {
		this.regionService = regionService;
	}
}
