package com.estock.market.company.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estock.market.company.entity.CompanyDTO;

/**
 * Repository class for Company related CRUD Operations
 * 
 *
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<CompanyDTO, Integer> {

	Optional<CompanyDTO> findByCompanyCode(String companyCode);

	@Transactional
	void deleteByCompanyCode(String companyCode);

}
