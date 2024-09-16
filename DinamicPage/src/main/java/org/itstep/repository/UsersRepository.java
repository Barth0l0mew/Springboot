package org.itstep.repository;

import org.itstep.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
