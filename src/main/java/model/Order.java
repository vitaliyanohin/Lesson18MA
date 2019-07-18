package model;

import java.util.ArrayList;

public class Order {

  private String address;
  private long userId;
  private long orderId;
  private ArrayList<Long> box;

  public Order(long userId, String address, ArrayList<Long> box) {
    this.address = address;
    this.userId = userId;
    this.box = box;
    orderId = generateOrderId();
  }

  public String getAddress() {
    return address;
  }

  public long getUserId() {
    return userId;
  }

  public long getOrderId() {
    return orderId;
  }

  public ArrayList<Long> getBox() {
    return box;
  }

  private int generateOrderId() {
    int max = 9998;
    int min = 1000;
    return (int) (Math.random() * ++max) + min;

  }
}
