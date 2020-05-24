package pl.sdacademy.wiosnademo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.sdacademy.wiosnademo.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

  @Query("SELECT u FROM users u left join fetch u.roles WHERE u.username = :username")
  Optional<User> findUserByUsernameWithRoles(@Param("username") final String username);
}
