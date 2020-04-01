package com.softserveinc.edu.oms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IRoleDao;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;
import com.softserveinc.edu.oms.service.interfaces.IRoleService;

@Service
public class RoleService implements IRoleService {
	private IRoleDao roleDao;

	@Autowired
	public void setRoleDao(final IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Transactional
	@Override
	public Long getRowCount() {
		return roleDao.getRowCount();
	}

	@Transactional
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Transactional
	@Override
	public List<Role> findAll(final SortProperties sortProperties) {
		return roleDao.findAll(sortProperties);
	}

	@Transactional
	@Override
	public Role findByID(final Integer id) {
		return roleDao.findByID(id);
	}

	@Transactional
	@Override
	public Role insertOrUpdate(final Role entity) {
		return roleDao.insertOrUpdate(entity);
	}

	@Transactional
	@Override
	public void delete(final Role entity) {
		roleDao.delete(entity);
	}

	@Transactional
	@Override
	public Role getAdminRole() {
		return roleDao.getAdminRole();
	}

	@Transactional
	@Override
	public Role getCustomerRole() {
		return roleDao.getCustomerRole();
	}

	@Transactional
	@Override
	public Role getMerchandiserRole() {
		return roleDao.getMerchandiserRole();
	}

	@Transactional
	@Override
	public Role getSupervisorRole() {
		return roleDao.getSupervisorRole();
	}

	@Transactional
	@Override
	public List<Role> findByRoleName(final String roleName) {
		return roleDao.findByRoleName(roleName);
	}

	@Transactional
	@Override
	public List<Role> findByRoleName(final String roleName,
			final SortProperties sortProperties) {
		return roleDao.findByRoleName(roleName, sortProperties);
	}

	@Transactional
	@Override
	public List<Role> findByRoleName(final String roleName,
			final UserSelectWayCondition condition) {
		return roleDao.findByRoleName(roleName, condition);
	}

	@Transactional
	@Override
	public List<Role> findByRoleName(final String roleName,
			final UserSelectWayCondition condition,
			final SortProperties sortProperties) {
		return roleDao.findByRoleName(roleName, condition, sortProperties);
	}

	@Transactional
	@Override
	public boolean containsRole(final String roleName) {
		return roleDao.containsRole(roleName);
	}

}
