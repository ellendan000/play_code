package top.bujiaban.test.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import top.bujiaban.test.domain.Customer;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}
