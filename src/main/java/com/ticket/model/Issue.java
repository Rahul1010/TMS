package com.ticket.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Issue {
	private int id;
	private User userId;
	private Department departmentId;
	private String subject;
	private String description;
	private String priority;
	private LocalDate dateReported;
	private LocalDate dateResolved;
	private String status;
}
