package com.estock.market.company.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estock.market.company.entity.CompanyDTO;
import com.estock.market.company.model.Company;
import com.estock.market.company.model.ErrorMessage;
import com.estock.market.company.model.StockExchange;
import com.estock.market.company.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1.0/market/company")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "Companies")
public class CompanyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	/**
	 * This Method is used to Register New Companies
	 * 
	 * @param company
	 * @return
	 */
	@PostMapping(value = "/register")
	@ApiOperation(value = "Registering a new Company")
	public ResponseEntity<Object> registerCompany(@RequestBody @Valid Company company) {
		try {

			if (!companyService.isCompanyAlreadyExists(company.getCompanyCode())) {
				companyService.addCompanyInfo(company);
				StockExchange stockExchange = companyService.addStock(company.getStockExchanges().get(0),
						company.getCompanyCode());
				company.getStockExchanges().get(0).setStockCreatedDate(stockExchange.getStockCreatedDate());
				LOGGER.info("New Company Registered Successfully");
				return ResponseEntity.status(HttpStatus.CREATED).body(company);
			} else {
				LOGGER.info("Company Code present in DB");
				ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Company Code Should be unique",
						"Company Code Already Exists");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
			}
		} catch (Exception e) {
			if ((e instanceof DataIntegrityViolationException)
					&& (e.getLocalizedMessage().contains("company_website_UNIQUE"))) {
				LOGGER.info("SQL Error : " + e.getLocalizedMessage());
				ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Company_Website should be UNIQUE",
						"Company_Website should be UNIQUE");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
			}
			LOGGER.info("Exception occured while processing add company request" + e);
			ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Technical Error Occured",
					"Add Request failed due to technical exception");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
		}

	}

	/**
	 * This Method is used to fetch CompanyDetails using CompanyCode
	 * 
	 * @param companyCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping(value = "/info/{companyCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get company by code")
	public ResponseEntity<Object> fetchCompanyDetailsByCode(@PathVariable("companyCode") String companyCode)
			throws JsonProcessingException {

		CompanyDTO companyDto = companyService.retrieveCompanyByCode(companyCode);
		if (null != companyDto) {
			StockExchange stockExchange = companyService.retrieveLatestStockByCompanyCode(companyCode);
			Company company = new Company();
			company.setCompanyCeo(companyDto.getCompanyCeo());
			company.setCompanyCode(companyDto.getCompanyCode());
			company.setCompanyName(companyDto.getCompanyName());
			company.setCompanyTurnOver(companyDto.getCompanyTurnOver());
			company.setCompanyWebsite(companyDto.getCompanyWebsite());
			company.setStockExchanges(Arrays.asList(stockExchange));
			LOGGER.info("API Response is  : " + new ObjectMapper().writeValueAsString(company));
			return ResponseEntity.status(HttpStatus.OK).body(company);
		} else {
			LOGGER.info("CompanyCode unavalable in DB");
			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Provided CompanyCode doesnot exists in DB",
					"Company Code is Not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}

	}

	/**
	 * This Method is used to fetch all the company details.
	 * 
	 * @return List<Company>
	 * @throws JsonProcessingException
	 */
	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all Companies")
	public ResponseEntity<Object> fetchAllCompanyDetails() throws JsonProcessingException {

		List<CompanyDTO> allCompanyDetails = companyService.retriveAllCompanyInfo();
		List<Company> companyDetails = new ArrayList<Company>();
		if (!CollectionUtils.isEmpty(allCompanyDetails)) {
			for (CompanyDTO companyDto : allCompanyDetails) {
				StockExchange stockExchange = companyService
						.retrieveLatestStockByCompanyCode(companyDto.getCompanyCode());
				Company company = new Company();
				company.setCompanyCeo(companyDto.getCompanyCeo());
				company.setCompanyCode(companyDto.getCompanyCode());
				company.setCompanyName(companyDto.getCompanyName());
				company.setCompanyTurnOver(companyDto.getCompanyTurnOver());
				company.setCompanyWebsite(companyDto.getCompanyWebsite());
				company.setStockExchanges(Arrays.asList(stockExchange));
				companyDetails.add(company);
			}
			LOGGER.info("API Response is : " + new ObjectMapper().writeValueAsString(companyDetails));
			return ResponseEntity.status(HttpStatus.OK).body(companyDetails);
		} else {
			LOGGER.info("CompanyDetails is Not Available in Database");
			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,
					"CompanyDetails is Not Available in Database", "CompanyDetails is Not Available in Database");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}

	/**
	 * This Method is used to Delete CompanyDetails Using CompanyCode
	 * 
	 * @param companyCode
	 * @return
	 */
	@DeleteMapping(value = "/delete/{companyCode}")
	@ApiOperation(value = "Delete a company by code")
	public ResponseEntity<Object> deleteCompany(@PathVariable("companyCode") String companyCode) {

		LOGGER.info("Inside deleteCompany Method");
		CompanyDTO companyDto = companyService.retrieveCompanyByCode(companyCode);
		if (null != companyDto) {
			companyService.deleteCompanyInfo(companyCode);
			Boolean isDeleted = companyService.deleteStockByCompanyCode(companyCode);
			if (isDeleted) {
				LOGGER.info("Company Code : " + companyCode + " - Stock info deleted Successfully from Database.");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				LOGGER.info("Failed to Delete the details in Stock Table");
				ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Failed to Delete the details",
						"Failed to Delete the details");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
			}
		} else {
			LOGGER.info("Provided CompanyCode doesnot exists in DB");
			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Provided CompanyCode doesnot exists in DB",
					"Company Code is Not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}
	}

}
