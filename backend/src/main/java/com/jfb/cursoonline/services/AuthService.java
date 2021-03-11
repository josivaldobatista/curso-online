package com.jfb.cursoonline.services;

import com.jfb.cursoonline.entities.User;
import com.jfb.cursoonline.repositories.UserRepository;
import com.jfb.cursoonline.services.exceptions.ForbiddenException;
import com.jfb.cursoonline.services.exceptions.UnauthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  // Método para verificar se o usuário está autenticado
  @Transactional(readOnly = true)
  public User authenticated() {
    try {
      String username = SecurityContextHolder.getContext().getAuthentication().getName();
      return userRepository.findByEmail(username);
    } catch (Exception e) {
      throw new UnauthorizedException("Invalid user");
    }
  }

  // Método para verificar se um usuário é do proprio usuário ou pe admin
  public void validateSelfOrAdmin(Long userId) {
    User user = authenticated();
    if (!user.getId().equals(userId) && !user.hasRole("ROLE_ADMIN")) {
      throw new ForbiddenException("Access denied");
    }
  }
}
