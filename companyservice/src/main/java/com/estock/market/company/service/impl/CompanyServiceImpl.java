package com.estock.market.company.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.estock.market.company.entity.CompanyDTO;
import com.estock.market.company.model.Company;
import com.estock.market.company.model.StockExchange;
import com.estock.market.company.repository.CompanyRepository;
import com.estock.market.company.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * This Method is used to check whether Company Exists in DB
	 * 
	 * @param companyCode
	 */
	@Override
	public CompanyDTO retrieveCompanyByCode(String companyCode) {
		Optional<CompanyDTO> company = companyRepository.findByCompanyCode(companyCode);
		if (company.isPresent()) {
			return company.get();
		}
		return null;
	}

	/**
	 * This Method is used to add company into DB
	 * 
	 * @param company
	 * @throws JsonProcessingException
	 */
	@Override
	public CompanyDTO addCompanyInfo(Company company) throws JsonProcessingException {
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.setCompanyCeo(company.getCompanyCeo());
		companyDto.setCompanyCode(company.getCompanyCode());
		companyDto.setCompanyName(company.getCompanyName());
		companyDto.setCompanyTurnOver(company.getCompanyTurnOver());
		companyDto.setCompanyWebsite(company.getCompanyWebsite());
		LOGGER.info("Company Object: " + new ObjectMapper().writeValueAsString(companyDto));
		return companyRepository.save(companyDto);
	}

	/**
	 * This Method is used to retriveAllCompanyDetails
	 * 
	 */
	@Override
	public List<CompanyDTO> retriveAllCompanyInfo() {

		List<CompanyDTO> allCompanyDetails = companyRepository.findAll();
		if (!CollectionUtils.isEmpty(allCompanyDetails)) {
			return allCompanyDetails;
		} else {
			return null;
		}
	}

	/**
	 * This Method is used to delete company details
	 * 
	 * @param companyCode
	 */
	@Override
	public void deleteCompanyInfo(String companyCode) {
		companyRepository.deleteByCompanyCode(companyCode);
	}

	@Override
	public StockExchange addStock(StockExchange stockExchange, String companyCode) throws JsonProcessingException {

		String baseUrl = "http://localhost:8082/api/v1.0/market/stock/add/new/" + companyCode;
		ResponseEntity<StockExchange> response = null;
		LOGGER.info("StockExchange value is : " + new ObjectMapper().writeValueAsString(stockExchange));

		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.POST, getRequestEntity(stockExchange),
					StockExchange.class);
			return response.getBody();
		} catch (Exception e) {
			LOGGER.error("Exception while calling Stock Service. " + e);
		}
		return null;
	}

	@Override
	public StockExchange retrieveLatestStockByCompanyCode(String companyCode) {

		String baseUrl = "http://localhost:8082/api/v1.0/market/stock/latest/" + companyCode;

		ResponseEntity<StockExchange> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaderRequestEntity(), StockExchange.class);
			LOGGER.info("Response from Stock Service is : " + new ObjectMapper().writeValueAsString(response));
			return response.getBody();
		} catch (Exception e) {
			LOGGER.error("Exception occured while calling Stock Service. " + e);
		}
		return null;
	}

	@Override
	public Boolean deleteStockByCompanyCode(String companyCode) {
		String baseUrl = "http://localhost:8082/api/v1.0/market/stock/delete/" + companyCode;
		ResponseEntity<String> response = null;
		Boolean flag = false;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.DELETE, getHeaderRequestEntity(), String.class);
			LOGGER.info("Response from Stock Service is : " + new ObjectMapper().writeValueAsString(response));
			if (null != response) {
				if (null != response.getBody()) {
					String status = response.getBody();
					if (null != status && status.equalsIgnoreCase("Success")) {
						flag = true;
						return flag;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured while calling Stock Service. " + e);
		}
		return flag;
	}

	private static HttpEntity<Object> getHeaderRequestEntity() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<Object>(httpHeaders);
	}

	private static HttpEntity<StockExchange> getRequestEntity(StockExchange stockExchange) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<StockExchange>(stockExchange, httpHeaders);
	}

	@Override
	public Boolean isCompanyAlreadyExists(String companyCode) {
		Optional<CompanyDTO> company = companyRepository.findByCompanyCode(companyCode);
		if (company.isPresent()) {
			return true;
		}
		return false;
	}
}
