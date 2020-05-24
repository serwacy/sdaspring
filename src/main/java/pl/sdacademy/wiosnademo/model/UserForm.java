package pl.sdacademy.wiosnademo.model;

import static java.util.Objects.nonNull;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

  @NotNull
  @Length(min = 4)
  private String username;

  @NotNull
  @Email
  private String email;
  private String password;
  private String confirmPassword;

  @AssertTrue(message = "passwords do not match")
  public boolean isPasswordsMatch() {
    return nonNull(password) && nonNull(confirmPassword)
        && password.length() >= 8 && password.equals(confirmPassword);
  }
}
