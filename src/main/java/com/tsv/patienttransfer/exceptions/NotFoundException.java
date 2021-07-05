package com.tsv.patienttransfer.exceptions;

public class NotFoundException extends ApplicationException{
	private static final long serialVersionUID = 4798703065471661252L;

	public NotFoundException(String msg) {
		super(msg);
	}
}
