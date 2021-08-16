package com.estock.market.stock.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * This is the Model class for CompanyStockDetails
 * 
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class CompanyStockDetails {

	private String companyCode;
	private Float minStockPrice;
	private Float maxStockPrice;
	private Double averageStockPrice;
	@JsonIgnoreProperties("company")
	private List<StockExchangeDTO> stockExchanges;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Float getMinStockPrice() {
		return minStockPrice;
	}

	public void setMinStockPrice(Float minStockPrice) {
		this.minStockPrice = minStockPrice;
	}

	public Float getMaxStockPrice() {
		return maxStockPrice;
	}

	public void setMaxStockPrice(Float maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}

	public Double getAverageStockPrice() {
		return averageStockPrice;
	}

	public void setAverageStockPrice(Double averageStockPrice) {
		this.averageStockPrice = averageStockPrice;
	}

	public List<StockExchangeDTO> getStockExchanges() {
		return stockExchanges;
	}

	public void setStockExchanges(List<StockExchangeDTO> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}

}
