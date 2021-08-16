package com.estock.market.company.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.estock.market.company.model.ErrorMessage;

/**
 * This Class holds the Customized Exception Handler for the application
 * 
 *
 *
 */
@ControllerAdvice
public class CompanyServiceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceExceptionHandler.class);

	/**
	 * This Method is used to handleMethodArgumentNotValid
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		LOGGER.error("Bean Validation Failed");
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
		return new ResponseEntity<Object>(message, new HttpHeaders(), message.getStatus());
	}

	/**
	 * This Method is used to handleDataIntegrityViolation
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {

		String errors = "DataIntegrityViolationException";
		LOGGER.error("DataIntegrityViolationException Occured While Processing Request");
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
		return new ResponseEntity<Object>(message, new HttpHeaders(), message.getStatus());
	}

	/**
	 * This Method is used to handleTransactionSystemException
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ TransactionSystemException.class })
	public ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException ex, WebRequest request) {

		String errors = "TransactionSystemException";
		LOGGER.error("TransactionSystemException Occured While Processing Request");
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
		return new ResponseEntity<Object>(message, new HttpHeaders(), message.getStatus());
	}
}
