package com.nk.controller;


import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController{

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public String error() {
		System.out.println("Dsad");
		return "error";
	}
	
}
