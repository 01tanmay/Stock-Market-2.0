package com.estock.market.company.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Company {

	@NotBlank(message = "Company Code is Mandatory")
	private String companyCode;

	@NotBlank(message = "Company Name is Mandatory")
	private String companyName;

	@NotBlank(message = "Company CEO is Mandatory")
	private String companyCeo;

	@NotNull(message = "Company TurnOver is Mandatory")
	@DecimalMin(value = "10.00", inclusive = false, message = "CompanyTurnOver must be greater than 10.00")
	@Digits(fraction = 2, integer = 10)
	private Float companyTurnOver;

	@NotBlank(message = "Company Website is Mandatory")
	private String companyWebsite;

	@NotNull(message = "Stock Exchange is Mandatory")
	private List<@Valid StockExchange> stockExchanges;

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

	public List<StockExchange> getStockExchanges() {
		return stockExchanges;
	}

	public void setStockExchanges(List<StockExchange> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}

}
