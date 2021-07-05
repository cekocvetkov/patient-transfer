package com.tsv.patienttransfer.business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.tsv.patienttransfer.dto.PatientDto;
import com.tsv.patienttransfer.exceptions.NotFoundException;
import com.tsv.patienttransfer.mappers.PatientDtoEntityMapper;
import com.tsv.patienttransfer.persistence.Patient;

@Stateless
public class PatientManager {
	
	private static Logger log = Logger.getLogger(PatientManager.class);

	@PersistenceContext(unitName = "transferPatientDS")
	private EntityManager em;

	@Inject
	private PatientDtoEntityMapper patientDtoEntityMapper;
	
	public PatientDto getPatient(String url) throws NotFoundException {
		Patient patient = getPatientByUrl(url);

		if (patient == null)
			throw new NotFoundException("Couldn't find patient for url: " + url);

		return patientDtoEntityMapper.toPatientDto(patient);
	}

	public Patient getPatientById(int patientId) throws NotFoundException {
		Patient patient = em.find(Patient.class, patientId);

		if (patient == null)
			throw new NotFoundException("Patient with id: " + patientId + "not found");

		return patient;
	}

	public Patient getPatientByUrl(String url) {
		TypedQuery<Patient> query = em.createNamedQuery("Patient.findByUrl", Patient.class);
		query.setParameter("url", url);
		try {
			Patient patientEntity = query.getSingleResult();
			return patientEntity;
		} catch (NoResultException e) {
			log.debug("Could not find patient for url: " + url, e);
			return null;
		}
	}
}
