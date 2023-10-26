package com.example.demo.Controllers;

import com.example.demo.DTO.JwtAuthenticationResponse;
import com.example.demo.DTO.SignUpRequest;
import com.example.demo.DTO.SignInRequest;
import com.example.demo.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthenticationService authenticationService;

  @Autowired
  public AuthController(AuthenticationService authenticationService){
    this.authenticationService = authenticationService;
  }

  @PostMapping("/signup")
  public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request){
    return ResponseEntity.ok(authenticationService.signup(request));
  }

  @PostMapping("/signin")
  public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request){
    return ResponseEntity.ok(authenticationService.signin(request));
  }
}
