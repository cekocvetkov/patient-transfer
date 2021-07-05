package com.tsv.patienttransfer.mappers;

import java.sql.Timestamp;

import javax.ejb.Stateless;

import com.tsv.patienttransfer.dto.PatientDto;
import com.tsv.patienttransfer.rest.models.PatientResponse;

@Stateless
public class PatientResponseMapper {
	
	public PatientResponse toPatientResponse(PatientDto patientDto) {
		return new PatientResponse(patientDto.getUrl(), patientDto.getFamily(), patientDto.getGiven(),
				patientDto.getPrefix(), patientDto.getSuffix(), patientDto.getGender(),
				new Timestamp(patientDto.getBirthDate().getTime()));
	}
	
}
