package com.tsv.patienttransfer.business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.tsv.patienttransfer.business.connectors.FhirServiceConnector;
import com.tsv.patienttransfer.dto.PatientDto;
import com.tsv.patienttransfer.exceptions.ApplicationException;
import com.tsv.patienttransfer.exceptions.PatientAlreadyTransferredException;
import com.tsv.patienttransfer.mappers.PatientDtoEntityMapper;
import com.tsv.patienttransfer.persistence.Patient;

@Stateless
public class TransferPatientManager {
	static Logger log = Logger.getLogger(TransferPatientManager.class);

	@PersistenceContext(unitName = "transferPatientDS")
	EntityManager em;

	@Inject
	private PatientManager patientManager;

	@Inject
	private FhirServiceConnector fhirServiceConnector;

	@Inject
	private PatientDtoEntityMapper patientDtoEntityMapper;
	
	public void transferFhirPatient(String url) throws ApplicationException {
		PatientDto patientDto = fhirServiceConnector.getFhirPatient(url);

		Patient patient = patientDtoEntityMapper.toPatientEntity(patientDto);

		if (isPatientAlreadyTransferred(patient)) {
			throw new PatientAlreadyTransferredException("Patient for url: " + url + " already transferred");
		} else {
			em.persist(patient);
		}
	}

	private boolean isPatientAlreadyTransferred(Patient patient) {
		//Eventually make a Patient Cache
		Patient patientFromDb = patientManager.getPatientByUrl(patient.getUrl());
		return patientFromDb != null ? true : false;
	}
}
