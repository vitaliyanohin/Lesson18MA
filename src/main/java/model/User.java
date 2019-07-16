package model;

import java.util.ArrayList;

public class User {
  private Long id;
  private String email;
  private String password;
  private String role;
  private ShoppingBox shoppingBox;

  public User(Long id, String email, String password, String role) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.role = role;
    shoppingBox = new ShoppingBox();
  }

  public User(String email, String password, String role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
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

  public void addInBox(Long id) {
    shoppingBox.add(id);
  }

  public ArrayList<Long> getBox() {
    return shoppingBox.products;
  }

  public int boxSize() {
    return shoppingBox.size();
  }

   class ShoppingBox {

    private ArrayList<Long> products;

    private ShoppingBox() {
      products = new ArrayList<>();
    }

    private void add(Long id) {
      products.add(id);
    }

    private int size() {
      return products.size();
    }
  }
}
