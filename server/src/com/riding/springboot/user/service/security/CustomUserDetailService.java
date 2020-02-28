package com.riding.springboot.user.service.security;

import com.riding.springboot.advice.exception.CUserNotFoundException;
import com.riding.springboot.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userJpaRepo;

    public UserDetails loadUserByUsername(String userPk) {
        return userJpaRepo.findById(Integer.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
    }
}
