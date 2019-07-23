package model;

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

  public void createNewUserBox() {
    shoppingBox = new ShoppingBox();
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

  public Long getBoxId() {
    return shoppingBox.getBoxId();
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

  public int incrementAndGetBoxSize() {
    return shoppingBox.boxSize();
  }

   class ShoppingBox {

    private Long BoxId;
    private int size = 1;

    private ShoppingBox() {
      BoxId = generateBoxId();
    }

     private Long getBoxId() {
       return BoxId;
     }

     private int boxSize() {
     return size++;
     }

     private Long generateBoxId() {
       int max = 9999;
       int min = 1000;
       return Long.valueOf((int) ((Math.random() * ++max) + min));
     }
   }
}
