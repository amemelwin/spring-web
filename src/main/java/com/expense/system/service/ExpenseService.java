package com.expense.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

import com.expense.system.entity.Expense;
import com.expense.system.helper.Helper;
import com.expense.system.model.MonthlyExpenseModel;
import com.expense.system.repository.ExpenseMapper;

@Service
public class ExpenseService {
	@Autowired
	ExpenseMapper expenseMapper;
	
	@Autowired
	Helper helper;
	
	public List<MonthlyExpenseModel> getAllExpense() {
		return this.helper.monthlyExpenseToForm(this.expenseMapper.getAllExpense());
	}
	
	public void expenseDetail(int year, int month, Model model) { 
		this.helper.prepareForDetail(this.expenseMapper.expenseDetail(year,month),model);
	}
	
	public void create(Expense expense) {
		this.expenseMapper.create(expense);
	}
	
	public Expense getExpense(int id) {
		return this.expenseMapper.getExpense(id);
	}

	public void updateExpense(Expense expense) {
		this.expenseMapper.updateExpense(expense);
	}
	
	public void deleteExpense(Expense expense) {
		this.expenseMapper.deleteExpense(expense);
	}
	
	public boolean isDetailExit(int year,int month) {
		return this.expenseMapper.expenseDetail(year,month).size()>0;
	}
	
	//	API
	public List<Expense> expenseDetail(int year, int month) { 
		return this.expenseMapper.expenseDetail(year,month);
	}
	
	public Expense latestExpense() {
		return this.expenseMapper.latestExpense();
	}


}
