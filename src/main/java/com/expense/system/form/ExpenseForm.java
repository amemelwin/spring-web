package com.expense.system.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.expense.system.validation.StringNumberConstraint;

import lombok.Data;

@Data
public class ExpenseForm {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@NotNull(message="Date is required")
	private Date date;

//	@NotBlank(message="Title is required")
	private String title;
	
//	@NotBlank(message="expense is required")
	private String expense;
}
