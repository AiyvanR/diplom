package com.example.diploma.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
@AllArgsConstructor
public class FieldAlreadyExistsException extends RuntimeException{
  private ExceptionMessage errorMessage;

  public FieldAlreadyExistsException(String fieldName,String value){
      errorMessage = new ExceptionMessage(String.format("%s '%s' already exists", fieldName, value ));
  }

    public ExceptionMessage getErrorMessage() {
        return errorMessage;
    }
}
