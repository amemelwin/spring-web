package com.expense.system.controller;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expense.system.entity.Expense;
import com.expense.system.form.ExpenseForm;
import com.expense.system.helper.Helper;
import com.expense.system.helper.Helper.DateBox;
import com.expense.system.service.ExpenseService;

//import jakarta.validation.Valid;

@Controller
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	Helper helper;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("monthlyList", this.expenseService.getAllExpense());
		return "screen/index";
	}

	@GetMapping("/expense/detail")
	public String detail(Model model, @RequestParam int year, @RequestParam int month) {
		this.expenseService.expenseDetail(year, month, model);
		return "screen/detail";
	}

	@GetMapping("/expense/create")
	public String create(Model model) {
		ExpenseForm expenseForm = new ExpenseForm();
		expenseForm.setDate(new Date());
		model.addAttribute("expenseForm", expenseForm);
		return "screen/create";
	}

	@PostMapping("/expense/create")
	public String create(@Valid @ModelAttribute ExpenseForm expenseForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "screen/create";
		}

//		this.expenseService.create(expenseForm);	
		return "redirect:/";
	}

	@GetMapping("/update-expense/{id}")
	public String updateExpense(Model model, @PathVariable int id) {
		model.addAttribute("expense", this.expenseService.getExpense(id));
		return "screen/update";
	}
	
	@PostMapping("/update-expense")
	public String updateExpense(@ModelAttribute Expense expense) {
		this.expenseService.updateExpense(expense);
		DateBox date= this.helper.dateSplitter(expense.getDate());
		return String.format("redirect:/expense/detail?year=%s&month=%s",date.getYear(),date.getMonth());
	}
	
	@PostMapping("/expense/delete/{id}")
	public String deleteExpense(@PathVariable int id) {
		Expense expense = this.expenseService.getExpense(id);
		DateBox date = this.helper.dateSplitter(expense.getDate());
		this.expenseService.deleteExpense(expense);		
		boolean isDetailExit = this.expenseService.isDetailExit(Integer.parseInt(date.getYear()), Integer.parseInt(date.getMonth()));
		return isDetailExit? String.format("redirect:/expense/detail?year=%s&month=%s",date.getYear(),date.getMonth()):"redirect:/";
//		return "redirect:/";
		
	}

}
