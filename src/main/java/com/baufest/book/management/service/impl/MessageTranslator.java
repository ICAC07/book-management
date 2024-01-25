package com.baufest.book.management.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageTranslator {
	
	private final MessageSource messageSource;
	
	public MessageTranslator(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String get(String key, String... args) {
		return messageSource.getMessage(key, args, Locale.getDefault());
	}
}
