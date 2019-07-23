package model;

public class Order {

  private String address;
  private Long userId;
  private Long boxId;

  public Order(Long userId, String address, Long boxId) {
    this.address = address;
    this.userId = userId;
    this.boxId = boxId;

  }

  public String getAddress() {
    return address;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getBoxId() {
    return boxId;
  }
}
