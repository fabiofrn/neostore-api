package com.fabio.neostoreapi.exception.handler;

import java.util.Arrays;

import com.fabio.neostoreapi.exception.EmptyResultDataAccessException;
import com.fabio.neostoreapi.exception.handler.model.Error;
import com.fabio.neostoreapi.generic.model.GenericResponse;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmptyResultDataAccessExceptionMapper implements ExceptionMapper<EmptyResultDataAccessException> {
	
	@Override
	public Response toResponse(EmptyResultDataAccessException exception) {
       
        GenericResponse genericResponse = new GenericResponse(Arrays.asList(new Error(exception.getMessage())));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(genericResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
	}

}
