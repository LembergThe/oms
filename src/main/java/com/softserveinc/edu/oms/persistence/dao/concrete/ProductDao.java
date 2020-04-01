/**
 * ProductDAO.java
 *
 * version - v1.0
 *
 * 9 лип. 2011 - 21:52:56 
 *
 * (c) sh.muro[at]gmail.com
 *
 */
package com.softserveinc.edu.oms.persistence.dao.concrete;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.persistence.dao.HibernatePageableDao;
import com.softserveinc.edu.oms.persistence.dao.interfaces.IProductDao;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

/**
 * @author Orest
 * 
 * @version v1.0
 * 
 */
@Repository
public class ProductDao extends HibernatePageableDao<Product> implements
		IProductDao {

	private static final Criterion IF_IS_ACTIVE = Restrictions.eq("active",
			true);

	public ProductDao() {
		super(Product.class);
	}

	@Override
	public List<Product> findAll(final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties) {
		return findByCriterions(startingFrom, maxResult, sortProperties,
				IF_IS_ACTIVE);
	}

	@Override
	public Long getRowCount() {
		return getRowCountByCriterions(IF_IS_ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#findByName
	 * (java.lang.String)
	 */
	@Override
	public List<Product> findByName(final String productName) {
		return findByCriterions(
				Restrictions.like("productName", productName + "%"),
				IF_IS_ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#findByName
	 * (java.lang.String,
	 * com.softserveinc.edu.oms.persistence.dao.params.SortProperties)
	 */
	@Override
	public List<Product> findByName(final String productName,
			final SortProperties sortProperties) {
		return findByCriterions(sortProperties,
				Restrictions.like("productName", productName + "%"),
				IF_IS_ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#
	 * containsProductByName(java.lang.String)
	 */
	@Override
	public boolean containsProductByName(final String productName) {
		return findByCriterions(Restrictions.like("productName", productName))
				.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#
	 * numberOfProductByName(java.lang.String)
	 */
	@Override
	public int numberOfProductByName(final String productName) {
		return findByCriterions(Restrictions.like("productName", productName))
				.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#
	 * findByDescription(java.lang.String)
	 */
	@Override
	public List<Product> findByDescription(final String productDescription) {
		return findByCriterions(
				Restrictions.like("productDescription", productDescription
						+ "%"), IF_IS_ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#
	 * findByDescription(java.lang.String,
	 * com.softserveinc.edu.oms.persistence.dao.params.SortProperties)
	 */
	@Override
	public List<Product> findByDescription(final String productDescription,
			final SortProperties sortProperties) {
		return findByCriterions(
				sortProperties,
				Restrictions.like("productDescription", productDescription
						+ "%"), IF_IS_ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#
	 * containsProductByDescription(java.lang.String)
	 */
	@Override
	public boolean containsProductByDescription(final String productDescription) {
		return findByCriterions(
				Restrictions.like("productDescription", productDescription))
				.size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#
	 * numberOfProductByDescription(java.lang.String)
	 */
	@Override
	public int numberOfProductByDescription(final String productDescription) {
		return findByCriterions(
				Restrictions.like("productDescription", productDescription))
				.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#findByPrice
	 * (java.lang.Double)
	 */
	@Override
	public List<Product> findByPrice(final Double productPrice) {
		return findByCriterions(Restrictions.eq("productPrice", productPrice),
				IF_IS_ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#
	 * findByPriceFromTo(java.lang.Double, java.lang.Double)
	 */
	@Override
	public List<Product> findByPriceFromTo(final Double productPriceFrom,
			final Double productPriceTo) {
		return findByCriterions(Restrictions.between("productPrice",
				productPriceFrom, productPriceTo), IF_IS_ACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserveinc.edu.oms.persistence.dao.concrete.IProductDAO#
	 * findByPriceFromTo(java.lang.Double, java.lang.Double,
	 * com.softserveinc.edu.oms.persistence.dao.params.SortProperties)
	 */
	@Override
	public List<Product> findByPriceFromTo(final Double productPriceFrom,
			final Double productPriceTo, final SortProperties sortProperties) {
		return findByCriterions(sortProperties, Restrictions.between(
				"productPrice", productPriceFrom, productPriceTo), IF_IS_ACTIVE);
	}

	@Override
	public List<Product> findByName(final String productName,
			final Integer startingFrom, final Integer maxResult,
			final SortProperties sortProperties) {
		return findByCriterions(startingFrom, maxResult, sortProperties,
				Restrictions.like("productName", productName + "%"),
				IF_IS_ACTIVE);
	}

	@Override
	public List<Product> findByDescription(final String productDescription,
			final Integer startingFrom, final Integer maxResult,
			final SortProperties sortProperties) {
		return findByCriterions(
				startingFrom,
				maxResult,
				sortProperties,
				Restrictions.like("productDescription", productDescription
						+ "%"), IF_IS_ACTIVE);
	}

	@Override
	public Long countProductsByName(final String productName) {
		return getRowCountByCriterions(
				Restrictions.like("productName", productName + "%"),
				IF_IS_ACTIVE);
	}

	@Override
	public Long countProductsByDescription(final String productDescription) {
		return getRowCountByCriterions(
				Restrictions.like("productDescription", productDescription
						+ "%"), IF_IS_ACTIVE);
	}
}
