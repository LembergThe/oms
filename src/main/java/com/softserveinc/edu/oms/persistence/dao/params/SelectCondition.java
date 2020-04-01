package com.softserveinc.edu.oms.persistence.dao.params;

import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public enum SelectCondition {
	EQUALS("equals") {
		@Override
		public Criterion createStringCriterion(final String propertyName,
				final String value) {
			return Restrictions.eq(propertyName, value);
		}

	},
	NOT_EQUALS_TO("not equals to"), STARTS_WITH("starts with"), CONTAINS(
			"contains"), DOES_NOT_CONTAIN("does not contain"), GREATER_THAN(
			"greater than"), LESS_THAN("less than");

	private final String displayText;

	private SelectCondition(final String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}

	public Criterion createStringCriterion(final String propertyName,
			final String value) {

		switch (this) {

		case EQUALS:
			return Restrictions.eq(propertyName, value);

		case NOT_EQUALS_TO:
			return Restrictions.not(Restrictions.eq(propertyName, value));

		case CONTAINS:
			return Restrictions.like(propertyName, "%" + value + "%");

		case DOES_NOT_CONTAIN:
			return Restrictions.not(Restrictions.like(propertyName, "%" + value
					+ "%"));

		default:
			return Restrictions.like(propertyName, value + "%");
		}
	}

	public Criterion createDoubleCriterion(final String propertyName,
			final double value) {
		switch (this) {

		case EQUALS:
			return Restrictions.eq(propertyName, value);

		case GREATER_THAN:
			return Restrictions.gt(propertyName, value);

		case LESS_THAN:
			return Restrictions.lt(propertyName, value);
		default:
			return Restrictions.eq(propertyName, value);
		}
	}

	public Criterion createDateCriterion(final String propertyName,
			final Date value) {
		switch (this) {

		case EQUALS:
			return Restrictions.eq(propertyName, value);

		case GREATER_THAN:
			return Restrictions.gt(propertyName, value);

		case LESS_THAN:
			return Restrictions.lt(propertyName, value);
		default:
			return Restrictions.eq(propertyName, value);
		}
	}

	@Override
	public String toString() {
		return displayText;
	}
}
