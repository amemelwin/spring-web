package com.expense.system.form;

import java.util.Date;

//import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.expense.system.entity.Expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//import com.expense.system.validation.StringNumberConstraint;

import lombok.Data;

@Data
public class ExpenseForm {
	
	private int id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Date is required")
	private Date date;

	@NotBlank(message="Title is required")
	private String title;
	
	@NotBlank(message="expense is required")
	private String expense;
	
	public Expense toExpense() {
		Expense expense = new Expense();
		expense.setId(id);
		expense.setDate(this.date);
		expense.setTitle(this.title);
		expense.setExpense( Integer.parseInt(this.expense) );
		return expense;
	}
}
