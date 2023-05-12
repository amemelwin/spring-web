package com.expense.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.system.entity.Expense;
import com.expense.system.form.ExpenseForm;
import com.expense.system.service.ExpenseService;
import com.expense.system.validation.ExpenseValidator;

import jakarta.validation.Valid;

import com.expense.system.handler.ErrorResponse;
import com.expense.system.helper.Helper;
import com.expense.system.model.MonthlyExpenseModel;

//import jakarta.validation.Valid;

import java.util.List;

//@Controller
@SuppressWarnings("rawtypes")
@RestController // @Controller + @ResponseBody
@RequestMapping(path = "${apiPrefix}")
public class ExpenseApiController {

	@Autowired
	ExpenseService expenseService;

	@Autowired
	Helper helper;

	@GetMapping("/expense/get-monthly")
	public ResponseEntity getExpense() {
		List<MonthlyExpenseModel> monthlyExpenseList = this.expenseService.getAllExpense();
		if (monthlyExpenseList.size() > 0) {
			return new ResponseEntity<>(monthlyExpenseList, HttpStatus.OK);
		} else {
			return new ErrorResponse().response();
		}
	}

	@GetMapping("/expense/detail")
	public ResponseEntity getExpenseByMonth(@RequestParam int year, @RequestParam int month) {
		List<Expense> expenses = this.expenseService.expenseDetail(year, month);
		if (expenses.size() > 0) {
			return new ResponseEntity<>(expenses, HttpStatus.OK);
		} else {
			return new ErrorResponse().response();
		}
	}

	@GetMapping("/expense/get/{id}")
	public ResponseEntity getExpense(@PathVariable int id) {
		Expense expense = this.expenseService.getExpense(id);
		if (expense != null) {
			return new ResponseEntity<>(expense, HttpStatus.OK);
		} else {
			return new ErrorResponse("Your Expense Id " + id + " not found").response();
		}
	}

	@PostMapping("/expense/create")
	public ResponseEntity createExpense(@Valid @ModelAttribute ExpenseForm expenseForm, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return new ErrorResponse(this.helper.formErrorExtractor(result)).response();
		}
		// custom validator
		ExpenseValidator expenseValidate = new ExpenseValidator(expenseForm, model);
		if (!expenseValidate.validate()) {
			return new ErrorResponse(expenseValidate.getErrors().toString()).response();
		}
		// custom validator
		this.expenseService.create(expenseForm.toExpense());
		return new ResponseEntity<>(this.expenseService.latestExpense(), HttpStatus.CREATED);
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/expense/update/{id}")
	public ResponseEntity<Expense> updateExpense(@Valid @ModelAttribute ExpenseForm expenseForm, @PathVariable int id,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return new ErrorResponse(this.helper.formErrorExtractor(result)).response();
		}
		// custom validator
		ExpenseValidator expenseValidate = new ExpenseValidator(expenseForm, model);
		if (!expenseValidate.validate()) {
			return new ErrorResponse(expenseValidate.getErrors().toString()).response();
		}
		// check duplicate
		if (this.expenseService.getExpense(id) == null) {
			return new ErrorResponse("Expence Not found!").response();
		}
		this.expenseService.updateExpense(expenseForm.toExpense());
		return new ResponseEntity<>(expenseForm.toExpense(), HttpStatus.CREATED);
	}

	@DeleteMapping("/expense/delete/{id}")
	public ResponseEntity deleteExpense(@PathVariable int id) {
		Expense expense = this.expenseService.getExpense(id);
		if (expense != null) {
			this.expenseService.deleteExpense(expense);
			return new ResponseEntity<>(expense, HttpStatus.OK);
		} else {
			return new ErrorResponse("Your Expense Id " + id + " not Found").response();

		}
	}

}
