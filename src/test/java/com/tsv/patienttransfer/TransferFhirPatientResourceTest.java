package com.tsv.patienttransfer;


import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tsv.patienttransfer.business.TransferPatientManager;
import com.tsv.patienttransfer.business.ValidationService;
import com.tsv.patienttransfer.exceptions.ApplicationException;
import com.tsv.patienttransfer.rest.TransferPatientController;
import com.tsv.patienttransfer.rest.models.FhirUrlRequest;
import com.tsv.patienttransfer.rest.models.TransferPatientResponse;

public class TransferFhirPatientResourceTest {
	
	@InjectMocks
	private TransferPatientController transferPatientController;
	
	@Mock
	private ValidationService validationService;
	@Mock
	private TransferPatientManager transferPatientManager;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void transferPatientOkResponse() throws ApplicationException {
		FhirUrlRequest request = new FhirUrlRequest();
		request.setFhirUrl("someOkUrl");
		Mockito.doNothing().when(validationService).validateFhirUrlRequest(request);
		Mockito.doNothing().when(transferPatientManager).transferFhirPatient(request.getFhirUrl());
		
		Response response = transferPatientController.transferFhirPatient(request);
		
		assertEquals(Status.CREATED, response.getStatusInfo());
		assertEquals("Successfully transferred patient from url: "+request.getFhirUrl(), ((TransferPatientResponse)response.getEntity()).getMessage());
	}
	
	@Test
	public void transferPatientCallsCorrectServices() throws ApplicationException {
		FhirUrlRequest request = new FhirUrlRequest();
		request.setFhirUrl("someOkUrl");
		
		transferPatientController.transferFhirPatient(request);
		
		Mockito.verify(validationService).validateFhirUrlRequest(request);
		Mockito.verify(transferPatientManager).transferFhirPatient(request.getFhirUrl());
	}
}
