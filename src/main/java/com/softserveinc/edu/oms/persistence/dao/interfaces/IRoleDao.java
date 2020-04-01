package com.softserveinc.edu.oms.persistence.dao.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.persistence.dao.IDao;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;

public interface IRoleDao extends IDao<Role> {

	/**
	 * 
	 * @return Role class with admin parameters inside :)
	 */
	Role getAdminRole();

	/**
	 * 
	 * @return Role class with customer parameters inside :)
	 */
	Role getCustomerRole();

	/**
	 * 
	 * @return Role class with merchandiser parameters inside :)
	 */
	Role getMerchandiserRole();

	/**
	 * 
	 * @return Role class with supervisor parameters inside :)
	 */
	Role getSupervisorRole();

	/**
	 * 
	 * @param roleName
	 *            - name of role to find
	 * @return list of roles with roleName equals to "roleName"
	 */
	List<Role> findByRoleName(final String roleName);

	/**
	 * with sorting capabilities.
	 * 
	 * @param roleName
	 *            - name of role to find
	 * @param sortProperties
	 *            - sorting options
	 * @return list of roles with roleName equals to "roleName"
	 */
	List<Role> findByRoleName(final String roleName,
			SortProperties sortProperties);

	/**
	 * 
	 * @param roleName
	 *            - role name to find or part of it
	 * @param condition
	 *            represent a condition for search
	 * @return list of roles
	 */
	List<Role> findByRoleName(final String roleName,
			final UserSelectWayCondition condition);

	/**
	 * 
	 * @param roleName
	 *            - role name to find or part of it
	 * @param condition
	 *            represent a condition for search
	 * @param sortProperties
	 *            - sorting options
	 * @return list of roles
	 */
	List<Role> findByRoleName(final String roleName,
			final UserSelectWayCondition condition,
			SortProperties sortProperties);

	/**
	 * 
	 * @param roleName
	 *            role name to check
	 * @return true if there is such a role in DB and false if not.
	 */
	boolean containsRole(String roleName);

}