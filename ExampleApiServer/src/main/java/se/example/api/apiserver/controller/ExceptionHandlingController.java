package se.example.api.apiserver.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import se.example.api.apiserver.exeption.WrongAnswerException;
import se.example.api.apiserver.model.ApiResponse;
import static se.example.api.apiserver.util.CommonUtil.buildResponseEntity;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiResponse (HttpStatus.BAD_REQUEST, error, ex));
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, 
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiResponse (HttpStatus.NOT_FOUND, error, ex));
	}

	
	@ExceptionHandler({ 
		IllegalArgumentException.class,
		JsonParseException.class, 
		WrongAnswerException.class 
	})
	protected ResponseEntity<Object> handleMBadRequestException(
			Exception ex) {
		ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler({
		JsonMappingException.class,
		IOException.class
	})
	protected ResponseEntity<Object>  handleInternalServerException(
			Exception ex) {
		ApiResponse apiError = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	

	
}
