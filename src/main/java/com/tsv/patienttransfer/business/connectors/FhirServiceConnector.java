package com.tsv.patienttransfer.business.connectors;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsv.patienttransfer.business.connectors.models.FhirDataModel;
import com.tsv.patienttransfer.business.connectors.models.FhirDataName;
import com.tsv.patienttransfer.dto.PatientDto;
import com.tsv.patienttransfer.exceptions.ApplicationException;
import com.tsv.patienttransfer.exceptions.NotFoundException;

@Singleton
public class FhirServiceConnector {
	static Logger log = Logger.getLogger(FhirServiceConnector.class);

	private Client client;

	@PostConstruct
	public void initClient() {
		client = ClientBuilder.newClient();
	}

	public PatientDto getFhirPatient(String url) throws ApplicationException {
		JsonObject fhirResponse = callUrlAndgetJsonResponse(url);

		PatientDto patientDto = getPatientDtoFromJson(fhirResponse);
		patientDto.setUrl(url);

		return patientDto;
	}

	private JsonObject callUrlAndgetJsonResponse(String url) throws ApplicationException {
		WebTarget target = client.target(url);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		validateFhirServiceResponse(response);
		JsonObject jsonResponse = response.readEntity(JsonObject.class);
		
		return jsonResponse;
	}

	private void validateFhirServiceResponse(Response response) throws NotFoundException, ApplicationException {
		StatusType status = response.getStatusInfo();
		System.out.println(status);
		if(status != Status.OK) {
			if(status == Status.NOT_FOUND) {
				throw new NotFoundException("Fhir Patient Data Not Found");
			} else {
				throw new ApplicationException("Error on getting fhir data");
			}
		}
	}

	private PatientDto getPatientDtoFromJson(JsonObject response) throws ApplicationException {
		FhirDataModel fhirDataModel = mapJsonToFhirData(response);

		FhirDataName name = fhirDataModel.getName()[0];

		PatientDto patientDto = new PatientDto(null, name.getFamily(),
				String.join(",", fhirDataModel.getName()[0].getGiven()), name.getPrefix(), name.getSuffix(),
				fhirDataModel.getGender(), fhirDataModel.getBirthDate());
		return patientDto;
	}

	private FhirDataModel mapJsonToFhirData(JsonObject response) throws ApplicationException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		FhirDataModel fhirDataModel;
		try {
			fhirDataModel = objectMapper.readValue(response.toString(), FhirDataModel.class);
		} catch (JsonProcessingException e) {
			log.info(e);
			throw new ApplicationException("Error on processing fhir data");
		}

		if (fhirDataModel.getName().length > 1) {
			throw new ApplicationException("Error processing fhir data: Patient has more than one name");
		}
		return fhirDataModel;
	}
}
