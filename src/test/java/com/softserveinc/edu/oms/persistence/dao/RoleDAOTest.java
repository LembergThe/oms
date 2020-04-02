/**
 * RoleDAOTest.java 
 *
 * version - v1.0
 *
 * 9 . 2011 - 15:52:35
 *
 * (c) marko
 *
 */
package com.softserveinc.edu.oms.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.softserveinc.edu.oms.domain.entities.Role;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;

/**
 * @author marko
 * 
 * @version v1.0
 * 
 */
public class RoleDAOTest extends CleanUpDBTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {

		Role role = new Role();
		role.setRoleName("Administrator");
		roleDao.insertOrUpdate(role);
		role = new Role();
		role.setRoleName("Supervisor");
		roleDao.insertOrUpdate(role);
		role = new Role();
		role.setRoleName("Customer");
		roleDao.insertOrUpdate(role);
		role = new Role();
		role.setRoleName("Merchandiser");
		roleDao.insertOrUpdate(role);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		super.cleanRole();
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.RoleDAO#getAdminRole()}.
	 */
	@Test
	public void testGetAdminRole() {
		String expected = "Administrator";
		assertEquals(expected, roleDao.getAdminRole().getRoleName());

		// for null return value
		Role tmp = roleDao.getAdminRole();
		roleDao.delete(tmp);
		assertEquals(null, roleDao.getAdminRole());
		Role role = new Role();
		role.setRoleName(tmp.getRoleName());
		roleDao.insertOrUpdate(role);
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.RoleDAO#getCustomerRole()}
	 * .
	 */
	@Test
	public void testGetCustomerRole() {
		String expected = "Customer";
		assertEquals(expected, roleDao.getCustomerRole().getRoleName());

		// for null return value
		Role tmp = roleDao.getCustomerRole();
		roleDao.delete(tmp);
		assertEquals(null, roleDao.getCustomerRole());
		Role role = new Role();
		role.setRoleName(tmp.getRoleName());
		roleDao.insertOrUpdate(role);
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.RoleDAO#getMerchandiserRole()}
	 * .
	 */
	@Test
	public void testGetMerchandiserRole() {
		String expected = "Merchandiser";
		assertEquals(expected, roleDao.getMerchandiserRole().getRoleName());

		// for null return value
		Role tmp = roleDao.getMerchandiserRole();
		roleDao.delete(tmp);
		assertEquals(null, roleDao.getMerchandiserRole());
		Role role = new Role();
		role.setRoleName(tmp.getRoleName());
		roleDao.insertOrUpdate(role);
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.RoleDAO#getSupervisorRole()}
	 * .
	 */
	@Test
	public void testGetSupervisorRole() {
		String expected = "Supervisor";
		assertEquals(expected, roleDao.getSupervisorRole().getRoleName());

		// for null return value
		Role tmp = roleDao.getSupervisorRole();
		roleDao.delete(tmp);
		assertEquals(null, roleDao.getSupervisorRole());
		Role role = new Role();
		role.setRoleName(tmp.getRoleName());
		roleDao.insertOrUpdate(role);
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.RoleDAO#findByRoleName(java.lang.String)}
	 * .
	 */
	@Test
	public void testFindByRoleName() {
		String expected = "Merchandiser";
		assertEquals(expected, roleDao.findByRoleName(expected).get(0)
				.getRoleName());
		expected = "Supervisor";
		assertEquals(expected, roleDao.findByRoleName(expected).get(0)
				.getRoleName());
		expected = "Administrator";
		assertEquals(expected, roleDao.findByRoleName(expected).get(0)
				.getRoleName());
		expected = "Customer";
		assertEquals(expected, roleDao.findByRoleName(expected).get(0)
				.getRoleName());
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.RoleDAO#containsRole(java.lang.String)}
	 * .
	 */
	@Test
	public void testContainsRole() {
		assertTrue(roleDao.containsRole("Administrator"));
		assertTrue(roleDao.containsRole("Supervisor"));
		assertTrue(roleDao.containsRole("Merchandiser"));
		assertTrue(roleDao.containsRole("Customer"));
		assertFalse(roleDao.containsRole("Stepan"));
		assertFalse(roleDao.containsRole("Actor"));
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.HibernateDao#findByCriterions(org.hibernate.criterion.Criterion[])}
	 * .
	 */
	@Test
	public void testFindByCriterionsSorting() {
		String roleName = "%is%";
		int idMax = 100;
		Criterion criterion1 = Restrictions.like("roleName", roleName);
		Criterion criterion2 = Restrictions.le("id", idMax);
		List<Role> roles = roleDao.findByCriterions(new SortProperties(
				"roleName", true), criterion1, criterion2);
		for (Role role : roles) {
			assertTrue(role.getId() < idMax
					&& role.getRoleName().contains(roleName.subSequence(1, 3)));
		}
		for (int i = 0; i < roles.size() - 1; i++) {
			assertTrue(roles.get(i).compareTo(roles.get(i + 1)) <= 0);
		}

		roles = roleDao.findByCriterions(new SortProperties("roleName", false),
				criterion1, criterion2);
		for (int i = 0; i < roles.size() - 1; i++) {
			assertTrue(roles.get(i).compareTo(roles.get(i + 1)) >= 0);
		}

		roles = roleDao.findByCriterions(new SortProperties("id", true),
				criterion1, criterion2);
		for (int i = 0; i < roles.size() - 1; i++) {
			assertTrue(roles.get(i).getId() <= roles.get(i + 1).getId());
		}

		roles = roleDao.findByCriterions(new SortProperties("id", false),
				criterion1, criterion2);
		for (int i = 0; i < roles.size() - 1; i++) {
			assertTrue(roles.get(i).getId() >= roles.get(i + 1).getId());
		}

	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.HibernateDao#findByCriterions(org.hibernate.criterion.Criterion[])}
	 * .
	 */
	@Test
	public void testFindByCriterions() {
		String roleName = "admin";
		int idMax = 100;
		Criterion criterion1 = Restrictions.eq("roleName", roleName);
		Criterion criterion2 = Restrictions.le("id", idMax);
		List<Role> roles = roleDao.findByCriterions(criterion1, criterion2);
		for (Role role : roles) {
			assertTrue(role.getId() < idMax
					&& role.getRoleName().equals(roleName));
		}

	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.HibernateDao#findByOneOfCriterions(org.hibernate.criterion.Criterion[])}
	 * .
	 */
	@Test
	public void testFindByOneOfCriterions() {
		String roleName = "%is%";
		int idMax = 100;
		Criterion criterion1 = Restrictions.like("roleName", roleName);
		Criterion criterion2 = Restrictions.le("id", idMax);
		List<Role> roles = roleDao
				.findByOneOfCriterions(criterion1, criterion2);
		for (Role role : roles) {
			assertTrue(role.getRoleName().contains(roleName.subSequence(1, 3))
					|| role.getId() < idMax);
		}
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.HibernateDao#findByOneOfCriterions(org.hibernate.criterion.Criterion[])}
	 * .
	 */
	@Test
	public void testFindByOneOfCriterionsSorting() {
		String roleName = "%is%";
		int idMax = 100;
		Criterion criterion1 = Restrictions.like("roleName", roleName);
		Criterion criterion2 = Restrictions.le("id", idMax);
		List<Role> roles = roleDao.findByCriterions(new SortProperties(
				"roleName", true), criterion1, criterion2);
		for (Role role : roles) {
			assertTrue(role.getRoleName().equals(roleName)
					|| role.getId() < idMax);
		}
		for (int i = 0; i < roles.size() - 1; i++) {
			assertTrue(roles.get(i).compareTo(roles.get(i + 1)) <= 0);
		}

		roles = roleDao.findByCriterions(new SortProperties("roleName", false),
				criterion1, criterion2);
		for (Role role : roles) {
			assertTrue(role.getRoleName().equals(roleName)
					|| role.getId() < idMax);
		}
		for (int i = 0; i < roles.size() - 1; i++) {
			assertTrue(roles.get(i).compareTo(roles.get(i + 1)) >= 0);
		}

	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.HibernateDao#findByID(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testFindByID() {
		List<Role> roles = roleDao.findAll();
		for (Role role : roles) {
			Role tmpRole = roleDao.findByID(role.getId());
			assertEquals(tmpRole, role);
		}
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.HibernateDao#insertOrUpdate(com.softserverinc.edu.oms.persistance.AbstractEntity)}
	 * .
	 */
	@Test
	public void testInsertOrUpdate() {
		Role expected = new Role();
		expected.setRoleName("tempRole");
		roleDao.insertOrUpdate(expected);
		assertEquals(expected, roleDao.findByID(expected.getId()));

		Role tempRole = roleDao.findAll().get(0);
		expected = new Role();
		expected.setId(tempRole.getId());
		expected.setRoleName(tempRole.getRoleName());

		tempRole.setRoleName("new Role name (:");

		roleDao.insertOrUpdate(tempRole);

		assertFalse(tempRole.equals(expected));

		tempRole.setRoleName(expected.getRoleName());
		roleDao.insertOrUpdate(tempRole);

	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.HibernateDao#delete(com.softserverinc.edu.oms.persistance.AbstractEntity)}
	 * .
	 */
	@Test
	public void testDelete() {
		Role expected = new Role();
		expected.setRoleName("tempRole");
		roleDao.insertOrUpdate(expected);
		roleDao.delete(expected);
		List<Role> roles = roleDao.findAll();
		for (Role role : roles) {
			assertFalse(expected.equals(role));
		}
	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.RoleDAO#findByRoleName(java.lang.String, UserSelectWayCondition)}
	 * .
	 */
	@Test
	public void testFindByRoleNameWithUserSelectWayConditions() {
		String expected = "tempAdmin";
		Role tmp = new Role();
		tmp.setRoleName(expected);
		roleDao.insertOrUpdate(tmp);
		List<Role> roles = roleDao.findByRoleName(expected,
				UserSelectWayCondition.EQUALS);
		assertTrue(roles.size() > 0);

		tmp.setRoleName(tmp.getRoleName() + " and something");
		roleDao.insertOrUpdate(tmp);
		assertTrue(roleDao.findByRoleName(expected,
				UserSelectWayCondition.STARTS_WITH).size() > 0);

		tmp.setRoleName("something here" + tmp.getRoleName()
				+ " and something here");
		roleDao.insertOrUpdate(tmp);
		assertTrue(roleDao.findByRoleName(expected,
				UserSelectWayCondition.CONTAINS).size() > 0);

	}

	/**
	 * Test method for
	 * {@link com.softserverinc.edu.oms.persistance.dao.RoleDAO#findByRoleName(java.lang.String, UserSelectWayCondition, SortProperties)}
	 * .
	 */
	@Test
	public void testFindByRoleNameWithUserSelectWayConditionsSorting() {
		String expected = "tempAdmin";
		Role tmp = new Role();
		tmp.setRoleName(expected);
		roleDao.insertOrUpdate(tmp);
		List<Role> roles = roleDao.findByRoleName(expected,
				UserSelectWayCondition.EQUALS);
		assertTrue(roles.size() > 0);

		tmp.setRoleName(tmp.getRoleName() + " and something");
		roleDao.insertOrUpdate(tmp);
		assertTrue(roleDao.findByRoleName(expected,
				UserSelectWayCondition.STARTS_WITH).size() > 0);

		tmp.setRoleName("something here" + tmp.getRoleName()
				+ " and something here");
		roleDao.insertOrUpdate(tmp);
		assertTrue(roleDao.findByRoleName(expected,
				UserSelectWayCondition.CONTAINS).size() > 0);

	}

}
