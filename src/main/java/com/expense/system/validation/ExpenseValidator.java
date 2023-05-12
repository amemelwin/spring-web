package com.expense.system.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;

import com.expense.system.form.ExpenseForm;

public class ExpenseValidator {
	private Date dateForm;
	private String titleForm;
	private String expenseForm;
	private Model model;
	private List<String> errors = new ArrayList<String>();

	public ExpenseValidator(Model model, ExpenseForm expenseForm) {
		this.dateForm = expenseForm.getDate();
		this.titleForm = expenseForm.getTitle();
		this.expenseForm = expenseForm.getExpense();
		this.model = model;
	}

	public ExpenseValidator(ExpenseForm expenseForm, Model model) {
		this.dateForm = expenseForm.getDate();
		this.titleForm = expenseForm.getTitle();
		this.expenseForm = expenseForm.getExpense();
		this.model = model;
	}

	public Boolean validate() {
		return this.dateSastify() && this.titleSastify() && this.expenseSastify();
	}

	public List<String> getErrors() {
		return errors;
	}

	private Boolean dateSastify() {
		// validate rule here
		return true;
	}

	private Boolean titleSastify() {
		// validate rule here
		return true;
	}

	private Boolean expenseSastify() {
		System.out.println(this.expenseForm);
		try {
			Integer.parseInt(this.expenseForm);
			return true;
		} catch (Exception e) {
			errors.add("Your Expense Should be number!");
			model.addAttribute("expense_error", "Your Expense Should be number!");
			return false;
		}
	}
}
