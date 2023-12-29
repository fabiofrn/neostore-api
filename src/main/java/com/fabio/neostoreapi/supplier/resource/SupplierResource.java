package com.fabio.neostoreapi.supplier.resource;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fabio.neostoreapi.exception.EmptyResultDataAccessException;
import com.fabio.neostoreapi.exception.PersistenceException;
import com.fabio.neostoreapi.generic.model.GenericResponse;
import com.fabio.neostoreapi.generic.pagination.PageData;
import com.fabio.neostoreapi.generic.pagination.PageParams;
import com.fabio.neostoreapi.supplier.model.Supplier;
import com.fabio.neostoreapi.supplier.model.SupplierRequest;
import com.fabio.neostoreapi.supplier.model.SupplierResponse;
import com.fabio.neostoreapi.supplier.service.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/suppliers")
@Produces(MediaType.APPLICATION_JSON)

public class SupplierResource {

	private ObjectMapper mapper;

	public SupplierResource() {
		this.mapper = new ObjectMapper();
	}

	@Inject
	private SupplierService supplierService;

	@GET
	public Response listSuppliers(@QueryParam("pageNumber") int pageNumber, @QueryParam("pageSize") int pageSize) {
		PageData<SupplierResponse> suppliersPage = supplierService.findAll(new PageParams(pageNumber, pageSize));
		return Response.ok(suppliersPage).build();
	}

	@GET
	@Path("/{id}")
	public SupplierResponse findBySupplierId(@PathParam("id") Integer id) throws EmptyResultDataAccessException {
		return new SupplierResponse(supplierService.findById(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveSupplier(@Valid SupplierRequest supplier, @Context UriInfo uriInfo)
			throws PersistenceException {
		Supplier supplierSaved = supplierService.save(
				new Supplier(supplier.getCnpj(), supplier.getName(), supplier.getDescription(), supplier.getEmail()));
		return Response.ok(new GenericResponse(MessageFormat.format("Registro criado e disponivel em {0}/{1}",
				uriInfo.getAbsolutePath(), supplierSaved.getId().toString()))).build();
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/upload")
	public void uploadFile(@FormDataParam("file") InputStream fileInputStream)
			throws IOException, PersistenceException {

		Supplier[] suppliers = mapper.readValue(fileInputStream, Supplier[].class);
		supplierService.processSuppliers(suppliers);

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response updateSupplier(@PathParam("id") Integer id, Supplier supplier)
			throws EmptyResultDataAccessException, PersistenceException {
		supplier.setId(id);
		supplierService.update(id, supplier);
		return Response.ok(new GenericResponse("Registro atualizado com sucesso.")).build();
	}

	@DELETE
	@Path("/{id}")
	public void deleteSupplier(@PathParam("id") Integer id) {
		supplierService.delete(id);
	}
}
