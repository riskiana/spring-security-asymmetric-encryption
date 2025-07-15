package dev.riskiana.app.user;

import dev.riskiana.app.user.request.ProfileUpdateRequest;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

  public void mergeUserInfo(User user, ProfileUpdateRequest request) {
    if(StringUtils.isNotBlank(request.getFirstName())
    && !user.getFirstName().equals(request.getFirstName())) {
      user.setFirstName(request.getFirstName());
    }
    if(StringUtils.isNotBlank(request.getLastName())
        && !user.getLastName().equals(request.getLastName())) {
      user.setLastName(request.getLastName());
    }
    if(request.getDateOfBirth() != null
        && !user.getDateOfBirth().equals(request.getDateOfBirth())) {
      user.setDateOfBirth(request.getDateOfBirth());

    }
  }
}
