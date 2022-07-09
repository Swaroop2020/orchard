package com.mt.insurancepolicies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mt.insurancepolicies.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>{

	@Query("select c from Company c where c.name=?1")
	Company findByName(String name);
}