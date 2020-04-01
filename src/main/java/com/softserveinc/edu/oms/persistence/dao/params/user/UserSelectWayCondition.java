package com.softserveinc.edu.oms.persistence.dao.params.user;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public enum UserSelectWayCondition {
	EQUALS("equals") {
		@Override
		public Criterion createStringCriterion(final String propertyName,
				final String value) {
			return Restrictions.eq(propertyName, value);
		}

	},
	NOT_EQUALS_TO("not equals to") {
		@Override
		public Criterion createStringCriterion(final String propertyName,
				final String value) {
			return Restrictions.not(Restrictions.eq(propertyName, value));
		}
	},
	STARTS_WITH("starts with") {

		@Override
		public Criterion createStringCriterion(final String propertyName,
				final String value) {
			return Restrictions.like(propertyName, value + "%");
		}

	},
	CONTAINS("contains") {

		@Override
		public Criterion createStringCriterion(final String propertyName,
				final String value) {
			return Restrictions.like(propertyName, "%" + value + "%");
		}

	},
	DOES_NOT_CONTAIN("does not contain") {

		@Override
		public Criterion createStringCriterion(final String propertyName,
				final String value) {
			return Restrictions.not(Restrictions.like(propertyName, "%" + value
					+ "%"));
		}

	};

	private final String displayText;

	private UserSelectWayCondition(final String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}

	public Criterion createStringCriterion(final String propertyName,
			final String value) {
		return Restrictions.like(propertyName, value + "%");
	}

	@Override
	public String toString() {
		return displayText;
	}

}
