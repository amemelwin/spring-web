package com.expense.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.system.entity.Expense;
import com.expense.system.form.MonthlyExpenseForm;
import com.expense.system.service.ExpenseService;
import com.expense.system.handler.ErrorResponse;
import com.expense.system.helper.Helper;

import jakarta.validation.Valid;

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
	// Path
	/*
	 * Example Usage /getuser/1 GET @PathVariable /createuser POST @ModelAttribute
	 * /deleteuser/3 DELETE @PathVariable /updateuser PUT or POST @ModelAttribute
	 */
	// @ResponseBody / Since @RestController, no need
	@GetMapping("/learn/{id}/{name}") // localhost:8080/api/v1/learn/4/Amie
	public String learn(@PathVariable int id, @PathVariable String name) {
		return name + "  is Learning page " + id;
	}

	// Request Parameter
	@GetMapping("/learn") // localhost:8080/api/v1/learn?firstname=Aar Kar&lastname=Mann Aung&age=26
	public String learn(@RequestParam String firstname, @RequestParam String lastname, @RequestParam int age) {
		return firstname + " " + lastname + " is " + age;
	}

	// [MonthlyExpenseForm(title=2022/1, year=2022, month=1, total=3500),
	// MonthlyExpenseForm(title=2023/4, year=2023, month=4, total=200)]
	@GetMapping("/expense/get-monthly")
	public ResponseEntity getExpense() {
		List<MonthlyExpenseForm> monthlyExpenseList = this.expenseService.getAllExpense();
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
			return new ErrorResponse().response();
		}
	}

	@PostMapping("/expense/create")
	public ResponseEntity createExpense(@Valid @ModelAttribute Expense expense, BindingResult result) {
		if (result.hasErrors()) {
			return new ErrorResponse(this.helper.formErrorExtractor(result)).response();
//			return new ResponseEntity<>(this.helper.formErrorExtractor(result), HttpStatus.CONFLICT);			
		}
		this.expenseService.create(expense);
		Expense latestExpense = this.expenseService.latestExpense();
		return new ResponseEntity<>(latestExpense, HttpStatus.CREATED);

	}
	
	@SuppressWarnings("unchecked")
	@PutMapping("/expense/update/{id}")
	public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense,@PathVariable int id) {	
		expense.setId(id);
		if(this.expenseService.getExpense(id) != null) {
			this.expenseService.updateExpense(expense);	
			return new ResponseEntity<>(expense,HttpStatus.OK);
		}else {
			return new ErrorResponse().response();			
		}		
	}
}
