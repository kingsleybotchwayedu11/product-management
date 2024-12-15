package product.mangagement.productm.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exceptions {
    
   @ExceptionHandler(MethodArgumentNotValidException.class)
   HashMap<String, String> formatException(MethodArgumentNotValidException exception) {
    HashMap<String , String> report = new HashMap<>();
    //find the fields that cause the errors and report the messages to it
    exception.getBindingResult().getFieldErrors().forEach((error -> {
        report.put(error.getField(), error.getDefaultMessage());
    }));
    return report;
   }

   @ExceptionHandler(NotFoundExceptions.class)
    public ResponseEntity<Response> notFound(RuntimeException exception) {

    // Return an error response with a 500 Internal Server Error status code.
    Response response = new Response(exception.getMessage(), 400);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<Response> handleAlreadyExist(RuntimeException exception) {

    // Return an error response with a 500 Internal Server Error status code.
    Response response = new Response(exception.getMessage(), 400);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler(WrongParameterValueException.class)
    public ResponseEntity<Response> wrongParamValue(RuntimeException exception) {

    // Return an error response with a 500 Internal Server Error status code.
    Response response = new Response(exception.getMessage(), 400);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class) 
    public ResponseEntity<Response> aunthorized(AccessDeniedException exception) {

        // Return an error response with a 500 Internal Server Error status code.
        Response response = new Response(exception.getMessage(), 400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
