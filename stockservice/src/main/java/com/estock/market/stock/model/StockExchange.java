package com.estock.market.stock.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * This is the Entity class for StockExchange
 * 
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class StockExchange {

	@NotNull(message = "Price is Mandatory")
	@DecimalMin(value = "0.00", inclusive = false, message = "Stock Price must be Fractional Value")
	@Digits(integer = 10, fraction = 2)
	private Float price;

	private String stockCreatedDate;

	private String companyCode;

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getStockCreatedDate() {
		return stockCreatedDate;
	}

	public void setStockCreatedDate(String stockCreatedDate) {
		this.stockCreatedDate = stockCreatedDate;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
