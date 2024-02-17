package com.dnd.api.common.random;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class SystemLocalDateHolder implements LocalDateHolder {
	@Override
	public LocalDate today() {
		return LocalDate.now();
	}
}
