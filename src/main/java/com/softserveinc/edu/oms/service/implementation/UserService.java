package com.softserveinc.edu.oms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IRoleDao;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IUserDao;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;
import com.softserveinc.edu.oms.service.interfaces.IUserService;
import com.softserveinc.edu.oms.web.security.UserSecurityData;

@Service
public class UserService implements IUserService, UserDetailsService {
	private IUserDao userDao;
	private IRoleDao roleDao;

	@Autowired
	public void setRoleDao(final IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setUserDao(final IUserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	@Override
	public List<User> findAll(final Integer startingFrom,
			final Integer maxResult) {
		return userDao.findAll(startingFrom, maxResult);
	}

	@Transactional
	@Override
	public List<User> findAll(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties) {
		return userDao.findAll(startingFrom, maxResult, sortProperties);
	}

	@Transactional
	@Override
	public Long getRowCount() {
		return userDao.getRowCount();
	}

	@Transactional
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Transactional
	@Override
	public List<User> findAll(final SortProperties sortProperties) {
		return userDao.findAll(sortProperties);
	}

	@Transactional
	@Override
	public User findByID(final Integer id) {
		return userDao.findByID(id);
	}

	@Transactional
	@Override
	public void delete(final User entity) {
		entity.setActive(false);

		userDao.insertOrUpdate(entity);
	}

	@Transactional
	@Override
	public User insertOrUpdate(final User entity) {
		return userDao.insertOrUpdate(entity);
	}

	@Transactional
	@Override
	public List<User> findUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition, final Integer startingFrom,
			final Integer maxResult) {
		return userDao.findUsersBySearchValue(searchValue, selectField,
				condition, startingFrom, maxResult);
	}

	@Transactional
	@Override
	public List<User> findUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition, final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties) {
		return userDao.findUsersBySearchValue(searchValue, selectField,
				condition, startingFrom, maxResult, sortProperties);
	}

	@Transactional
	@Override
	public Long countUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition) {
		return userDao.countUsersBySearchValue(searchValue, selectField,
				condition);
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(final String login)
			throws UsernameNotFoundException, DataAccessException {
		final User user = userDao.findByLogin(login);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return new UserSecurityData(user);
	}

	@Transactional
	@Override
	public User findByLogin(final String login) {
		return userDao.findByLogin(login);
	}

	/**
	 * @see com.softserveinc.edu.oms.service.interfaces.IUserService#findMerchandiserUsers()
	 * 
	 * @author Ivanka
	 */
	@Transactional
	@Override
	public List<User> findMerchandiserUsers() {
		Role merchandiserRole = roleDao.getMerchandiserRole();
		Long maxResult = countUsersBySearchValue(
				merchandiserRole.getRoleName(), UserSelectField.ROLE,
				UserSelectWayCondition.EQUALS);

		return userDao.findUsersBySearchValue(merchandiserRole.getRoleName(),
				UserSelectField.ROLE, UserSelectWayCondition.EQUALS, 0,
				maxResult.intValue());
	}
}