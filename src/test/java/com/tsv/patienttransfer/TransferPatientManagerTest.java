package com.tsv.patienttransfer;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tsv.patienttransfer.business.PatientManager;
import com.tsv.patienttransfer.business.TransferPatientManager;
import com.tsv.patienttransfer.business.connectors.FhirServiceConnector;
import com.tsv.patienttransfer.exceptions.ApplicationException;
import com.tsv.patienttransfer.mappers.PatientDtoEntityMapper;
import com.tsv.patienttransfer.persistence.Patient;

public class TransferPatientManagerTest {

	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@InjectMocks
	private TransferPatientManager transferPatientManager;

	@Mock
	private FhirServiceConnector fhirServiceConnector;
	@Mock
	private EntityManager em;
	@Mock
	private PatientDtoEntityMapper patientDtoEntityMapper;
	@Mock
	private PatientManager patientManager;
	@Mock
	private Patient patient;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void transferPatientCallCorrectServices() throws ApplicationException {
		String url = "someValidUrl";
		Mockito.when(patientDtoEntityMapper.toPatientEntity(Matchers.any())).thenReturn(patient);
		Mockito.when(patient.getUrl()).thenReturn(url);
		
		transferPatientManager.transferFhirPatient(url);

		Mockito.verify(fhirServiceConnector).getFhirPatient(url);
		Mockito.verify(patientDtoEntityMapper).toPatientEntity(Matchers.any());
		Mockito.verify(patientManager).getPatientByUrl(url);
		Mockito.verify(em).persist(patient);
	}

}
