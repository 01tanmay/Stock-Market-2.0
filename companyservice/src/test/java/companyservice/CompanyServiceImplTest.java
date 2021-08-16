package companyservice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.estock.market.company.entity.CompanyDTO;
import com.estock.market.company.repository.CompanyRepository;
import com.estock.market.company.service.impl.CompanyServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceImplTest {

	@Mock
	private CompanyRepository companyRepository;

	@InjectMocks
	private CompanyServiceImpl companyService;

	@Mock
	private RestTemplate restTemplate;

	@Test
	public void testRetrieveCompanyByCode() {

		CompanyDTO expectedCompanyDto = new CompanyDTO();
		expectedCompanyDto.setCompanyCode("testCode1");
		expectedCompanyDto.setCompanyName("testName1");
		Optional<CompanyDTO> compDto = Optional.of(expectedCompanyDto);

		String companyCode = "testCode1";
		Mockito.when(companyRepository.findByCompanyCode(companyCode)).thenReturn(compDto);
		CompanyDTO actualCompanyDto = companyService.retrieveCompanyByCode(companyCode);
		assertEquals(actualCompanyDto.getCompanyCode(), expectedCompanyDto.getCompanyCode());
	}

	@Test
	public void testRetrieveAllCompanyInfo() {

		CompanyDTO companyDto = new CompanyDTO();
		companyDto.setCompanyCeo("testCode1");
		companyDto.setCompanyCode("testCode1");
		companyDto.setCompanyName("testName1");
		companyDto.setCompanyTurnOver(100.50f);
		companyDto.setCompanyWebsite("www.test1.com");

		List<CompanyDTO> allCompanyDetails = new ArrayList<>();
		allCompanyDetails.add(companyDto);
		Mockito.when(companyRepository.findAll()).thenReturn(allCompanyDetails);

		List<CompanyDTO> actualCompanyDetails = companyService.retriveAllCompanyInfo();
		assertEquals(actualCompanyDetails.size(), 1);
		assertEquals(actualCompanyDetails.get(0).getCompanyCode(), "testCode1");
	}

	@Test
	public void testDeleteStockByCompanyCode() {

		String baseUrl = "https://cpztbgiam2.execute-api.ap-south-1.amazonaws.com/prod/" + "testCode1";
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body("success");

		Mockito.when(restTemplate.exchange(baseUrl, HttpMethod.DELETE, getHeaderRequestEntity(), String.class))
				.thenReturn(response);
		String companyCode = "testCode1";
		Boolean actualResponse = companyService.deleteStockByCompanyCode(companyCode);

		assertEquals(actualResponse, true);
	}

	private static HttpEntity<Object> getHeaderRequestEntity() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<Object>(httpHeaders);
	}
}
