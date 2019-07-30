package model;

public class MyOrder {

  private Long OrderId;
  private String address;
  private String user;
  private String product;
  private String description;
  private Double price;

  public MyOrder(Long orderId, String address, String user, String product, String description,
                 Double price) {
    this.OrderId = orderId;
    this.address = address;
    this.user = user;
    this.product = product;
    this.description = description;
    this.price = price;
  }

  public Long getOrderId() {
    return OrderId;
  }

  public String getAddress() {
    return address;
  }

  public String getUser() {
    return user;
  }

  public String getProduct() {
    return product;
  }

  public String getDescription() {
    return description;
  }

  public Double getPrice() {
    return price;
  }
}
