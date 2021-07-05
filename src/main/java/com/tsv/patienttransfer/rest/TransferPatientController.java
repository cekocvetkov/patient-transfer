package com.tsv.patienttransfer.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.tsv.patienttransfer.business.PatientManager;
import com.tsv.patienttransfer.business.TransferPatientManager;
import com.tsv.patienttransfer.business.ValidationService;
import com.tsv.patienttransfer.exceptions.ApplicationException;
import com.tsv.patienttransfer.mappers.PatientResponseMapper;
import com.tsv.patienttransfer.rest.models.FhirUrlRequest;
import com.tsv.patienttransfer.rest.models.PatientResponse;
import com.tsv.patienttransfer.rest.models.TransferPatientResponse;


@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransferPatientController{
	
	@Inject
	private TransferPatientManager transferPatientService;
	
	@Inject
	private PatientManager patientManager;
	
	@Inject
	private ValidationService validationService;
	
	@Inject
	private PatientResponseMapper patientResponseMapper;
	
    @GET
    @Path("/transferedPatient")
    public Response transferedPatient(FhirUrlRequest request) throws ApplicationException {
    	validationService.validateFhirParam(request);
    	
    	PatientResponse patient = patientResponseMapper.toPatientResponse(patientManager.getPatient(request.getFhirUrl())); 
    	
        return Response.ok().entity(patient).build();
    }
    
    @POST
    @Path("/transferFhirPatient")
    public Response transferFhirPatient(FhirUrlRequest request) throws ApplicationException {
    	validationService.validateFhirParam(request);
    	
    	transferPatientService.transferFhirPatient(request.getFhirUrl());
    	
        return Response.status(Status.CREATED).entity(new TransferPatientResponse("Successfully transferred patient from url: "+request.getFhirUrl())).build();
    }

}