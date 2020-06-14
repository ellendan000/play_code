package top.bujiaban.test.application;

import org.springframework.stereotype.Service;
import top.bujiaban.test.domain.Customer;
import top.bujiaban.test.domain.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
