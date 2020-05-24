package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
