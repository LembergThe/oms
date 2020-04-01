package com.softserveinc.edu.oms.persistence.dao.concrete;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.entities.CreditCardType;
import com.softserveinc.edu.oms.persistence.dao.HibernateDao;
import com.softserveinc.edu.oms.persistence.dao.interfaces.ICreditCardTypeDao;

@Repository
public class CreditCardTypeDao extends HibernateDao<CreditCardType> implements
		ICreditCardTypeDao {

	public CreditCardTypeDao() {
		super(CreditCardType.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.ICreditCardType#
	 * findRegionByName(java.lang.String)
	 */
	@Override
	public List<CreditCardType> findRegionByName(final String cardType) {
		return findByCriterions(Restrictions.like("cardType", cardType + "%"));
	}
}
