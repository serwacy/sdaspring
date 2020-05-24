package pl.sdacademy.wiosnademo.bootstrap;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.Role;
import pl.sdacademy.wiosnademo.repositories.RoleRepository;


@Component
public class RoleInitializer implements ApplicationListener<ContextRefreshedEvent> {

  private final RoleRepository roleRepository;

  public RoleInitializer(final RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Transactional
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
    roleRepository.save(new Role("ADMIN", LocalDateTime.now().plusYears(5), List.of()));
    roleRepository.save(new Role("USER", LocalDateTime.now().plusYears(5), List.of()));
  }
}
