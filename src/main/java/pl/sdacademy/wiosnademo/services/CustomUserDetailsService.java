package pl.sdacademy.wiosnademo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
   private final UserService userService;

   public CustomUserDetailsService(final UserService userService) {
      this.userService = userService;
   }

   @Override
   public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
      return new UserDetailsAdapter(userService.getUserWithRoles(s));
   }
}
