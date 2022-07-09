package com.mt.insurancepolicies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mt.insurancepolicies.dto.PolicyDTO;
import com.mt.insurancepolicies.entity.Company;
import com.mt.insurancepolicies.entity.Policy;
import com.mt.insurancepolicies.exceptions.InvalidCompanyNameException;
import com.mt.insurancepolicies.exceptions.InvalidPolicyException;
import com.mt.insurancepolicies.exceptions.ServiceException;
import com.mt.insurancepolicies.repositories.CompanyRepository;
import com.mt.insurancepolicies.repositories.PolicyRepository;
import com.mt.insurancepolicies.service.InsuranceServices;

@Service
public class InsuranceServicesImpl implements InsuranceServices{
	
	@Autowired
	private CompanyRepository companyDB;
	@Autowired
	private PolicyRepository policyDB;

	@Override
	public Company addCompany(Company company) {
		return companyDB.save(company);
	}

	@Override
	public PolicyDTO addPolicy(PolicyDTO policyDto) {
		Policy policy=new Policy();
		mapDtoEntity(policyDto,policy);
		Policy savedInsurance=policyDB.save(policy);
		return mapEntityToDto(savedInsurance);
	}
	
	@Override
	public List<Policy> getAllPolicies(String companyName) {
		List<Policy> policies = null;
		try {
			Company company = null;
			company = companyDB.findByName(companyName);
			if (company == null) {
				throw new InvalidCompanyNameException("no such name found");
			}
			policies = company.getPolicies();
			return policies;

		} catch (InvalidCompanyNameException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public void deletePolicy(String policyName) {
		Policy policy = null;
		try {
			policy = policyDB.findByName(policyName);
			if (policy== null) {
				throw new InvalidPolicyException("Such name not Exists");
			} else {
				policyDB.deleteByName(policyName);
			}
		} catch (InvalidPolicyException e) {
			throw new ServiceException(e.getMessage());

		}
	}
	
	@Override
	public Policy updatePolicy(String policyName,Policy policy) {
		Policy policy1 = null;
		policy1 = policyDB.findByName(policyName);
		try {
		if(policy1==null) {
			throw new InvalidPolicyException("such name not exists");
		}
		policy1.setPeriod(policy.getPeriod());
		policy1.setPremium(policy.getPremium());
		 return policyDB.save(policy1);
		}
		 catch(InvalidPolicyException e) {
			 throw new ServiceException(e.getMessage());
		 }

	}
	
	private PolicyDTO mapEntityToDto(Policy policy) {
		// TODO Auto-generated method stub
		PolicyDTO responseDto=new PolicyDTO();
		responseDto.setName(policy.getName());
		responseDto.setId(policy.getId());
		responseDto.setPeriod(policy.getPeriod());
		responseDto.setPremium(policy.getPremium());
		responseDto.setCompanies(policy.getCompanies().stream().map(Company::getName).collect(Collectors.toList()));
		return responseDto;
	}
	
	private void mapDtoEntity(PolicyDTO policyDto, Policy policy) {
		// TODO Auto-generated method stub
		policy.setName(policyDto.getName());
		policy.setId(policyDto.getId());
		policy.setPeriod(policyDto.getPeriod());
		policy.setPremium(policyDto.getPremium());
		
		if(null==policy.getCompanies()) {
			policy.setCompanies(new ArrayList<>());
		}
		policyDto.getCompanies().stream().forEach(companyName->{
			Company company=companyDB.findByName(companyName);
			if(null==company) {
				company=new Company();
				company.setPolicies(new ArrayList<>());
			}
			company.setName(companyName);
			policy.addCompany(company);
		});
		
	}
	
	
}
