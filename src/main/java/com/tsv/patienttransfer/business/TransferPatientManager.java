package com.tsv.patienttransfer.business;

import java.sql.Timestamp;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.tsv.patienttransfer.business.connectors.FhirServiceConnector;
import com.tsv.patienttransfer.dto.PatientDto;
import com.tsv.patienttransfer.exceptions.ApplicationException;
import com.tsv.patienttransfer.exceptions.PatientAlreadyTransferredException;
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

	public void transferFhirPatient(String url) throws ApplicationException {
		PatientDto patientDto = fhirServiceConnector.getFhirPatient(url);

		Patient patient = new Patient(patientDto.getUrl(), patientDto.getFamily(), patientDto.getGiven(),
				patientDto.getPrefix(), patientDto.getSuffix(), patientDto.getGender(),
				new Timestamp(patientDto.getBirthDate().getTime()));

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
