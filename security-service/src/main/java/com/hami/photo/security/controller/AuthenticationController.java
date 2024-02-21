package com.hami.photo.security.controller;

import com.hami.photo.common.entity.security.User;
import com.hami.photo.security.dto.JwtAuthenticationResponse;
import com.hami.photo.security.dto.RefreshTokenRequest;
import com.hami.photo.security.dto.SigningRequest;
import com.hami.photo.security.dto.SignupRequest;
import com.hami.photo.security.repository.UserRepository;
import com.hami.photo.security.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hami.photo.common.entity.security.Role.ROLE_ADMIN;


@Tag(name = "User-Security", description = "signing and signup for Hamed Photo website.")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;


    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByRole(ROLE_ADMIN);

        if (adminAccount == null) {

            User user = new User();

            user.setFirstName("Hamed");
            user.setLastName("Zarrabi");
            user.setEmail("hamed.zarrabi87@gmail.com");
            user.setEnabled(true);
            user.setRole(ROLE_ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("lightwave"));

            LOGGER.info(user.getRole().toString());

            userRepository.save(user);
        }
    }

    @Operation(
            summary = "Signup",
            description = "signup new user for access"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful signup")
    })
    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {

        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new UsernameNotFoundException("Email is already taken!");
        } else {
            return new ResponseEntity<User>(authenticationService.signup(signupRequest), HttpStatus.CREATED);
        }
    }

    @Operation(
            summary = "Signing",
            description = "signing user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful signing")
    })
    @PostMapping("/signing")
    public ResponseEntity<JwtAuthenticationResponse> signing(@RequestBody SigningRequest signingRequest) {
        return ResponseEntity.ok(authenticationService.signing(signingRequest));
    }

    @Operation(
            summary = "Refresh",
            description = "refresh jwt"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful refresh")
    })
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
