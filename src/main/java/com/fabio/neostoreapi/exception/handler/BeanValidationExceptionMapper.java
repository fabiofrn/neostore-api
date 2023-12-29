package com.fabio.neostoreapi.exception.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fabio.neostoreapi.exception.handler.model.Error;
import com.fabio.neostoreapi.generic.model.GenericResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BeanValidationExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        List<Error> errors = new ArrayList<Error>();
        for (ConstraintViolation<?> violation : violations) {
            errors.add(new Error(violation.getMessage()));
        }
        GenericResponse genericResponse = new GenericResponse(errors);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(genericResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
	}

}
