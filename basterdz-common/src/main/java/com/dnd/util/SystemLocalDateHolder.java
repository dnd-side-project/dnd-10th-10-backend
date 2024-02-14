package com.dnd.util;

import java.time.LocalDate;

public class SystemLocalDateHolder implements LocalDateHolder {
	@Override
	public LocalDate today() {
		return LocalDate.now();
	}
}
