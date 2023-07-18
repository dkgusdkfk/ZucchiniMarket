package com.zucchini.zucchini_back.global.config.security;

import com.zucchini.zucchini_back.domain.user.domain.User;
import com.zucchini.zucchini_back.domain.user.repository.UserRepository;
import com.zucchini.zucchini_back.global.config.cache.CacheKey;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Cacheable(value = CacheKey.USER, key = "#username", unless = "#result == null")
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        return CustomUserDetails.of(user);
    }
}