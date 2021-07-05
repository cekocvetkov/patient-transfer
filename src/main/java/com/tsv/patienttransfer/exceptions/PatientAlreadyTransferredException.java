package com.tsv.patienttransfer.exceptions;

public class PatientAlreadyTransferredException extends ApplicationException{
	private static final long serialVersionUID = 7717439611246019039L;

	public PatientAlreadyTransferredException(String msg) {
		super(msg);
	}
}