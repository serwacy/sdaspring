package pl.sdacademy.wiosnademo.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@Entity(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  @Id
  @Length(min = 3, max = 255)
  private String name;

  @Column(name = "expiration_date")
  @Future
  private LocalDateTime expirationDate;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;
}
