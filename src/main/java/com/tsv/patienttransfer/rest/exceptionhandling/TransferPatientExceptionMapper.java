package com.tsv.patienttransfer.rest.exceptionhandling;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.tsv.patienttransfer.exceptions.BadRequestException;
import com.tsv.patienttransfer.exceptions.NotFoundException;
import com.tsv.patienttransfer.exceptions.PatientAlreadyTransferredException;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class TransferPatientExceptionMapper implements ExceptionMapper<Exception>{

    @Override
    public Response toResponse(Exception exception) {
    	ErrorResponse errorResponse = new ErrorResponse(getErrorStatus(exception), exception.getMessage());
        return Response.status(errorResponse.getStatus()).entity(errorResponse).build();
    }

	private Status getErrorStatus(Exception exception) {
		if(exception instanceof NotFoundException)
			return Status.NOT_FOUND;
		if(exception instanceof PatientAlreadyTransferredException)
			return Status.CONFLICT;
		if(exception instanceof BadRequestException)
			return Status.BAD_REQUEST;
		
		return Status.INTERNAL_SERVER_ERROR;
	}

}