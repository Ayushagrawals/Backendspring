package com.example.Backend_Spring.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.Backend_Spring.Exception.CustomException;
import com.example.Backend_Spring.Exception.ExceptionResponse;

@ControllerAdvice
public class ExceptionAdvice {
	
	public ResponseEntity<?> customResponse(CustomException e,WebRequest webRequest)
	{
		ExceptionResponse er=new ExceptionResponse("601","Invalid Value");
		return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
	}

}
