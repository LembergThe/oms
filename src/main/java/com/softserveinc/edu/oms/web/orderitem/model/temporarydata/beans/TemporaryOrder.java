//
// TemporaryOrder
//
// 12 вер. 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans;

import com.softserveinc.edu.oms.domain.entities.Order;

/**
 * @author Ivanka
 * 
 */
public class TemporaryOrder {
	private Integer id;
	private Order order;

	public TemporaryOrder(final Integer id, final Order order) {
		super();
		this.id = id;
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(final Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			if (obj instanceof Integer && id.equals(obj)) {
				return true;
			}
			return false;
		}
		TemporaryOrder other = (TemporaryOrder) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
