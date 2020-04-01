package com.softserveinc.edu.oms.persistence.dao.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.CreditCardType;
import com.softserveinc.edu.oms.persistence.dao.IDao;

public interface ICreditCardTypeDao extends IDao<CreditCardType> {

	List<CreditCardType> findRegionByName(String cardType);

}
