package com.example.demo.DTO;

public class SignUpRequest {
  private String firstName;
  private String lastName;
  private String email;
  private String password;

  private boolean isAdmin;

  public SignUpRequest(){}

  public SignUpRequest(String firstName, String lastName, String email, String password, boolean isAdmin){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  public String getFirstName(){
    return firstName;
  }

  public void setFirstName(String firstName){
    this.firstName = firstName;
  }

  public String getLastName(){
    return lastName;
  }

  public void setLastName(String lastName){
    this.lastName = lastName;
  }

  public String getEmail(){
    return email;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public String getPassword(){
    return password;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public boolean isAdmin(){
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin){
    this.isAdmin = isAdmin;
  }

}