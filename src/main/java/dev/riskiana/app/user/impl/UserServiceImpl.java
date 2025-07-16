package dev.riskiana.app.user.impl;

import dev.riskiana.app.exception.BusinessException;
import dev.riskiana.app.exception.ErrorCode;
import dev.riskiana.app.user.User;
import dev.riskiana.app.user.UserMapper;
import dev.riskiana.app.user.UserRepository;
import dev.riskiana.app.user.UserService;
import dev.riskiana.app.user.request.ChangePasswordRequest;
import dev.riskiana.app.user.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
    return this.userRepository.findByEmailIgnoreCase(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userEmail));
  }

  @Override
  public void updateProfileInfo(ProfileUpdateRequest request, String userId) {
    final User savedUser = this.userRepository.findById(userId)
        .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
    this.userMapper.mergeUserInfo(savedUser, request);
    this.userRepository.save(savedUser);
  }

  @Override
  public void changePassword(ChangePasswordRequest request, String userId) {
    if(!request.getNewPassword().equals(request.getConfirmPassword())) {
      throw new BusinessException(ErrorCode.CHANGE_PASSWORD_MISMATCH);
    }

    final User savedUser = userRepository.findById(userId)
        .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
    if(!this.passwordEncoder.matches(request.getCurrentPassword(),
        savedUser.getPassword())) {
      throw new BusinessException(ErrorCode.INVALID_CURRENT_PASSWORD);
    }
    final String encoded = passwordEncoder.encode(request.getNewPassword());
    savedUser.setPassword(encoded);
    userRepository.save(savedUser);

  }

  @Override
  public void deactivateAccount(String userId) {
    final User user = userRepository.findById(userId)
        .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
    if(!user.isEnabled()){
      throw new BusinessException(ErrorCode.ACCOUNT_ALREADY_DEACTIVATED);
    }

    user.setEnabled(false);
    userRepository.save(user);

  }

  @Override
  public void reactivateAccount(String userId) {
    final User user = userRepository.findById(userId)
        .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
    if(user.isEnabled()){
      throw new BusinessException(ErrorCode.ACCOUNT_ALREADY_ACTIVE);
    }

    user.setEnabled(true);
    userRepository.save(user);
  }

  @Override
  public void deleteAccount(String userId) {
    //using scheduler to delete everything to the profile
  }

}
