package com.jfb.cursoonline.services;

import java.util.Optional;

import com.jfb.cursoonline.dto.UserDTO;
import com.jfb.cursoonline.entities.User;
import com.jfb.cursoonline.repositories.UserRepository;
import com.jfb.cursoonline.services.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

  private static Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository repository;

  @Autowired
  private AuthService authService;

  @Transactional(readOnly = true)
  public UserDTO findById(Long id) {
    authService.validateSelfOrAdmin(id); // <- Verificar se o usuário logado é do proprio usuário ou admin
    Optional<User> obj = repository.findById(id);
    User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
    return new UserDTO(entity);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByEmail(username);
    if (user == null) {
      logger.error("User not found: " + username);
      throw new UsernameNotFoundException("Email not found.");
    }
    logger.info("User found: " + username);
    return user;
  }

}
