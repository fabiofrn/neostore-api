package com.fabio.neostoreapi.supplier.repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fabio.neostoreapi.exception.PersistenceException;
import com.fabio.neostoreapi.generic.pagination.PageData;
import com.fabio.neostoreapi.generic.pagination.PageParams;
import com.fabio.neostoreapi.supplier.model.Supplier;
import com.fabio.neostoreapi.supplier.model.SupplierResponse;
import com.fabio.neostoreapi.utils.MessageService;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

public class SupplierRepository {

	@Inject
	private EntityManager entityManager;

	@Inject
	private MessageService messageService;

	public Optional<Supplier> findById(Integer id) {
		return Optional.ofNullable(entityManager.find(Supplier.class, id));
	}

	@SuppressWarnings("unchecked")
	public PageData<SupplierResponse> findAll(PageParams pageParams) {

		String jpqCount = "SELECT COUNT(s.id) FROM Supplier s";
		long count = (long) entityManager.createQuery(jpqCount).getSingleResult();
		if (count > 0) {

			int primeiroResultado = (pageParams.getPageNumber() - 1) * pageParams.getPageSize();

			String jpql = "SELECT s FROM Supplier s ORDER BY s.id";
			Query query = entityManager.createQuery(jpql, Supplier.class).setFirstResult(primeiroResultado)
					.setMaxResults(pageParams.getPageSize());
			List<SupplierResponse> responseList = (List<SupplierResponse>) query.getResultStream()
					.map(record -> new SupplierResponse((Supplier) record)).collect(Collectors.toUnmodifiableList());

			return new PageData<SupplierResponse>(responseList, count, pageParams.getPageSize());
		}
		return new PageData<SupplierResponse>(new ArrayList<SupplierResponse>(), count, pageParams.getPageSize());

	}

	public Supplier save(Supplier supplier) throws PersistenceException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(supplier);
			entityManager.getTransaction().commit();
			return supplier;
		} catch (Exception exception) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			throw new PersistenceException(
					MessageFormat.format(messageService.getMensagem("message.error.insert"), supplier.getCnpj()),
					exception.getMessage());
		}
	}

	@Transactional
	public void update(Supplier supplier) {
		entityManager.merge(supplier);
	}

	@Transactional
	public void delete(Integer id) {
		Supplier supplier = entityManager.find(Supplier.class, id);
		if (supplier != null) {
			entityManager.remove(supplier);
		}
	}

}
