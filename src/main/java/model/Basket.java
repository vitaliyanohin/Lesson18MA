package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user_basket_Hibernate")
public class Basket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "basket_id")
  private Long basket_id;


  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  @Column(name = "available")
  private String available;

  @ManyToMany(fetch = FetchType.EAGER ,cascade = { CascadeType.MERGE })
  @JoinTable(
          name = "product_basket_hib",
          joinColumns = { @JoinColumn(name = "basket_id") },
          inverseJoinColumns = { @JoinColumn(name = "product_id") }
  )
private List<Product> products;



  public Basket() {
  }

  public Basket(List<Product> products , User user) {
    this.products = products;
    this.user = user;
    this.available = "true";
  }

  public String getAvailable() {
    return available;
  }

  public void setAvailable(String available) {
    this.available = available;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public void addProducts(Product products) {
    this.products.add(products);
  }
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getBasket_id() {
    return basket_id;
  }

  public void setBasket_id(Long basket_id) {
    this.basket_id = basket_id;
  }

  public String getAvailble() {
    return available;
  }

  public void setAvailble(String available) {
    this.available = available;
  }
}
