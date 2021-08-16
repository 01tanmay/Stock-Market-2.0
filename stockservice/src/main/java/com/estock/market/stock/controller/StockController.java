package com.estock.market.stock.controller;

import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estock.market.stock.entity.CompanyStockDetails;
import com.estock.market.stock.entity.StockExchangeDTO;
import com.estock.market.stock.model.ErrorMessage;
import com.estock.market.stock.model.StockExchange;
import com.estock.market.stock.service.StockExchangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;

/**
 * This Class handles all the requests for StockExchange related information
 * 
 * 
 *
 */
@RestController
@RequestMapping("/api/v1.0/market/stock")
@CrossOrigin
public class StockController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockExchangeService stockExchangeService;

	/**
	 * This Method is used to add StockPrice by Company
	 * 
	 * @param companyCode
	 * @param stockExchange
	 * @return
	 * @throws JsonProcessingException
	 */

	@ApiOperation(value = "Registering a new Stock for existing company")
	@PostMapping(value = "/add/{companyCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addStockPriceByCompanyCode(@PathVariable("companyCode") String companyCode,
			@RequestBody @Valid StockExchange stockExchange) throws JsonProcessingException {

		if (stockExchangeService.isCompanyCodeExists(companyCode)) {
			StockExchangeDTO stockExchangeDTO = stockExchangeService.addStockPrice(stockExchange, companyCode);
			if (null != stockExchangeDTO) {
				StockExchange stockExchangeResp = new StockExchange();
				stockExchangeResp.setCompanyCode(stockExchangeDTO.getCompanyCode());
				stockExchangeResp.setPrice(stockExchangeDTO.getPrice());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]");
				stockExchangeResp.setStockCreatedDate(stockExchangeDTO.getStockCreatedDate().format(formatter));
				LOGGER.info("API Response : " + new ObjectMapper().writeValueAsString(stockExchangeResp));
				return ResponseEntity.status(HttpStatus.OK).body(stockExchangeResp);
			} else {
				LOGGER.info("Failed to Add the stock price in Stock Table");
				ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,
						"Failed to Add the stock price in Stock Table", "Failed to Add the stock price in Stock Table");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
			}
		} else {
			LOGGER.info("Provided CompanyCode doesnot exists in DB");
			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Provided CompanyCode doesnot exists in DB",
					"Company Code is Not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}
	}

	/**
	 * This Method is used to view StockDetails of Company in Given DateRange
	 * 
	 * @param companyCode
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws JsonProcessingException
	 */
	@ApiOperation(value = "View StockDetails of Company in Given DateRange")
	@GetMapping(value = "/get/{companyCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> viewStockDetails(@PathVariable("companyCode") String companyCode,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate)
			throws JsonProcessingException {

		if (stockExchangeService.isCompanyCodeExists(companyCode)) {
			LOGGER.info("CompanyCode exist in database ");
			CompanyStockDetails companyStockDetails = stockExchangeService.getStocksBetweenDate(startDate, endDate,
					companyCode);
			if (null != companyStockDetails) {
				companyStockDetails.setCompanyCode(companyCode);
				LOGGER.info("API Response is : " + new ObjectMapper().writeValueAsString(companyStockDetails));
				return ResponseEntity.status(HttpStatus.OK).body(companyStockDetails);
			} else {
				LOGGER.info("No Stocks Available For Given Date Range");
				ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,
						"No Stocks Available For Given Date Range", "No Stocks Available For Given Date Range");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
			}
		} else {
			LOGGER.info("Provided CompanyCode doesnot exists in DB");
			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Provided CompanyCode doesnot exists in DB",
					"Company Code is Not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}
	}

	@GetMapping("/latest/{companyCode}")
	@ApiOperation(value = "Fetch Latest Price by Company Code")
	public ResponseEntity<Object> getLatestStockByCompanyCode(@PathVariable("companyCode") String companyCode)
			throws JsonProcessingException {
		if (stockExchangeService.isCompanyCodeExists(companyCode)) {
			StockExchangeDTO stockExchangeDto = stockExchangeService.retrieveLatestStockByCompanyCode(companyCode);
			if (null != stockExchangeDto) {
				LOGGER.info("The Latest Stock info is : " + new ObjectMapper().writeValueAsString(stockExchangeDto));
				StockExchange stockExchangeResp = new StockExchange();
				stockExchangeResp.setCompanyCode(stockExchangeDto.getCompanyCode());
				stockExchangeResp.setPrice(stockExchangeDto.getPrice());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]");
				stockExchangeResp.setStockCreatedDate(stockExchangeDto.getStockCreatedDate().format(formatter));
				LOGGER.info("Response is : " + new ObjectMapper().writeValueAsString(stockExchangeResp));
				return ResponseEntity.status(HttpStatus.OK).body(stockExchangeResp);
			} else {
				LOGGER.info("Failed to get latest stock price from Stock Table");
				ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,
						"Failed to get latest stock price from Stock Table",
						"Failed to get latest stock price from Stock Table");
				LOGGER.info("Response is : " + message);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
			}
		} else {
			LOGGER.info("Provided CompanyCode doesnot exists in DB");
			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Provided CompanyCode doesnot exists in DB",
					"Company Code is Not Valid");
			LOGGER.info("Response is : " + message);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}
	}

	@DeleteMapping("/delete/{companyCode}")
	@ApiOperation(value = "Delete Stock by Company Code")
	public ResponseEntity<Object> deleteStockByCompanyCode(@PathVariable("companyCode") String companyCode)
			throws JsonProcessingException {
		if (stockExchangeService.isCompanyCodeExists(companyCode)) {
			LOGGER.info("CompanyCode exists in Database");
			stockExchangeService.deleteStocks(companyCode);
			return ResponseEntity.status(HttpStatus.OK).body("Success");
		} else {
			LOGGER.info("Provided CompanyCode doesnot exists in DB");
			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, "Provided CompanyCode doesnot exists in DB",
					"Company Code is Not Valid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}
	}

	@PostMapping(value = "/add/new/{companyCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Registering a new Stock for new company")
	public ResponseEntity<Object> addStockPriceForNewCompany(@PathVariable("companyCode") String companyCode,
			@RequestBody @Valid StockExchange stockExchange) throws JsonProcessingException {

		StockExchangeDTO stockExchangeDTO = stockExchangeService.addStockPrice(stockExchange, companyCode);
		if (null != stockExchangeDTO) {
			LOGGER.info("Saved SuccessFully");
			StockExchange stockExchangeResp = new StockExchange();
			stockExchangeResp.setCompanyCode(stockExchangeDTO.getCompanyCode());
			stockExchangeResp.setPrice(stockExchangeDTO.getPrice());
			LOGGER.info("Before Formatting");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]");
			stockExchangeResp.setStockCreatedDate(stockExchangeDTO.getStockCreatedDate().format(formatter));
			LOGGER.info("After Formatting " + stockExchangeResp.getStockCreatedDate());
			LOGGER.info("Added Stock Price successfully : " + new ObjectMapper().writeValueAsString(stockExchangeResp));
			return ResponseEntity.status(HttpStatus.OK).body(stockExchangeResp);
		} else {
			LOGGER.info("Failed to Add the stock price in Stock Table");
			ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,
					"Failed to Add the stock price in Stock Table", "Failed to Add the stock price in Stock Table");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}
	}
}
