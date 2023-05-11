package com.expense.system.form;

import java.util.Date;

import com.expense.system.validation.StringNumberConstraint;

//import org.springframework.format.annotation.DateTimeFormat;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpenseForm {

//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@NotNull(message="Date is required")
	private Date date;

//	@NotBlank(message="Title is required")
	private String title;
	
//	@NotBlank(message="expense is required")
	@StringNumberConstraint
	private String expense;
}
