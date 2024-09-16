package org.itstep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name="product")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private String discription;
    private int price;

}
