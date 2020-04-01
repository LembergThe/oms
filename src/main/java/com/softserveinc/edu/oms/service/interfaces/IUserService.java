package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;

public interface IUserService extends ServiceForPaging<User> {
	List<User> findUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition, final Integer startingFrom,
			final Integer maxResult);

	List<User> findUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition, final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties);

	Long countUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition);

	User findByLogin(final String login);

	/**
	 * return list of users who has merchandiser role
	 * 
	 * @return list of users
	 * @author Ivanka
	 */
	List<User> findMerchandiserUsers();
}
