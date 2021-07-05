package com.tsv.patienttransfer.persistence;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;

@NamedQuery(name = "Patient.findByUrl", query = "SELECT p FROM Patient p WHERE p.url = :url")
@Entity
public class Patient {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int patientId;

	private String url;
	private String family;
	private String given;
	private String prefix;
	private String suffix;
	private String gender;
	private Timestamp birthDate;
	private Timestamp creationTime;

	public Patient() {
		super();
	}

	public Patient(String url, String family, String given, String prefix, String suffix, String gender,
			Timestamp birthDate) {
		super();
		this.url = url;
		this.family = family;
		this.given = given;
		this.prefix = prefix;
		this.suffix = suffix;
		this.gender = gender;
		this.birthDate = birthDate;
	}

	public int getPatientId() {
		return patientId;
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

	public Timestamp getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	@PrePersist
	public void updateCreationTime() {
		setCreationTime(new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", url=" + url + ", family=" + family + ", given=" + given
				+ ", prefix=" + prefix + ", suffix=" + suffix + ", gender=" + gender + ", birthDate=" + birthDate
				+ ", creationTime=" + creationTime + "]";
	}

}
