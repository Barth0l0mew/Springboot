package org.itstep.order_details;

import lombok.Data;
import org.itstep.order.Order;
import org.itstep.product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "order_details")
@IdClass(OrderDetailsId.class)
public class OrderDetails {
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    @Id
    private Order order;
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    @Id
    private Product product;
    private Double quantity;
    private Double unitPrice;
    private Double discount;
    private String orderDetailStatus;
    private Date dateAllocated;
}

@Data
class OrderDetailsId implements Serializable {
    private Order order;
    private Product product;
} 