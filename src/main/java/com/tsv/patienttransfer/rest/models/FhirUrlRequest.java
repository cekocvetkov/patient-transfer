package com.tsv.patienttransfer.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FhirUrlRequest {
	
	String fhirUrl;

	public String getFhirUrl() {
		return fhirUrl;
	}

	public void setFhirUrl(String fhirUrl) {
		this.fhirUrl = fhirUrl;
	}

	@Override
	public String toString() {
		return "FhirParam [fhirUrl=" + fhirUrl + "]";
	}
}
