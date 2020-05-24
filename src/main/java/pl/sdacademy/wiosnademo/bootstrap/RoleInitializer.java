package pl.sdacademy.wiosnademo.bootstrap;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.Role;
import pl.sdacademy.wiosnademo.domain.Status;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.repositories.RoleRepository;
import pl.sdacademy.wiosnademo.repositories.UserRepository;


@Component
public class RoleInitializer implements ApplicationListener<ContextRefreshedEvent> {

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public RoleInitializer(final RoleRepository roleRepository, final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
    final Role newRole = new Role("ADMIN", LocalDateTime.now().plusYears(5), List.of());
    roleRepository.save(newRole);
    roleRepository.save(new Role("USER", LocalDateTime.now().plusYears(5), List.of()));
    userRepository.save(new User("admin", "admin@test.com", passwordEncoder.encode("Admin_123"),
            Status.ACTIVE, List.of(newRole)));
  }
}
