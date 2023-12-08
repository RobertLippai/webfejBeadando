package com.robertlippai.TreasureHunter.service;


import com.robertlippai.TreasureHunter.dao.request.SignUpRequest;
import com.robertlippai.TreasureHunter.dao.request.SigninRequest;
import com.robertlippai.TreasureHunter.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
