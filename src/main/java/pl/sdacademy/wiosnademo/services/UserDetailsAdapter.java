package pl.sdacademy.wiosnademo.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.sdacademy.wiosnademo.domain.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsAdapter implements UserDetails {
   private final User user;

   public UserDetailsAdapter(final User user) {
      this.user = user;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return user.getRoles().stream()
              .filter(role->role.getExpirationDate().isBefore(LocalDateTime.now()))
              .map(role -> new SimpleGrantedAuthority(role.getName()))
              .collect(Collectors.toList());
   }

   @Override
   public String getPassword() {
      return user.getPassword();
   }

   @Override
   public String getUsername() {
      return user.getUsername();
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
