package com.mt.insurancepolicies.service;

import java.util.List;

import com.mt.insurancepolicies.dto.PolicyDTO;
import com.mt.insurancepolicies.entity.Company;
import com.mt.insurancepolicies.entity.Policy;
import com.mt.insurancepolicies.exceptions.ServiceException;

public interface InsuranceServices {

	public Company addCompany(Company company);
	public PolicyDTO addPolicy(PolicyDTO policy);
	public List<Policy> getAllPolicies(String companyName);
	public void deletePolicy(String policyName);
	public Policy updatePolicy(String policyName,Policy policy);
}
