package com.estock.market.stock;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.estock.market.stock.entity.StockExchangeDTO;
import com.estock.market.stock.repository.StockExchangeRepository;
import com.estock.market.stock.service.impl.StockExchangeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceImplTest {

	@Mock
	private StockExchangeRepository stockExchangeRepository;

	@InjectMocks
	private StockExchangeServiceImpl stockExchangeService;

	@Test
	public void testIsCompanyCodeExists() throws JsonProcessingException {
		String companyCode = "testCode1";
		StockExchangeDTO stockExchangeDTO = new StockExchangeDTO();
		stockExchangeDTO.setPrice(100.45f);
		stockExchangeDTO.setCompanyCode(companyCode);
		List<StockExchangeDTO> stockExchanges = new ArrayList<StockExchangeDTO>();
		stockExchanges.add(stockExchangeDTO);

		Mockito.when(stockExchangeRepository.findByCompanyCode(companyCode)).thenReturn(stockExchanges);
		Boolean actualFlag = stockExchangeService.isCompanyCodeExists(companyCode);
	    assertEquals(actualFlag, true);
	}
	
	@Test
	public void testRetrieveLatestStockByCompanyCode() {
		String companyCode = "testCode1";
		StockExchangeDTO stockExchangeDTO1 = new StockExchangeDTO();
		stockExchangeDTO1.setPrice(100.45f);
		stockExchangeDTO1.setCompanyCode(companyCode);
		stockExchangeDTO1.setStockCreatedDate(LocalDateTime.now());
		StockExchangeDTO stockExchangeDTO2 = new StockExchangeDTO();
		stockExchangeDTO2.setPrice(100.45f);
		stockExchangeDTO2.setCompanyCode(companyCode);
		stockExchangeDTO2.setStockCreatedDate(LocalDateTime.now());
		List<StockExchangeDTO> stockExchanges = new ArrayList<StockExchangeDTO>();
		stockExchanges.add(stockExchangeDTO1);
		stockExchanges.add(stockExchangeDTO2);
		
		Mockito.when(stockExchangeRepository.findByCompanyCode(companyCode)).thenReturn(stockExchanges);
		StockExchangeDTO actualStockExchangeDTO = stockExchangeService.retrieveLatestStockByCompanyCode(companyCode);
		assertEquals(actualStockExchangeDTO.getCompanyCode(), companyCode);
	}
}
