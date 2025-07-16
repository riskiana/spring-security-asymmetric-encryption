package dev.riskiana.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
  USER_NOT_FOUND("USER_NOT_FOUND", "User not found with id %s", HttpStatus.NOT_FOUND),

  CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH" ,"current password and new password are not the same", HttpStatus.BAD_REQUEST),
  INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD",
      "current password is invalid", HttpStatus.BAD_REQUEST ),
  ACCOUNT_ALREADY_DEACTIVATED("ACCOUNT_ALREADY_DEACTIVATED","Account already deactivated", HttpStatus.BAD_REQUEST
  ), ACCOUNT_ALREADY_ACTIVE("ACCOUNT_ALREADY_ACTIVE","Account already active" , HttpStatus.BAD_REQUEST );

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
