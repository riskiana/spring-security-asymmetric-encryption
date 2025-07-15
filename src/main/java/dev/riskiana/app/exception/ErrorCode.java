package dev.riskiana.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
  USER_NOT_FOUND("USER_NOT_FOUND", "User not found with id %s", HttpStatus.NOT_FOUND)

  ;

  private final String code;
  private final String defaultMessage;

  //i18n /l10n

  private HttpStatus status;

  ErrorCode(String code, String defaultMessage, HttpStatus status) {
    this.code = code;
    this.defaultMessage = defaultMessage;
    this.status = status;
  }
}
