package com.tsv.patienttransfer;


import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tsv.patienttransfer.business.PatientManager;
import com.tsv.patienttransfer.business.ValidationService;
import com.tsv.patienttransfer.dto.PatientDto;
import com.tsv.patienttransfer.exceptions.ApplicationException;
import com.tsv.patienttransfer.mappers.PatientResponseMapper;
import com.tsv.patienttransfer.rest.TransferPatientController;
import com.tsv.patienttransfer.rest.models.FhirUrlRequest;
import com.tsv.patienttransfer.rest.models.PatientResponse;

public class TransferedPatientResourceTest {
	
	@InjectMocks
	private TransferPatientController transferPatientController;
	
	@Mock
	private ValidationService validationService;
	@Mock
	private PatientManager patientManager;
	@Mock
	private PatientResponseMapper patientResponseMapper;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void transferedPatientOkResponse() throws ApplicationException {
		FhirUrlRequest request = new FhirUrlRequest();
		request.setFhirUrl("someOkUrl");
		PatientResponse patientResponse = new PatientResponse();
		patientResponse.setUrl("someOkUrl");
		Mockito.doNothing().when(validationService).validateFhirUrlRequest(request);
		Mockito.doReturn(patientResponse).when(patientResponseMapper).toPatientResponse(Matchers.any());
		Mockito.doReturn(new PatientDto()).when(patientManager).getPatient(request.getFhirUrl());
		
		Response response = transferPatientController.transferedPatient(request);
		
		assertEquals(response.getStatusInfo(), Status.OK);
		assertEquals(response.getEntity(), patientResponse);
	}
	
	@Test
	public void transferedPatientCallsCorrectServices() throws ApplicationException {
		FhirUrlRequest request = new FhirUrlRequest();
		request.setFhirUrl("someOkUrl");
		PatientResponse patientResponse = new PatientResponse();
		patientResponse.setUrl("someOkUrl");
		
		transferPatientController.transferedPatient(request);
		
		Mockito.verify(validationService).validateFhirUrlRequest(request);
		Mockito.verify(patientResponseMapper).toPatientResponse(Matchers.any());
		Mockito.verify(patientManager).getPatient(request.getFhirUrl());
	}
}
