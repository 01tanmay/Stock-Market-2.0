package com.estock.market.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * This is the Entity class for the Company
 */
@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "Company Code is Mandatory")
	@Column(name = "company_code")
	private String companyCode;

	@NotBlank(message = "Company Name is Mandatory")
	@Column(name = "company_name")
	private String companyName;

	@NotBlank(message = "Company CEO is Mandatory")
	@Column(name = "company_ceo")
	private String companyCeo;

	@NotNull(message = "Company TurnOver is Mandatory")
	@DecimalMin(value = "10.00", inclusive = false, message = "CompanyTurnOver must be greater than 10.00")
	@Digits(fraction = 2, integer = 10)
	@Column(name = "company_turnover")
	private Float companyTurnOver;

	@NotBlank(message = "Company Website is Mandatory")
	@Column(name = "company_website")
	private String companyWebsite;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCeo() {
		return companyCeo;
	}

	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}

	public Float getCompanyTurnOver() {
		return companyTurnOver;
	}

	public void setCompanyTurnOver(Float companyTurnOver) {
		this.companyTurnOver = companyTurnOver;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

}
