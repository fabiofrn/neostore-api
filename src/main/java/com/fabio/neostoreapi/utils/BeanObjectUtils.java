package com.fabio.neostoreapi.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;

import com.fabio.neostoreapi.supplier.model.Supplier;

public class BeanObjectUtils {

	public static Supplier copyProperties(Supplier newObject, Supplier persistedObject, String... ignoredProperties)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<PropertyDescriptor> properties = Arrays.asList(PropertyUtils.getPropertyDescriptors(newObject));

		List<String> ignoredPropertiesList = Arrays.asList(ignoredProperties);
		List<String> propertiesToCopy = properties.stream().map(PropertyDescriptor::getName)
				.filter(ignoredPropertie -> !ignoredPropertiesList.contains(ignoredPropertie))
				.collect(Collectors.toList());

		for (String property : propertiesToCopy) {
			PropertyUtils.setProperty(persistedObject, property, PropertyUtils.getProperty(newObject, property));
		}

		return persistedObject;
	}

}
