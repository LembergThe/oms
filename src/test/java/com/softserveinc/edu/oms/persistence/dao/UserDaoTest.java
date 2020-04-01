package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.domain.entities.User;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectField;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;

public class UserDaoTest extends CleanUpDBTestCase {
	private final static Integer USERS_COUNT = 3;

	private User user1 = new User();
	private User user2 = new User();
	private User user3 = new User();

	private Role role1;
	private Role role2;

	@Before
	public void setUp() {
		createUsers();
	}

	@After
	public void tearDown() {
		cleanDB();
	}

	@Test
	public void testGetRowCount() {
		Long l = (long) 0;
		assertEquals(l, userDao.getRowCount());
	}

	@Test
	public void testFindAllFromEmptyDb() {
		assertEquals(0, userDao.findAll().size());
	}

	@Test
	public void testFindAllFromEmptyDbVol2() {
		assertEquals(0, userDao.findAll(0, USERS_COUNT).size());
	}

	@Test
	public void testInsertIntoEmptyDbAndFindAllAfterThat() {
		assertEquals(0, userDao.findAll().size());

		assertNull(user1.getId());
		assertNull(user2.getId());
		assertNull(user3.getId());

		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		assertNotNull(user1.getId());
		assertNotNull(user2.getId());
		assertNotNull(user3.getId());

		assertEquals(3, userDao.findAll().size());
	}

	@Test
	public void testFindAllFromWithPaging() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		int users = 2;
		assertEquals(users, userDao.findAll(0, users).size());
	}

	@Test
	public void testUpdateUser() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		Role role = user2.getRole();
		user1.setRole(role);
		User test1 = userDao.insertOrUpdate(user1);

		String newFirstName = "newFirstNameThatIsNotInDB";
		user2.setFirstName(newFirstName);
		User test2 = userDao.insertOrUpdate(user2);

		assertEquals(role.getRoleName(), test1.getRole().getRoleName());
		assertEquals(newFirstName, test2.getFirstName());
		assertEquals(3, userDao.findAll().size());
	}

	@Test
	public void testDeleteUser() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> users = userDao.findAll();
		assertTrue(users.contains(user1));
		assertEquals(3, users.size());

		userDao.delete(user1);

		users = userDao.findAll();
		assertFalse(users.contains(user1));
		assertEquals(2, users.size());
	}

	@Test
	public void testFindById() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		Integer id1 = user1.getId();
		assertEquals(user1, userDao.findByID(id1));
		Integer id2 = user2.getId();
		assertEquals(user2, userDao.findByID(id2));
		Integer id3 = user3.getId();
		assertEquals(user3, userDao.findByID(id3));

		assertNull(userDao.findByID(null));
		assertNull(userDao.findByID(id1 + id2 + id3));
	}

	@Test
	public void testFindByRoleNameEqualName() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue(
				role1.getRoleName(), UserSelectField.ROLE,
				UserSelectWayCondition.EQUALS, 0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue(
				role2.getRoleName(), UserSelectField.ROLE,
				UserSelectWayCondition.EQUALS, 0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.ROLE, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByRoleNameNotEqualName() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue(
				role1.getRoleName(), UserSelectField.ROLE,
				UserSelectWayCondition.NOT_EQUALS_TO, 0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue(
				role2.getRoleName(), UserSelectField.ROLE,
				UserSelectWayCondition.NOT_EQUALS_TO, 0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.ROLE, UserSelectWayCondition.NOT_EQUALS_TO, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(2, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByRoleNameStartsWith() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("super",
				UserSelectField.ROLE, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("adm",
				UserSelectField.ROLE, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.ROLE, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(2, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByRoleNameDoesNotContain() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("uper",
				UserSelectField.ROLE, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("pra",
				UserSelectField.ROLE, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("or",
				UserSelectField.ROLE, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(3, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByRoleNameContains() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("uper",
				UserSelectField.ROLE, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("pra",
				UserSelectField.ROLE, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("or",
				UserSelectField.ROLE, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(0, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByLoginEqual() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("ork",
				UserSelectField.LOGIN, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("rudenjke",
				UserSelectField.LOGIN, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LOGIN, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByLoginNotEqual() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("ork",
				UserSelectField.LOGIN, UserSelectWayCondition.NOT_EQUALS_TO, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("rudenjke",
				UserSelectField.LOGIN, UserSelectWayCondition.NOT_EQUALS_TO, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LOGIN, UserSelectWayCondition.NOT_EQUALS_TO, 0,
				USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(2, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByLoginStartsWith() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("or",
				UserSelectField.LOGIN, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("rude",
				UserSelectField.LOGIN, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LOGIN, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByLoginContains() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("rk",
				UserSelectField.LOGIN, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("ude",
				UserSelectField.LOGIN, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LOGIN, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByLoginDoesNotContain() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("rk",
				UserSelectField.LOGIN, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("ude",
				UserSelectField.LOGIN, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LOGIN, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(2, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByFirstNameEqual() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Orest",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("Ivanka",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByFirstNameNotEqual() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Orest",
				UserSelectField.FIRST_NAME,
				UserSelectWayCondition.NOT_EQUALS_TO, 0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("Ivanka",
				UserSelectField.FIRST_NAME,
				UserSelectWayCondition.NOT_EQUALS_TO, 0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.FIRST_NAME,
				UserSelectWayCondition.NOT_EQUALS_TO, 0, USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(2, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByFirstNameStartsWith() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Ore",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.STARTS_WITH,
				0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("Iva",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.STARTS_WITH,
				0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.STARTS_WITH,
				0, USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByFirstNameContains() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("res",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("van",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByFirstNameDoesNotContain() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("res",
				UserSelectField.FIRST_NAME,
				UserSelectWayCondition.DOES_NOT_CONTAIN, 0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("van",
				UserSelectField.FIRST_NAME,
				UserSelectWayCondition.DOES_NOT_CONTAIN, 0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.FIRST_NAME,
				UserSelectWayCondition.DOES_NOT_CONTAIN, 0, USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(2, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByLastNameEqual() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Vovchack",
				UserSelectField.LAST_NAME, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("Horoshko",
				UserSelectField.LAST_NAME, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LAST_NAME, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByLastNameNotEqual() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Vovchack",
				UserSelectField.LAST_NAME,
				UserSelectWayCondition.NOT_EQUALS_TO, 0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("Horoshko",
				UserSelectField.LAST_NAME,
				UserSelectWayCondition.NOT_EQUALS_TO, 0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LAST_NAME,
				UserSelectWayCondition.NOT_EQUALS_TO, 0, USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(2, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByLastNameStartsWith() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Vovc",
				UserSelectField.LAST_NAME, UserSelectWayCondition.STARTS_WITH,
				0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("Horo",
				UserSelectField.LAST_NAME, UserSelectWayCondition.STARTS_WITH,
				0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LAST_NAME, UserSelectWayCondition.STARTS_WITH,
				0, USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByLastNameContains() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("ovc",
				UserSelectField.LAST_NAME, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("oro",
				UserSelectField.LAST_NAME, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LAST_NAME, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByLastNameDoesNotContain() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("ovc",
				UserSelectField.LAST_NAME,
				UserSelectWayCondition.DOES_NOT_CONTAIN, 0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("oro",
				UserSelectField.LAST_NAME,
				UserSelectWayCondition.DOES_NOT_CONTAIN, 0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.LAST_NAME,
				UserSelectWayCondition.DOES_NOT_CONTAIN, 0, USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(2, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByAllEqual() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Orest",
				UserSelectField.ALL, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("Supervisor",
				UserSelectField.ALL, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.ALL, UserSelectWayCondition.EQUALS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(1, result2.size());
		assertEquals(0, result3.size());
	}

	@Test
	public void testFindByAllNotEqual() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Orest",
				UserSelectField.ALL, UserSelectWayCondition.NOT_EQUALS_TO, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("Supervisor",
				UserSelectField.ALL, UserSelectWayCondition.NOT_EQUALS_TO, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.ALL, UserSelectWayCondition.NOT_EQUALS_TO, 0,
				USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(2, result2.size());
		assertEquals(3, result3.size());
	}

	@Test
	public void testFindByAllStartsWith() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("Ore",
				UserSelectField.ALL, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("v",
				UserSelectField.ALL, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("adm",
				UserSelectField.ALL, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);
		List<User> result4 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.ALL, UserSelectWayCondition.STARTS_WITH, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(2, result2.size());
		assertEquals(2, result3.size());
		assertEquals(0, result4.size());
	}

	@Test
	public void testFindByAllContains() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("res",
				UserSelectField.ALL, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("v",
				UserSelectField.ALL, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("dmin",
				UserSelectField.ALL, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);
		List<User> result4 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.ALL, UserSelectWayCondition.CONTAINS, 0,
				USERS_COUNT);

		assertEquals(1, result1.size());
		assertEquals(3, result2.size());
		assertEquals(2, result3.size());
		assertEquals(0, result4.size());
	}

	@Test
	public void testFindByAllDoesNotContain() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		List<User> result1 = userDao.findUsersBySearchValue("res",
				UserSelectField.ALL, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);
		List<User> result2 = userDao.findUsersBySearchValue("v",
				UserSelectField.ALL, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);
		List<User> result3 = userDao.findUsersBySearchValue("dmin",
				UserSelectField.ALL, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);
		List<User> result4 = userDao.findUsersBySearchValue("not existing",
				UserSelectField.ALL, UserSelectWayCondition.DOES_NOT_CONTAIN,
				0, USERS_COUNT);

		assertEquals(2, result1.size());
		assertEquals(0, result2.size());
		assertEquals(1, result3.size());
		assertEquals(3, result4.size());
	}

	@Test
	public void testCountUserBySearchValue() {
		user1 = userDao.insertOrUpdate(user1);
		user2 = userDao.insertOrUpdate(user2);
		user3 = userDao.insertOrUpdate(user3);

		Long l1 = userDao.countUsersBySearchValue("Orest",
				UserSelectField.FIRST_NAME, UserSelectWayCondition.EQUALS);
		Long l2 = userDao.countUsersBySearchValue("Vovchack",
				UserSelectField.LAST_NAME, UserSelectWayCondition.EQUALS);
		Long l3 = userDao.countUsersBySearchValue("ork", UserSelectField.LOGIN,
				UserSelectWayCondition.EQUALS);
		Long l4 = userDao.countUsersBySearchValue("Administrator",
				UserSelectField.ROLE, UserSelectWayCondition.EQUALS);
		Long l5 = userDao.countUsersBySearchValue("Orest", UserSelectField.ALL,
				UserSelectWayCondition.EQUALS);
		Long l6 = userDao.countUsersBySearchValue("Orest", UserSelectField.ALL,
				UserSelectWayCondition.NOT_EQUALS_TO);

		Long l1r = (long) 1;
		Long l2r = (long) 1;
		Long l3r = (long) 1;
		Long l4r = (long) 2;
		Long l5r = (long) 1;
		Long l6r = (long) 2;

		assertEquals(l1r, l1);
		assertEquals(l2r, l2);
		assertEquals(l3r, l3);
		assertEquals(l4r, l4);
		assertEquals(l5r, l5);
		assertEquals(l6r, l6);
	}

	private void createUsers() {
		role1 = new Role();
		role2 = new Role();

		role1.setRoleName("Administrator");
		role2.setRoleName("Supervisor");

		roleDao.insertOrUpdate(role1);
		roleDao.insertOrUpdate(role2);

		user1 = new User();
		user2 = new User();
		user3 = new User();

		user1.setFirstName("orest");
		user1.setLastName("vovchack");
		user1.setLogin("ork");
		user1.setPassword("123");
		user1.setRole(role1);
		user1.setEmail("email@gmail.com");

		user2.setFirstName("ivanka");
		user2.setLastName("horoshko");
		user2.setLogin("rudenjke");
		user2.setPassword("123");
		user2.setRole(role2);
		user2.setEmail("email@gmail.com");

		user3.setFirstName("vitalik");
		user3.setLastName("nobis");
		user3.setLogin("vnobis");
		user3.setPassword("123");
		user3.setRole(role1);
		user3.setEmail("email@gmail.com");
	}
}
