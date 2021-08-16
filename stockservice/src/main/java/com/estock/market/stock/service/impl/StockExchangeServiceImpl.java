package com.estock.market.stock.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.estock.market.stock.entity.CompanyStockDetails;
import com.estock.market.stock.entity.StockExchangeDTO;
import com.estock.market.stock.model.StockExchange;
import com.estock.market.stock.repository.StockExchangeRepository;
import com.estock.market.stock.service.StockExchangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This Class is the Service Class for StockExchange related info.
 */
@Service
public class StockExchangeServiceImpl implements StockExchangeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StockExchangeServiceImpl.class);

	@Autowired
	private StockExchangeRepository stockExchangeRepository;

	@Override
	public boolean isCompanyCodeExists(String companyCode) throws JsonProcessingException {
		List<StockExchangeDTO> stockExchanges = stockExchangeRepository.findByCompanyCode(companyCode);
		LOGGER.info("StockExchange List from database " + new ObjectMapper().writeValueAsString(stockExchanges));
		return (!CollectionUtils.isEmpty(stockExchanges)) ? true : false;
	}

	@Override
	public StockExchangeDTO addStockPrice(StockExchange stockExchange, String companyCode)
			throws JsonProcessingException {
		stockExchange.setCompanyCode(companyCode);
		StockExchangeDTO stockExchangeDto = convertToDto(stockExchange);
		LOGGER.info("After Converting to DTO " + new ObjectMapper().writeValueAsString(stockExchangeDto));
		return stockExchangeRepository.save(stockExchangeDto);
	}

	private StockExchangeDTO convertToDto(StockExchange stockExchange) {
		StockExchangeDTO stockExchangeDto = new StockExchangeDTO();
		stockExchangeDto.setPrice(stockExchange.getPrice());
		stockExchangeDto.setCompanyCode(stockExchange.getCompanyCode());
		stockExchangeDto.setStockCreatedDate(LocalDateTime.now());
		return stockExchangeDto;
	}

	@Override
	public CompanyStockDetails getStocksBetweenDate(String startDate, String endDate, String companyCode)
			throws JsonProcessingException {
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime start_date = LocalDate.parse(startDate, dtFormat).atStartOfDay();
		LocalDateTime end_date = LocalDate.parse(endDate, dtFormat).atStartOfDay();

		List<StockExchangeDTO> stockExchangeList = stockExchangeRepository.findByStockCreatedDateBetween(start_date,
				end_date);

		LOGGER.info(
				"List of Stocks For Given date range : " + new ObjectMapper().writeValueAsString(stockExchangeList));

		if (!CollectionUtils.isEmpty(stockExchangeList)) {
			LOGGER.info("Filtering Stocks by CompanyCode : " + companyCode);
			List<StockExchangeDTO> companyStockList = stockExchangeList.stream()
					.filter(stock -> stock.getCompanyCode().equalsIgnoreCase(companyCode)).collect(Collectors.toList());
			LOGGER.info("After filtering , Stock List is : " + new ObjectMapper().writeValueAsString(companyStockList));
			LOGGER.info("processing StockList to retrieve max, min and average stock price ");
			Float maxStockPrice = null;
			Float minStockprice = null;

			Optional<StockExchangeDTO> maxSp = companyStockList.stream()
					.max(Comparator.comparing(StockExchangeDTO::getPrice));
			if (maxSp.isPresent()) {
				maxStockPrice = maxSp.get().getPrice();
			}

			Optional<StockExchangeDTO> minSp = companyStockList.stream()
					.min(Comparator.comparing(StockExchangeDTO::getPrice));
			if (minSp.isPresent()) {
				minStockprice = minSp.get().getPrice();
			}
			Double averageStockPrice = companyStockList.stream().mapToDouble(stock -> stock.getPrice().doubleValue())
					.average().getAsDouble();

			LOGGER.info("Max Price : " + maxStockPrice);
			LOGGER.info("Min Price : " + minStockprice);
			LOGGER.info("Average Price : " + averageStockPrice);
			CompanyStockDetails compStockDtls = new CompanyStockDetails();
			compStockDtls.setMaxStockPrice(maxStockPrice);
			compStockDtls.setMinStockPrice(minStockprice);
			compStockDtls.setAverageStockPrice(averageStockPrice);
			compStockDtls.setStockExchanges(companyStockList);
			return compStockDtls;
		}
		return null;
	}

	@Override
	public StockExchangeDTO retrieveLatestStockByCompanyCode(String companyCode) {
		List<StockExchangeDTO> stockExchanges = stockExchangeRepository.findByCompanyCode(companyCode);
		if (!CollectionUtils.isEmpty(stockExchanges)) {
			Optional<StockExchangeDTO> latestStock = stockExchanges.stream()
					.max(Comparator.comparing(StockExchangeDTO::getStockCreatedDate));
			if (latestStock.isPresent()) {
				return latestStock.get();
			}
		}
		return null;
	}

	@Override
	public void deleteStocks(String companyCode) {
		stockExchangeRepository.deleteByCompanyCode(companyCode);
	}
}
