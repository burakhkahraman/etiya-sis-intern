package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.config.CustomUserDetailService;
import com.etiya.studentinfosystem.postgredb.dto.LoginDto;
import com.etiya.studentinfosystem.postgredb.dto.RefreshDto;
import com.etiya.studentinfosystem.postgredb.dto.RegisterDto;
import com.etiya.studentinfosystem.postgredb.exception.APIException;
import com.etiya.studentinfosystem.postgredb.jwt.JwtUtil;
import com.etiya.studentinfosystem.postgredb.model.*;
import com.etiya.studentinfosystem.postgredb.repository.RefreshRepository;
import com.etiya.studentinfosystem.postgredb.repository.RoleRepository;
import com.etiya.studentinfosystem.postgredb.repository.UserRepository;
import com.etiya.studentinfosystem.postgredb.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CustomUserDetailService customUserDetailService;
    private JwtUtil jwtUtil;

    private RefreshRepository refreshRepository;

    @Override
    public JwtResponse loginService(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = customUserDetailService.loadUserByUsername(loginDto.getEmail());


        return JwtResponse.builder()
                .accessToken(jwtUtil.generateToken(user))
                .refreshToken(jwtUtil.generateRefreshToken(user.getUsername()))
                .build();


    }

    @Override
    public APIResponse registerService(RegisterDto registerDto) throws Exception {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "User already exists with the provided email,try with another email");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        //setting the role
        Role role = roleRepository.findByName("Teacher").orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST, "Teacher doesn't exists in the database"));
        Set<Role> userRole = new HashSet<>();
        userRole.add(role);

        user.setRoles(userRole);
        userRepository.save(user);

        return APIResponse.builder()
                .message("Registration successful")
                .build();
    }

    @Override
    public JwtResponse refreshService(RefreshDto refreshDto) {

        Optional<RefreshToken> refreshToken = refreshRepository.findByToken(refreshDto.getRefreshToken());

        if (refreshToken.isEmpty()) {
            throw new APIException(HttpStatus.UNAUTHORIZED, "Refresh token " + refreshDto.getRefreshToken() + " not available for user with email " + refreshToken.get().getEmail());
        }

        Instant refreshTokenExpiryTime = refreshToken.get().getExpiry();
        Instant currentTime = Instant.now();

        if (refreshTokenExpiryTime.isAfter(currentTime)) {
            UserDetails user = customUserDetailService.loadUserByUsername(refreshToken.get().getEmail());
            String newAccessToken = jwtUtil.generateToken(user);
            return new JwtResponse(newAccessToken, refreshToken.get().getToken());
        } else {
            refreshRepository.deleteById(refreshToken.get().getId());
            throw new APIException(HttpStatus.UNAUTHORIZED, "Refresh token has expired.Please re-authenticate yourself");
        }
    }

}
