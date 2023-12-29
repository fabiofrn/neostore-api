package com.fabio.neostoreapi.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

@ApplicationScoped
public class MessageService {

	@Produces
	@Named("messages")
	public ResourceBundle getMessages() {
		Locale defaultLocale = Locale.getDefault();
		return ResourceBundle.getBundle("messages", defaultLocale);
	}

	public String getMensagem(String chave) {
		return getMessages().getString(chave);
	}
}
