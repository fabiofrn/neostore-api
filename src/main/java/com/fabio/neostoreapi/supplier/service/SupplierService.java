package com.fabio.neostoreapi.supplier.service;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.jvnet.hk2.annotations.Service;

import com.fabio.neostoreapi.exception.EmptyResultDataAccessException;
import com.fabio.neostoreapi.exception.PersistenceException;
import com.fabio.neostoreapi.generic.pagination.PageData;
import com.fabio.neostoreapi.generic.pagination.PageParams;
import com.fabio.neostoreapi.supplier.model.Supplier;
import com.fabio.neostoreapi.supplier.model.SupplierResponse;
import com.fabio.neostoreapi.supplier.repository.SupplierRepository;
import com.fabio.neostoreapi.utils.BeanObjectUtils;
import com.fabio.neostoreapi.utils.MessageService;

import jakarta.inject.Inject;

@Service
public class SupplierService {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Inject
	private SupplierRepository repository;

	@Inject
	private MessageService messageService;

	public Supplier findById(Integer id) throws EmptyResultDataAccessException {
		return repository.findById(id).orElseThrow(
				() -> new EmptyResultDataAccessException(messageService.getMensagem("message.error.notfound")));
	}

	public PageData<SupplierResponse> findAll(PageParams pageParams) {
		return repository.findAll(pageParams);
	}

	public Supplier save(Supplier supplier) throws PersistenceException {
		return repository.save(supplier);
	}

	public void update(Integer id, Supplier supplier) throws EmptyResultDataAccessException, PersistenceException {
		Supplier supplierSaved = findById(id);
		try {
			BeanObjectUtils.copyProperties(supplier, supplierSaved, "id");
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new PersistenceException(MessageFormat.format(messageService.getMensagem("message.error.update"), id),
					e.getLocalizedMessage());
		}
		repository.update(supplierSaved);
	}

	public void processSuppliers(Supplier[] suppliers) throws PersistenceException {
		StringBuilder cnpjErrors = new StringBuilder("Houve um erro ao inserir os seguintes CNPJ's: ");
		Stream.of(suppliers).forEach(supplier -> {
			try {
				repository.save(supplier);
			} catch (PersistenceException e) {
				logger.log(Level.SEVERE, MessageFormat.format(messageService.getMensagem("message.error.insert"), supplier.getCnpj()));
				cnpjErrors.append(supplier.getCnpj().concat(", "));
			}
		});

		if (!cnpjErrors.toString().endsWith("CNPJ's: ")) {
			throw new PersistenceException(cnpjErrors.toString().substring(0, cnpjErrors.toString().length() - 2),
					null);
		}
	}

	public void delete(Integer id) {
		repository.delete(id);
	}

}
