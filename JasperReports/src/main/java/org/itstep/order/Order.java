package org.itstep.order;

import lombok.Data;
import org.itstep.customer.Customer;
import org.itstep.employer.Employer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "employee_id")
    Employer employer;
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    Customer customer;
    private Date orderDate;
    private Date shippedDate;
    private String shipName;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipPostalCode;
    private String shipCountry;
    private Double shippingFee;
    private String paymentType;
    private Date paidDate;
    private String orderStatus;


} 