package com.universidad.crudv2.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class ApiException extends RuntimeException {

  private HttpStatus httpStatus;

  private String code;

  public ApiException(HttpStatus httpStatus, String code, String message) {

    super(message);

    this.code = code;

    this.httpStatus = httpStatus;

  }
}
