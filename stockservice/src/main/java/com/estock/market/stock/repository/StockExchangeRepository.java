package com.estock.market.stock.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estock.market.stock.entity.StockExchangeDTO;

/**
 * This Class is the Repository class for StockExchange related CRUD Opertations
 * 
 *
 *
 */
@Repository
public interface StockExchangeRepository extends MongoRepository<StockExchangeDTO, String> {

	List<StockExchangeDTO> findByCompanyCode(String companycode);
	List<StockExchangeDTO> findByStockCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	void deleteByCompanyCode(String companyCode);
}
