package org.itstep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "users")
public class Users {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String password;

}
