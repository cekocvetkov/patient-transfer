package com.tsv.patienttransfer.exceptions;

public class BadRequestException extends ApplicationException{
	private static final long serialVersionUID = 3622213869480568060L;

	public BadRequestException(String msg) {
		super(msg);
	}
}