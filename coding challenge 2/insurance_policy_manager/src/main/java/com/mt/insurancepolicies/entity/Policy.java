package com.mt.insurancepolicies.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Policy {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@Min(12)
	private int period;
	@Min(100000)
	private double premium;
	@ManyToMany(targetEntity = Company.class, mappedBy = "policies", cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JsonIgnore
	private List<Company> companies;
	
	public Policy() {
		super();
	}

	public Policy(int id, String name, int period, double premium, List<Company> companies) {
		super();
		this.id = id;
		this.name = name;
		this.period = period;
		this.premium = premium;
		this.companies = companies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public List<Company> getCompanies() {
		return companies;
	}
	
	public void addCompany(Company company) {
		// TODO Auto-generated method stub
		this.companies.add(company);
		company.getPolicies().add(this);

	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}	

}
