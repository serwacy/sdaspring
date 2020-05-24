package pl.sdacademy.wiosnademo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.Status;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    //return userRepository.findAll();
    return List.of(
        new User("Andrzej", "andrzej@test.com", "Andrzej_123", Status.ACTIVE, List.of()),
        new User("Ala1", "ala@test.com", "Ala_1234", Status.ACTIVE, List.of())
    );
  }
}
