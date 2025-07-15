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

  }

  @Override
  public void deactivateAccount(String userId) {

  }

  @Override
  public void reactivateAccount(String userId) {

  }

  @Override
  public void deleteAccount(String userId) {

  }

}
