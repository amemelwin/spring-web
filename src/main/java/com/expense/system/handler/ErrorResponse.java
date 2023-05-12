package com.expense.system.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.expense.system.handler.ErrorResponse;
import com.expense.system.handler.ErrorResponse.ErrorMessage;

import lombok.Data;

@SuppressWarnings("unused")
public class ErrorResponse {
	public HttpStatus httpStatus = HttpStatus.CONFLICT;
	public String message = "Unknown Error Occour!";

	// No Argument Constructor
	public ErrorResponse() {
	}

	// One Argument Constructor
	public ErrorResponse(String message) {
		this.message = message;
	}

	// Two Argument Constructor (httpStatus, message)
	public ErrorResponse(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	// Two Argument Constructor (message, httpStatus)
	public ErrorResponse(String message, HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	@Data
	public class ErrorMessage {
		public int code;
		public String message;
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity response() {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(this.httpStatus.value());
		errorMessage.setMessage(this.message.toString());
		return ResponseEntity.status(this.httpStatus).body(errorMessage);
	}

}
