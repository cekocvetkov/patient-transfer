package com.tsv.patienttransfer.business;

import java.util.regex.Pattern;

import javax.ejb.Stateless;

import com.tsv.patienttransfer.exceptions.BadRequestException;
import com.tsv.patienttransfer.rest.models.FhirUrlRequest;

@Stateless
public class ValidationService {
	private final static String URL_REGEX = "(https?:\\/\\/)?([\\w\\-])+\\.{1}([a-zA-Z]{2,63})([\\/\\w-]*)*\\/?\\??([^#\\n\\r]*)?#?([^\\n\\r]*)";
	
	public void validateFhirParam(FhirUrlRequest fhirParam) throws BadRequestException {
		if(fhirParam.getFhirUrl() == null)
			throw new BadRequestException("Request Body must contain a valid fhirUrl parameter");
		
		if(!Pattern.matches(URL_REGEX, fhirParam.getFhirUrl()))
			throw new BadRequestException("Not a valid Url");
	}
}
