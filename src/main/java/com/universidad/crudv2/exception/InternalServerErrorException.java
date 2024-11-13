package com.universidad.crudv2.exception;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor

public class InternalServerErrorException extends ApiException {

  public InternalServerErrorException(String code, String message) {

    super(HttpStatus.INTERNAL_SERVER_ERROR, code, message);

  }
}
