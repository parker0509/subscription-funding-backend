package mall.shopping.mall.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "prodcut_id")
    private Project project;

    private int quantity;
    private BigDecimal bigDecimal;

    public OrderItem(Long id, Order order, Project project, int quantity, BigDecimal bigDecimal) {
        this.id = id;
        this.order = order;
        this.project = project;
        this.quantity = quantity;
        this.bigDecimal = bigDecimal;
    }


}
