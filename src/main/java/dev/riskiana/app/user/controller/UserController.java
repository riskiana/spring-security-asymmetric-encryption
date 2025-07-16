package dev.riskiana.app.user.controller;

import dev.riskiana.app.user.User;
import dev.riskiana.app.user.UserService;
import dev.riskiana.app.user.request.ChangePasswordRequest;
import dev.riskiana.app.user.request.ProfileUpdateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

  private final UserService userService;

  @PatchMapping("/me")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void updateProfileInfo(
      @RequestBody @Valid final ProfileUpdateRequest request,
      final String userId,
      final Authentication principal) {
    userService.updateProfileInfo(request, getUserId(principal));

  }

  @PostMapping("/me/password")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void updatePassword(
      @RequestBody
      @Valid final ChangePasswordRequest request,
      final Authentication principal
  ) {
    userService.changePassword(request, getUserId(principal));
  }

  @PatchMapping("/me/deactivate")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deactivateAccount(
      final Authentication principal
  ) {
    userService.deactivateAccount(getUserId(principal));
  }

  @PatchMapping("/me/reactivate")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void reactivateAccount(
      final Authentication principal
  ) {
    userService.reactivateAccount(getUserId(principal));
  }

  @DeleteMapping("/me")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteAccount(final Authentication principal) {
    userService.deleteAccount(getUserId(principal));
  }


  private String getUserId(Authentication principal) {
    return ((User) principal.getPrincipal()).getId();
  }

}
