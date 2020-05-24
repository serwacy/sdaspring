package pl.sdacademy.wiosnademo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @Length(min = 4, max = 255)
  private String username;

  @NotNull
  @Email
  @Column(name = "email")
  private String email;

  @NotNull
  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  private Status status;

  @ManyToMany
  @JoinTable(name = "users_to_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "username"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "name"))
  private List<Role> roles;
}
