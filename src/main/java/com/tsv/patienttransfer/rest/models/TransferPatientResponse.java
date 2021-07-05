package com.tsv.patienttransfer.rest.models;

public class TransferPatientResponse {
	String message;
	
	public TransferPatientResponse(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
