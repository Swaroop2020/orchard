package com.mt.insurancepolicies.dto;

import java.util.List;;

public class PolicyDTO {
	
	private int id;
	private String name;
	private double premium;
	private int period;
	private List<String> companies;
	
	public PolicyDTO() {
		super();
	}

	public PolicyDTO(int id, String name, double premium, int period, List<String> companies) {
		super();
		this.id = id;
		this.name = name;
		this.premium = premium;
		this.period = period;
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

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public List<String> getCompanies() {
		return companies;
	}

	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}

	@Override
	public String toString() {
		return "PolicyDTO [id=" + id + ", name=" + name + ", premium=" + premium + ", period=" + period + ", companies="
				+ companies + "]";
	}

}
