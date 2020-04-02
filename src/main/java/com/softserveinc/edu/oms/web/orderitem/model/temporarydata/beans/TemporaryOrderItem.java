//
// TemporaryOrderItem
//
// 12 . 2011
//
package com.softserveinc.edu.oms.web.orderitem.model.temporarydata.beans;

import com.softserveinc.edu.oms.domain.entities.OrderItem;

/**
 * @author Ivanka
 * 
 */
/**
 * @author Ivanka
 * 
 */
public class TemporaryOrderItem {
	private Integer temporaryId;
	private OrderItem orderItem;

	public TemporaryOrderItem(final Integer temporaryId,
			final OrderItem orderItem) {
		super();
		this.temporaryId = temporaryId;
		this.orderItem = orderItem;
	}

	public Integer getTemporaryId() {
		return temporaryId;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	@Override
	public int hashCode() {
		return temporaryId;
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
			if (obj instanceof Integer) {
				return temporaryId.equals(obj);
			}
			return false;
		}
		TemporaryOrderItem other = (TemporaryOrderItem) obj;
		if (temporaryId == null) {
			if (other.temporaryId != null) {
				return false;
			}
		} else if (!temporaryId.equals(other.temporaryId)) {
			return false;
		}
		return true;
	}

	/**
	 * @param orderItem
	 */
	public void setOrderItem(final OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	/**
	 * @param temporaryId
	 */
	public void setId(final Integer temporaryId) {
		this.temporaryId = temporaryId;
	}

}
