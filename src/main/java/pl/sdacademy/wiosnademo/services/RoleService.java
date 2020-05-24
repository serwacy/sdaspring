package pl.sdacademy.wiosnademo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.sdacademy.wiosnademo.domain.Role;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.repositories.RoleRepository;

@Service
public class RoleService {

  private final RoleRepository roleRepository;

  public RoleService(final RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  public List<Role> findRolesUnassignedToUser(final User user) {
    return getAllRoles().stream()
        .filter(role -> !user.getRoles().contains(role)) // problem z equals?
        .collect(Collectors.toList());
  }
}
