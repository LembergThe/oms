package com.softserveinc.edu.oms.persistence.dao.concrete;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.persistence.dao.HibernatePageableDao;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IUserDao;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;

@Repository
public class UserDao extends HibernatePageableDao<User> implements IUserDao {

	private static final Criterion IF_IS_ACTIVE = Restrictions.eq("active",
			true);

	public UserDao() {
		super(User.class);
	}

	@Override
	public List<User> findAll(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties) {
		return findByCriterions(startingFrom, maxResult, sortProperties,
				IF_IS_ACTIVE);
	}

	@Override
	public Long getRowCount() {
		return getRowCountByCriterions(IF_IS_ACTIVE);
	}

	/**
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IUserDao#
	 *      findUsersBySearchValue(java.lang.String,
	 *      com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField,
	 *      com.softserveinc.edu.oms.persistence.dao.params.UserSelectWayCondition,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<User> findUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition, final Integer startingFrom,
			final Integer maxResult) {

		return findUsersBySearchValue(searchValue, selectField, condition,
				startingFrom, maxResult, new SortProperties());
	}

	/**
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IUserDao#
	 *      findUsersBySearchValue(java.lang.String,
	 *      com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField,
	 *      com.softserveinc.edu.oms.persistence.dao.params.UserSelectWayCondition,
	 *      java.lang.Integer, java.lang.Integer,
	 *      com.softserveinc.edu.oms.persistence.dao.params.SortProperties)
	 */
	@Override
	public List<User> findUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition, final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties) {

		switch (selectField) {

		case FIRST_NAME:
			return findByCriterions(startingFrom, maxResult, sortProperties,
					condition.createStringCriterion("firstName", searchValue),
					IF_IS_ACTIVE);

		case LAST_NAME:
			return findByCriterions(startingFrom, maxResult, sortProperties,
					condition.createStringCriterion("lastName", searchValue),
					IF_IS_ACTIVE);

		case LOGIN:
			return findByCriterions(startingFrom, maxResult, sortProperties,
					condition.createStringCriterion("login", searchValue),
					IF_IS_ACTIVE);

		case ROLE:
			Criterion roleCriterion = createRoleCriterion(searchValue,
					condition);
			if (roleCriterion == null) {
				return new LinkedList<User>();
			}
			return findByCriterions(startingFrom, maxResult, sortProperties,
					roleCriterion, IF_IS_ACTIVE);

		default:
			return findUserByValue(searchValue, condition, startingFrom,
					maxResult, sortProperties);
		}
	}

	/**
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IUserDao#
	 *      countUsersBySearchValue(java.lang.String,
	 *      com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField,
	 *      com.softserveinc.edu.oms.persistence.dao.params.UserSelectWayCondition)
	 */
	@Override
	public Long countUsersBySearchValue(final String searchValue,
			final UserSelectField selectField,
			final UserSelectWayCondition condition) {
		switch (selectField) {

		case FIRST_NAME:
			return getRowCountByCriterions(
					condition.createStringCriterion("firstName", searchValue),
					IF_IS_ACTIVE);

		case LAST_NAME:
			return getRowCountByCriterions(
					condition.createStringCriterion("lastName", searchValue),
					IF_IS_ACTIVE);

		case LOGIN:
			return getRowCountByCriterions(
					condition.createStringCriterion("login", searchValue),
					IF_IS_ACTIVE);

		case ROLE:
			Criterion roleCriterion = createRoleCriterion(searchValue,
					condition);
			if (roleCriterion == null) {
				return new Long(0);
			}
			return getRowCountByCriterions(roleCriterion, IF_IS_ACTIVE);

		default:
			return countUserByValue(searchValue, condition);
		}
	}

	private List<User> findUserByValue(final String value,
			final UserSelectWayCondition condition, final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties) {

		Criterion[] criterions = createFindByValueCriterions(value, condition);

		Disjunction disjunction = Restrictions.disjunction();

		for (Criterion criterion : criterions) {
			if (criterion != null) {
				disjunction.add(criterion);
			}
		}

		List<User> users = new LinkedList<User>();

		if (condition == UserSelectWayCondition.NOT_EQUALS_TO
				|| condition == UserSelectWayCondition.DOES_NOT_CONTAIN) {
			Criterion[] all = new Criterion[criterions.length + 1];

			for (int i = 0; i < criterions.length; i++) {
				all[i] = criterions[i];
			}

			all[criterions.length] = IF_IS_ACTIVE;

			users.addAll(findByCriterions(startingFrom, maxResult,
					sortProperties, all));
		} else {
			users.addAll(findByCriterions(startingFrom, maxResult,
					sortProperties, disjunction, IF_IS_ACTIVE));
		}

		return users;
	}

	private Long countUserByValue(final String value,
			final UserSelectWayCondition condition) {

		Criterion[] criterions = createFindByValueCriterions(value, condition);

		Disjunction disjunction = Restrictions.disjunction();

		for (Criterion criterion : criterions) {
			if (criterion != null) {
				disjunction.add(criterion);
			}
		}

		final Long count;

		if (condition == UserSelectWayCondition.NOT_EQUALS_TO
				|| condition == UserSelectWayCondition.DOES_NOT_CONTAIN) {
			Criterion[] all = new Criterion[criterions.length + 1];

			for (int i = 0; i < criterions.length; i++) {
				all[i] = criterions[i];
			}

			all[criterions.length] = IF_IS_ACTIVE;

			count = getRowCountByCriterions(all);
		} else {
			count = getRowCountByCriterions(disjunction, IF_IS_ACTIVE);
		}

		return count;
	}

	private Criterion[] createFindByValueCriterions(final String value,
			final UserSelectWayCondition condition) {
		Criterion roleCriterion = createRoleCriterion(value, condition);
		Criterion firstName = condition.createStringCriterion("firstName",
				value);
		Criterion lastName = condition.createStringCriterion("lastName", value);
		Criterion login = condition.createStringCriterion("login", value);

		Criterion[] criterions = new Criterion[] { roleCriterion, firstName,
				lastName, login };
		return criterions;
	}

	private Criterion createRoleCriterion(final String value,
			final UserSelectWayCondition condition) {
		RoleDao roleDao = new RoleDao();
		roleDao.setSessionFactory(sessionFactory);

		List<Role> roles = roleDao.findByRoleName(value, condition);

		if (roles.isEmpty()) {
			return null;
		}

		Disjunction disjunction = Restrictions.disjunction();

		for (Role role : roles) {
			disjunction.add(Restrictions.eq("role", role));
		}

		return disjunction;
	}

	@Override
	public User findByLogin(final String login) {
		Criterion criterion = Restrictions.eq("login", login);

		List<User> users = findByCriterions(criterion);

		if (users.isEmpty()) {
			return null;
		} else {
			for (User user : users) {
				if (user.isActive()) {
					return user;
				}
			}
			return null;
		}
	}
}
