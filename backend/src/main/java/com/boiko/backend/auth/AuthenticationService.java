package com.boiko.backend.auth;

import com.boiko.backend.dto.AuthResponse;
import com.boiko.backend.dto.SignInRequest;
import com.boiko.backend.dto.SignUpRequest;
import com.boiko.backend.model.User;
import com.boiko.backend.service.FileInfoService;
import com.boiko.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final FileInfoService fileInfoService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse signUp(SignUpRequest request) {

        System.out.println(request);
        User user = User.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .birthday(request.getBirthday())
                .avatar(fileInfoService.getDefaultAvatar())
                .timestampRegistered(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        user = userService.create(user);
        String token = jwtUtils.generateToken(user);

        return new AuthResponse(user.getId(), token);
    }

    public AuthResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        User user = (User) userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());
        String token = jwtUtils.generateToken(user);

        return new AuthResponse(user.getId(), token);
    }
}
