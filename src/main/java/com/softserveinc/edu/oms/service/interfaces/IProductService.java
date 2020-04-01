package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.web.itemManagement.util.ProductSelectField;

public interface IProductService extends ServiceForPaging<Product> {

	/**
	 * This method searches products by name or part of it.
	 * 
	 * @param productName
	 *            product name that need to find;
	 * 
	 * @return list of products with specific name;
	 * 
	 * */
	List<Product> findByName(final String productName);

	List<Product> findByName(final String productName,
			final Integer startingFrom, final Integer maxResult,
			final SortProperties sortProperties);

	/**
	 * This method searches products by name or part of it with sorting
	 * capabilities.
	 * 
	 * @param productName
	 *            product name that need to find;
	 * 
	 * @param sortProperties
	 *            sorting options;
	 * 
	 * @return list of products with a sorted name;
	 * 
	 * */
	List<Product> findByName(final String productName,
			SortProperties sortProperties);

	/**
	 * This method checks whether this product in the database by name.
	 * 
	 * @param productName
	 *            product name that need to check;
	 * 
	 * @return true if this product exists in the database and false in another
	 *         case;
	 * 
	 * */
	boolean containsProductByName(final String productName);

	/**
	 * This method returns number of specific product by name.
	 * 
	 * @param product
	 *            name that need to count;
	 * 
	 * @return number of products;
	 * 
	 **/
	int numberOfProductByName(final String productName);

	/**
	 * This method searches products by its description or part of it.
	 * 
	 * @param productDescription
	 *            product description that need to find;
	 * 
	 * @return list of products with specific description;
	 * 
	 * */
	List<Product> findByDescription(final String productDescription);

	List<Product> findByDescription(final String productDescription,
			final Integer startingFrom, final Integer maxResult,
			final SortProperties sortProperties);

	/**
	 * This method searches products by its description or part of it with
	 * sorting capabilities.
	 * 
	 * @param productDescription
	 *            product description that need to find;
	 * 
	 * @param sortProperties
	 *            sorting options;
	 * 
	 * @return list of products with specific description;
	 * 
	 * */
	List<Product> findByDescription(final String productDescription,
			SortProperties sortProperties);

	/**
	 * This method checks whether this product in the database by description or
	 * part of it.
	 * 
	 * @param productDescription
	 *            product description that need to check;
	 * 
	 * @return true if this product exists in the database and false in another
	 *         case;
	 * 
	 * */
	boolean containsProductByDescription(final String productDescription);

	/**
	 * This method returns number of specific product by description.
	 * 
	 * @param product
	 *            description that need to count;
	 * 
	 * @return number of products;
	 * 
	 **/
	int numberOfProductByDescription(final String productDescription);

	/**
	 * This method searches products by its price.
	 * 
	 * @param productPrice
	 *            product price that need to find;
	 * 
	 * @return list of products with specific price;
	 * 
	 * */
	List<Product> findByPrice(final Double productPrice);

	/**
	 * This method searches products by its price(from productPriceFrom to
	 * productPriceTo values).
	 * 
	 * @param productPriceFrom
	 *            initial value for price that need to find;
	 * 
	 * @param productPriceTo
	 *            final value for price that need to find;
	 * 
	 * @return list of products with specific price range;
	 * 
	 * */
	List<Product> findByPriceFromTo(final Double productPriceFrom,
			final Double productPriceTo);

	/**
	 * This method searches products by its price with sorting capabilities(from
	 * productPriceFrom to productPriceTo values).
	 * 
	 * @param productPriceFrom
	 *            initial value for price that need to find;
	 * 
	 * @param productPriceTo
	 *            final value for price that need to find;
	 * 
	 * @param sortProperties
	 *            sorting properties;
	 * 
	 * @return list of products with specific sorted price range;
	 * 
	 * */
	List<Product> findByPriceFromTo(final Double productPriceFrom,
			final Double productPriceTo, SortProperties sortProperties);

	List<Product> findBySearchValue(final String searchValue,
			final ProductSelectField selectField, final Integer startingFrom,
			final Integer maxResult, final SortProperties sortProperties);

	Integer countProductsBySearchValue(final String searchValue,
			final ProductSelectField selectField);

	Long countProductsByName(final String productName);

	Long countProductsByDescription(final String productDescription);
}
