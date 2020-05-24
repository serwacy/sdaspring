package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import pl.sdacademy.wiosnademo.domain.Role;

// A extends JpaRepository -> B e A -> C e B;
public interface RoleRepository extends JpaRepository<Role, String> {
}
