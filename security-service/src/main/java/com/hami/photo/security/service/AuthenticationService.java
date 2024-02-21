package com.hami.photo.security.service;

import com.hami.photo.common.entity.security.User;
import com.hami.photo.security.dto.JwtAuthenticationResponse;
import com.hami.photo.security.dto.RefreshTokenRequest;
import com.hami.photo.security.dto.SigningRequest;
import com.hami.photo.security.dto.SignupRequest;


public interface AuthenticationService {
    User signup(SignupRequest signupRequest);
    JwtAuthenticationResponse signing(SigningRequest signingRequest);
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
