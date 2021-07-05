package com.tsv.patienttransfer.exceptions;

public class ApplicationException extends Exception{
	private static final long serialVersionUID = -4657723457403454350L;
	
	public ApplicationException() {
		super();
	}
	public ApplicationException(String msg) {
		super(msg);
	}
	public ApplicationException(String msg, Throwable e) {
		super(msg, e);
	}
}
