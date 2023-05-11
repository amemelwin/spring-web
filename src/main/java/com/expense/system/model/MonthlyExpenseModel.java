package com.expense.system.model;

import lombok.Data;

@Data
public class MonthlyExpenseModel {
	private String title;
	private int year;
	private int month;
	private int total;
}
