package com.tsv.patienttransfer.dto;

import java.util.Date;

public class PatientDto {
	private String url;
	private String family;
	private String given;
	private String prefix;
	private String suffix;
	private String gender;
	private Date birthDate;

	public PatientDto() {
	}

	public PatientDto(String url, String family, String given, String prefix, String suffix, String gender, Date birthDate) {
		this.url = url;
		this.family = family;
		this.given = given;
		this.prefix = prefix;
		this.suffix = suffix;
		this.gender = gender;
		this.birthDate = birthDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getGiven() {
		return given;
	}

	public void setGiven(String given) {
		this.given = given;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
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
		return "PatientDto [url=" + url + ", family=" + family + ", given=" + given + ", prefix=" + prefix + ", suffix="
				+ suffix + ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}

}
