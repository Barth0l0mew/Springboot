package org.itstep;

import org.itstep.firm.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmRepository extends JpaRepository<Firm, Long> {
} 