package model;

import java.util.Optional;

public class User {
  private Long id;
  private String email;
  private String password;
  private String role;
  private Optional<Long> basketId = Optional.empty();

  public User(Long id, String email, String password, String role) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.role = role;
}

  public User(String email, String password, String role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public Optional<Long> getBasketId() {
    return basketId;
  }

  public void setBasketId(Long basketId) {
    this.basketId = Optional.ofNullable(basketId);
  }

  public void dropBasketId() {
    basketId = Optional.empty();
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
