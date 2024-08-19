package org.itstep.firm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.model.ModelCar;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Firm {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private String idSymbol;
    private String name;
    private String cyrillicName;
    private boolean popular;
    private String country;
    @OneToMany(
            cascade = CascadeType.PERSIST, orphanRemoval = true
    )
    private Set<ModelCar> models;
}
