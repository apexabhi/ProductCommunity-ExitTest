package com.nagarro.exceptions;
//importing packages
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> manageProductNotFound(ProductNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		
	}
	@ExceptionHandler(ProductAlreadyExistException.class)
	public ResponseEntity<Object> manageProductAlreadyExistException(ProductAlreadyExistException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> manageUserNotFound(ProductNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		
	}
	@ExceptionHandler(DeliverNotPossibleException.class)
	public ResponseEntity<Object> manageDeliveryNotPossible(DeliverNotPossibleException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		
	}
	@ExceptionHandler(NoPinCodeAvailableException.class)
	public ResponseEntity<Object> managePinCodes(DeliverNotPossibleException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		
	}
}
