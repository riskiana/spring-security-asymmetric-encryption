package dev.riskiana.role;

import dev.riskiana.app.user.User;
import dev.riskiana.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name="ROLES")
public class Role extends BaseEntity {

  private String name;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;

}
