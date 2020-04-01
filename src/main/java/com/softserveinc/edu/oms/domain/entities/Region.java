package com.softserveinc.edu.oms.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;

@Entity
@Table(name = "Regions")
public class Region extends AbstractEntity implements Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -886563328312236401L;

	private Integer id;
	private String regionName;

	public Region() {
	}

	public Region(final Integer id, final String name) {
		this.id = id;
		regionName = name;
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

	@Column(name = "RegionName")
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(final String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return regionName;
	}

}
