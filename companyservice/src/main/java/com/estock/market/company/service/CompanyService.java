package com.estock.market.company.service;

import java.util.List;

import com.estock.market.company.entity.CompanyDTO;
import com.estock.market.company.model.Company;
import com.estock.market.company.model.StockExchange;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * This Interface is used to perform CRUD operation on CompanyDetails
 * 
 *
 *
 */
public interface CompanyService {

	CompanyDTO retrieveCompanyByCode(String companyCode);

	CompanyDTO addCompanyInfo(Company company) throws JsonProcessingException;

	List<CompanyDTO> retriveAllCompanyInfo();

	void deleteCompanyInfo(String companyCode);

	StockExchange addStock(StockExchange stockExchange, String companyCode) throws JsonProcessingException;

	StockExchange retrieveLatestStockByCompanyCode(String companyCode);

	Boolean deleteStockByCompanyCode(String companyCode);

	Boolean isCompanyAlreadyExists(String companyCode);

}
