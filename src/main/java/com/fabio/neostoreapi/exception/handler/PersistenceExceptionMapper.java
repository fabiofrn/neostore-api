package com.fabio.neostoreapi.exception.handler;

import java.util.Arrays;

import com.fabio.neostoreapi.exception.PersistenceException;
import com.fabio.neostoreapi.exception.handler.model.Error;
import com.fabio.neostoreapi.generic.model.GenericResponse;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

	@Override
	public Response toResponse(PersistenceException exception) {

		GenericResponse genericResponse = new GenericResponse(
				Arrays.asList(new Error(exception.getMessage(), exception.getInternalMessage())));
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(genericResponse)
				.type(MediaType.APPLICATION_JSON).build();
	}

}
