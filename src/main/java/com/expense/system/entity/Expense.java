package com.expense.system.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Expense {

	private int id;
	
	private Date date;

	private String title;
	
	private int expense;

}
