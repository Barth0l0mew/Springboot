package org.itstep;

import org.itstep.model.ModelCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelCar, Long> {
} 