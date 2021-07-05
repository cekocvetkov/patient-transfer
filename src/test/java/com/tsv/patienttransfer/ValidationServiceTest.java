package com.tsv.patienttransfer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.tsv.patienttransfer.business.ValidationService;
import com.tsv.patienttransfer.exceptions.BadRequestException;
import com.tsv.patienttransfer.rest.models.FhirUrlRequest;

public class ValidationServiceTest {
	private static final String VALID_URL = "http://hapi.fhir.org/baseR4/Patient/618641";

	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@InjectMocks
	private ValidationService validationService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validateFhirRequestOk() throws BadRequestException {
		FhirUrlRequest fhirUrlRequest = new FhirUrlRequest();
		fhirUrlRequest.setFhirUrl(VALID_URL);

		validationService.validateFhirUrlRequest(fhirUrlRequest);
	}

	@Test
	public void validateRequestUrlNull() throws BadRequestException {
		FhirUrlRequest fhirUrlRequest = new FhirUrlRequest();

		exceptionRule.expect(BadRequestException.class);
		exceptionRule.expectMessage("Request Body must contain a valid fhirUrl parameter");
		
		validationService.validateFhirUrlRequest(fhirUrlRequest);
	}

	@Test
	public void validateRequestUrlNotValid() throws BadRequestException {
		FhirUrlRequest fhirUrlRequest = new FhirUrlRequest();
		fhirUrlRequest.setFhirUrl("invalidUrl%");
		
		exceptionRule.expect(BadRequestException.class);
		exceptionRule.expectMessage("Not a valid Url");
		
		validationService.validateFhirUrlRequest(fhirUrlRequest);
	}
}
