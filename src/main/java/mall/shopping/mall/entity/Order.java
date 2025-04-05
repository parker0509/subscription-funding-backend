package mall.shopping.mall.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity

@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 하나의 주문은 하나의 고객
    @JoinColumn(name = "custom_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Project> projects;

    private LocalDateTime localDateTime;

    private String status;

    public Order(Long id, Customer customer, List<Project> projects, LocalDateTime localDateTime, String status) {
        this.id = id;
        this.customer = customer;
        this.projects = projects;
        this.localDateTime = localDateTime;
        this.status = status;
    }

}
