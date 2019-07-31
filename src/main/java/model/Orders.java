package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_order_hibernate")
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id")
  private User userId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "basket_id")
  private Basket boxId;

  @Column(name = "address")
  private String address;

  public Orders() {
  }

  public Orders(User userId, Basket boxId, String address) {
    this.userId = userId;
    this.boxId = boxId;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  public Basket getBoxId() {
    return boxId;
  }

  public void setBoxId(Basket boxId) {
    this.boxId = boxId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
