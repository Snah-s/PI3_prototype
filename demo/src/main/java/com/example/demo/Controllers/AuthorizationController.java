package com.example.demo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class AuthorizationController {
  
  @Secured("ADMIN")
  @GetMapping("/admin")
  public ResponseEntity<String> hello(){
    return ResponseEntity.ok("Hello Admin");
  }

  @Secured({"ADMIN", "USER"})
  @GetMapping("/user")
  public ResponseEntity<String> helloUser(){
    return ResponseEntity.ok("Hello Use, of course Admin too :)!");
  }
}
