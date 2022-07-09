package com.mt.insurancepolicies.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mt.insurancepolicies.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy,Integer>{
	@Query("select p from Policy p where p.name=?1")
	Policy findByName(String name);
	
	@Modifying
	@Transactional
	@Query("delete from Policy where name=?1")
	void deleteByName(String name);
}
