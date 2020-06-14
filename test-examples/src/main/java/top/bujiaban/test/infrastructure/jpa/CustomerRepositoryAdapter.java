package top.bujiaban.test.infrastructure.jpa;

import org.springframework.stereotype.Repository;
import top.bujiaban.test.domain.Customer;
import top.bujiaban.test.domain.CustomerRepository;

import java.util.List;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {
    private CustomerJpaRepository repository;

    public CustomerRepositoryAdapter(CustomerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> fetchAllCustomerWithProjects() {
        return repository.findAll();
    }

    @Override
    public void save(Customer customer) {
        repository.save(customer);
    }
}
