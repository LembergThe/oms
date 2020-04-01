package com.softserveinc.edu.oms.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softserveinc.edu.oms.domain.AbstractEntity;


@Entity
@Table(name = "CardType")
public class CreditCardType extends AbstractEntity {
	private Integer id;
	private String creditCardType;
	private String creditCardNumber;
	private int cvv2;
	private String expDate;
	private String issueDate;
	private String issueNumber;

	public CreditCardType() {
	}

	public CreditCardType(final Integer id, final String creditCardType ) {
		super();
		this.id = id;
		this.creditCardType = creditCardType;
	}
	

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	@Column(name = "CardType")
	public String getCardType() {
		return creditCardType;
	}

	public void setCardType(final String creditCardType) {
		this.creditCardType = creditCardType;
	}
	
	@Column(name = "CardNumber")
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	@Column(name = "CardCvv2")
	public int getCvv2() {
		return cvv2;
	}

	public void setCvv2(int cvv2) {
		this.cvv2 = cvv2;
	}
	
	@Column(name = "Expiration_Date")
	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	@Column(name = "IssueDate")
	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "IssueNumber")
	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}
	
	

}
