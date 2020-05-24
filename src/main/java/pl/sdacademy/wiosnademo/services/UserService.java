package pl.sdacademy.wiosnademo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.Status;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserForm;
import pl.sdacademy.wiosnademo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
//    return List.of(
//        new User("Andrzej", "andrzej@test.com", "Andrzej_123", Status.ACTIVE, List.of()),
//        new User("Ala1", "ala@test.com", "Ala_1234", Status.ACTIVE, List.of())
//    );
  }

  public void createUser(final UserForm userForm) {
    // improvement -> stworzyć mapper, jako osobny, dodać builder zamiast konstruktora
    final User user = new User(userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), Status.ACTIVE, List.of());
    userRepository.save(user);
  }

  public void delete(final String username) {
    final User user = userRepository.findById(username).orElseThrow();
    userRepository.delete(user);
  }

  public User getByUsername(final String username) {
    return userRepository.findById(username).orElseThrow();
  }

  public void updateUser(final String username, final UserForm userForm) {
    if (!username.equals(userForm.getUsername())) {
      throw new RuntimeException("You filthy bastard. Cannot change username");
    }
    final User user = getByUsername(username);
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    userRepository.save(user);
  }
}
