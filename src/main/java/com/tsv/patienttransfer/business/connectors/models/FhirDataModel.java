package com.tsv.patienttransfer.business.connectors.models;

import java.util.Arrays;
import java.util.Date;

public class FhirDataModel {
	private String fullUrl;
	private FhirDataName[] name;
	private String gender;
	private Date birthDate;

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public FhirDataName[] getName() {
		return name;
	}

	public void setName(FhirDataName[] name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "FhirDataModel [fullUrl=" + fullUrl + ", name=" + Arrays.toString(name) + ", gender=" + gender
				+ ", birthDate=" + birthDate + "]";
	}

}
