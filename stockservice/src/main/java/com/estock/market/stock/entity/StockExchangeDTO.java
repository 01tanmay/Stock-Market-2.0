package com.estock.market.stock.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This is the Entity class for StockExchange
 * 
 *
 */
@Document(collection = "StockDetails")
public class StockExchangeDTO {

	@Id
	private String id;

	private Float price;

	private LocalDateTime stockCreatedDate;

	private String companyCode;

	public StockExchangeDTO(String id, Float price, LocalDateTime stockCreatedDate, String companyCode) {
		super();
		this.id = id;
		this.price = price;
		this.stockCreatedDate = stockCreatedDate;
		this.companyCode = companyCode;
	}

	public StockExchangeDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public LocalDateTime getStockCreatedDate() {
		return stockCreatedDate;
	}

	public void setStockCreatedDate(LocalDateTime stockCreatedDate) {
		this.stockCreatedDate = stockCreatedDate;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
