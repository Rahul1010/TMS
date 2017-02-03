package com.ticket.util;

import java.time.LocalDate;

public class ValidationUtil {

	public static boolean isInvalidString(String name) {
		return name == null || "".equals(name.trim());
	}

	public static boolean isInvalidNumber(Integer no) {
		return no == null || no <= 0;
	}

	public static boolean isPastDate(LocalDate ld) {
		return ld.isBefore(LocalDate.now());
	}
}
