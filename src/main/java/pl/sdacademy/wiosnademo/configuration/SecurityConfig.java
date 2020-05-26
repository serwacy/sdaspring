package pl.sdacademy.wiosnademo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   private final UserDetailsService userDetailsService;

   public SecurityConfig(@Qualifier("customUserDetailsService") final UserDetailsService userDetailsService) {
      this.userDetailsService = userDetailsService;
   }

   @Override
   protected void configure(final HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/users/**").authenticated()
              .anyRequest().permitAll()
              .and()
              .formLogin()
              .and()
              .logout();
   }
   
   @Override
   public UserDetailsService userDetailsServiceBean() throws Exception{
      return userDetailsService;
   }
}
