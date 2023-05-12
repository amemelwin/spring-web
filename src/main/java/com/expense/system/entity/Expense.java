package com.expense.system.entity;

import java.util.Date;

import com.expense.system.form.ExpenseForm;

import lombok.Data;

@Data
public class Expense {

	private int id;

	private Date date;

	private String title;

	private int expense;

	public ExpenseForm toExpenseForm() {
		ExpenseForm expenseForm = new ExpenseForm();
		expenseForm.setId(id);
		expenseForm.setDate(date);
		expenseForm.setTitle(title);
		expenseForm.setExpense(Integer.toString(expense));
		return expenseForm;
	}

}
