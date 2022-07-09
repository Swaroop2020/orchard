package com.mt.insurancepolicies.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mt.insurancepolicies.dto.ServiceResponse;
import com.mt.insurancepolicies.dto.PolicyDTO;
import com.mt.insurancepolicies.entity.Company;
import com.mt.insurancepolicies.entity.Policy;
import com.mt.insurancepolicies.exceptions.ControllerException;
import com.mt.insurancepolicies.exceptions.ServiceException;
import com.mt.insurancepolicies.service.InsuranceServices;

@RestController
public class PolicyController {
	
	@Autowired
	private InsuranceServices service;
	
	@PostMapping(path="/addCompany")
	public ResponseEntity<Object> addCompany(@Valid @RequestBody Company company){
		service.addCompany(company);
		ServiceResponse<Company> response = new ServiceResponse<Company>("success",company);
		return new ResponseEntity<Object>(response,HttpStatus.CREATED);
	}
	
	@PostMapping(path="/addPolicies")
	public ResponseEntity<Object> addPolicy(@RequestBody PolicyDTO policyDto){
		service.addPolicy(policyDto);
		ServiceResponse<PolicyDTO> response = new ServiceResponse<PolicyDTO>("success",policyDto);
		return new ResponseEntity<Object>(response,HttpStatus.CREATED);
	}
	
	@GetMapping(path="/company/policy/{companyName}")
	 public ResponseEntity<List<Policy>> getInsurances(@PathVariable(value="companyName") String companyName){
		 try {
			return new ResponseEntity<>(service.getAllPolicies(companyName),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new ControllerException(e.getMessage());
		}
		 
	 }
	
	@DeleteMapping(path = "/insurance/{policyName}")
	public ResponseEntity<String> deleteInsurance(@PathVariable(value = "policyName") String policyName) {
		try {
			service.deletePolicy(policyName);
			return new ResponseEntity<>("insurance with insuranceName : " + policyName + " deleted successfully", HttpStatus.OK);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@PutMapping(path="/updatePolicy/{policyName}")
	 public ResponseEntity<Policy> updateDirector(@PathVariable(value="policyName") String policyName,@RequestBody Policy policy){
		 try {
			return new ResponseEntity<>(service.updatePolicy(policyName, policy),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new ControllerException(e.getMessage());
			
		}
	 }
	
}



