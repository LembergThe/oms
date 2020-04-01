package com.softserveinc.edu.oms.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;

@Entity
@Table(name = "Roles")
public class Role extends AbstractEntity implements Comparable<Role>,
		Serializable {
	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 854019921809349570L;

	private Integer id;
	private String roleName;

	public Role() {
	}

	public Role(final Integer id, final String name) {
		super();
		this.id = id;
		roleName = name;
	}

	@Override
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

	@Column(name = "RoleName")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return roleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (roleName == null ? 0 : roleName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Role other = (Role) obj;
		if (roleName == null) {
			if (other.roleName != null) {
				return false;
			}
		} else if (!roleName.equals(other.roleName)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(final Role o) {
		return roleName.compareTo(o.roleName);
	}
	
	

}
