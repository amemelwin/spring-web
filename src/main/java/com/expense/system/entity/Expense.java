package com.expense.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Expense {

	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Date is required")
	private Date date;

	@NotBlank(message="Title is required")
	private String title;
	
	@NotNull	
	@Min(1)
	private int expense;

}
