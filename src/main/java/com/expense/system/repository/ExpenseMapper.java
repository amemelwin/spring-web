package com.expense.system.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.expense.system.entity.Expense;
import com.expense.system.entity.MonthlyExpense;

@Mapper
public interface ExpenseMapper {
	
	// get all expense by Monthly
	public List<MonthlyExpense> getAllExpense();
	
	//detail
	public List<Expense> expenseDetail(@Param("year") int year, @Param("month") int month);
	
	// create new expense
	public void create(Expense expense);
	
	// get expense by Id
	public Expense getExpense(@Param("id") int id);
	
	// update expense 
	public void updateExpense(Expense expense);
	
	// delete expense
	public void deleteExpense(Expense expense);
	
	//API
	public Expense latestExpense();

}
