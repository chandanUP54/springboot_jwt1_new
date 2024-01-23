
package com.example.securitymain.services;

import com.example.securitymain.dto.JwtAuthenticationResponse;
import com.example.securitymain.dto.RefreshTokenRequest;
import com.example.securitymain.dto.SignUpRequest;
import com.example.securitymain.dto.SigninRequest;
import com.example.securitymain.entities.Users;
import com.example.securitymain.exception.UserException;

public interface AuthenticationService {

   Users signup(SignUpRequest signUpRequest) ;
  public JwtAuthenticationResponse signin(SigninRequest signinRequest) ;
  public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
Users findUserProfileByJwt(String jwt) throws UserException;
}
