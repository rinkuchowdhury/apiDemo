package crm.demo.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// add an exception handle using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
		
		// create a studentErrorResponse
		CustomerErrorResponse error=new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		//return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	// add another exception handle for generic purpose
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc){
			
		// create a studentErrorResponse
		CustomerErrorResponse error=new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
