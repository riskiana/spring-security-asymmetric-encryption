package dev.riskiana.app.user;

import dev.riskiana.app.user.request.ChangePasswordRequest;
import dev.riskiana.app.user.request.ProfileUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  void updateProfileInfo(ProfileUpdateRequest request, String userId);

  void changePassword(ChangePasswordRequest request, String userId);

  void deactivateAccount(String userId);

  void reactivateAccount(String userId);

  void deleteAccount(String userId);

}
