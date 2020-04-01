package com.softserveinc.edu.oms.domain.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;

@Entity
@Table(name = "Users")
public class User extends AbstractEntity implements Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 800979855728109655L;

	private Integer id;
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String email;
	private Boolean active;

	private CustomerType customerType;
	private Double balance;

	private Role role;
	private Region region;

	public User() {
		active = true;
	}

	@Override
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

	@Column(name = "FirstName", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LastName", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "RoleRef", nullable = false)
	public Role getRole() {
		return role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "RegionRef")
	public Region getRegion() {
		return region;
	}

	public void setRegion(final Region region) {
		this.region = region;
	}

	@Column(name = "Login", nullable = false)
	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	@Column(name = "Password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Column(name = "Email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "CustomerTypeRef")
	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(final CustomerType customerType) {
		this.customerType = customerType;
	}

	@Column(name = "Balance")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(final Double balance) {
		this.balance = balance;
	}

	@Column(name = "IsUserActive")
	public Boolean isActive() {
		return active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", login=" + login + ", password=" + password
				+ ", email=" + email + ", customerType=" + customerType
				+ ", balance=" + balance + ", role=" + role + ", region="
				+ region + "]";
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (!(obj instanceof User)) {
			return false;
		} else {
			User that = (User) obj;

			return that.getLogin().equals(login);
		}
	}
}
