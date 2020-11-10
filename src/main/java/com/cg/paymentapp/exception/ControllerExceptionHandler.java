package com.cg.paymentapp.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class ControllerExceptionHandler {
	    
	    @ResponseStatus(value = HttpStatus.NOT_FOUND)	// 404
		@ExceptionHandler(InvalidInputException.class)
		public ResponseEntity<Object> handleInvalidInputException(InvalidInputException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	    
	    @ResponseStatus(value = HttpStatus.NOT_FOUND)	// 404
		@ExceptionHandler(InsufficientBalanceException.class)
		public ResponseEntity<Object> handleInsuffiencientBalanceException(InsufficientBalanceException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	    
	    @ExceptionHandler(NoSuchDateFoundException.class)
		@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
		public ResponseEntity<Object> handleNullPinterException(NoSuchDateFoundException ex){
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	    
	    @ResponseStatus(value = HttpStatus.NOT_FOUND)	// 404
		@ExceptionHandler(BankAccountNotFoundException.class)
		public ResponseEntity<Object> handleBankAcccountNotFound(BankAccountNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	    
	    @ResponseStatus(value = HttpStatus.NOT_FOUND)	// 404
		@ExceptionHandler(BillPaymentNotFoundException.class)
		public ResponseEntity<Object> handleBillPaymentNotFound(BillPaymentNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		@ResponseStatus(value = HttpStatus.NOT_FOUND)	// 404
		@ExceptionHandler(InvalidFormatException.class)
		public ResponseEntity<Object> handleBillTypeNotFound(InvalidFormatException e) {
			return new ResponseEntity<>("Billtype is not valid", HttpStatus.NOT_FOUND);
		}
		
		@ResponseStatus(value = HttpStatus.NOT_FOUND)	// 404
		@ExceptionHandler(BenificiaryNotFoundException.class)
		public ResponseEntity<Object> handleResourceNotFound(BenificiaryNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(InvalidInputTransactionException.class)
		@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)   ///500
		public ResponseEntity<Object> handleInvalidInputexception(InvalidInputTransactionException ex){
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	    
}

