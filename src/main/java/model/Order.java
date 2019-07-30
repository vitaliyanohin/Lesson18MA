package model;

import java.util.Optional;

public class Order {

  private String address;
  private Long userId;
  private Optional<Long> boxId;

  public Order(Long userId, String address, Optional<Long> boxId) {
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

  public Optional<Long> getBoxId() {
    return boxId;
  }
}
