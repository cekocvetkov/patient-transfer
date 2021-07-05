package com.tsv.patienttransfer.mappers;

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.Stateless;

import com.tsv.patienttransfer.dto.PatientDto;
import com.tsv.patienttransfer.persistence.Patient;

@Stateless
public class PatientDtoEntityMapper {

	public PatientDto toPatientDto(Patient patientEntity) {
		return new PatientDto(patientEntity.getUrl(), patientEntity.getFamily(), patientEntity.getGiven(), patientEntity.getPrefix(),
				patientEntity.getSuffix(), patientEntity.getGender(), new Date(patientEntity.getBirthDate().getTime()));
	}
	
	public Patient toPatientEntity(PatientDto patientDto) {
		return new Patient(patientDto.getUrl(), patientDto.getFamily(), patientDto.getGiven(),
				patientDto.getPrefix(), patientDto.getSuffix(), patientDto.getGender(),
				new Timestamp(patientDto.getBirthDate().getTime()));
	}
	
}
