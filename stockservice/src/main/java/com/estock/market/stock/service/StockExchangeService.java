package com.estock.market.stock.service;

import com.estock.market.stock.entity.CompanyStockDetails;
import com.estock.market.stock.entity.StockExchangeDTO;
import com.estock.market.stock.model.StockExchange;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * This Interface is used to perform CRUD operation on StockExchange Details
 * 
 *
 *
 */
public interface StockExchangeService {

	boolean isCompanyCodeExists(String companyCode) throws JsonProcessingException;

	StockExchangeDTO addStockPrice(StockExchange stockExchange, String companyCode) throws JsonProcessingException;

	CompanyStockDetails getStocksBetweenDate(String startDate, String endDate, String companyCode)
			throws JsonProcessingException;

	StockExchangeDTO retrieveLatestStockByCompanyCode(String companyCode);

	void deleteStocks(String companyCode);
}
