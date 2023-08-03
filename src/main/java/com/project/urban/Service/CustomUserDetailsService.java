package com.project.urban.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.urban.Entity.User;
import com.project.urban.Repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	@Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
          User user = userRepository.findByEmail(Email)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("User not found with username or email: "+ Email));

          Set<GrantedAuthority> authorities = new HashSet<>();
          authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);
    }

}
