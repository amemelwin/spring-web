package com.expense.system.helper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.expense.system.entity.Expense;
import com.expense.system.entity.MonthlyExpense;
import com.expense.system.model.MonthlyExpenseModel;

import lombok.Data;

@Service
public class Helper {
	public List<MonthlyExpenseModel> monthlyExpenseToForm(List<MonthlyExpense> monthlyExpense) {

		List<MonthlyExpenseModel> monthlyExpenseForm = new ArrayList<MonthlyExpenseModel>();

		for (MonthlyExpense expense : monthlyExpense) {
			MonthlyExpenseModel expenseModel = new MonthlyExpenseModel();
			expenseModel.setYear(expense.getYear());
			expenseModel.setMonth(expense.getMonth());
			expenseModel.setTitle(expense.getYear() + "/" + expense.getMonth());
			expenseModel.setTotal(expense.getTotal());
			monthlyExpenseForm.add(expenseModel);
		}
		return monthlyExpenseForm;
	}

	public void prepareForDetail(List<Expense> expenses, Model model) {
		int total = 0;
		for (Expense expense : expenses) {
			total += expense.getExpense();
		}
		model.addAttribute("expenseList", expenses);
		model.addAttribute("total", total);
	}

	@Data
	public class DateBox {
		String year;
		String month;
		String day;

		DateBox(String year, String month, String day) {
			this.year = year;
			this.month = month;
			this.day = day;
		}
	}

	public DateBox dateSplitter(Date date) {
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		String year = yearFormat.format(date);
		String month = monthFormat.format(date);
		String day = dayFormat.format(date);
		return new DateBox(year, month, day);
	}

	// Api
	public String formErrorExtractor(BindingResult result) {
		List<String> errorMsgs = new ArrayList<>();
		for (ObjectError error : result.getAllErrors()) {
			errorMsgs.add(error.getDefaultMessage());
		}
		return errorMsgs.toString();
	}

}
