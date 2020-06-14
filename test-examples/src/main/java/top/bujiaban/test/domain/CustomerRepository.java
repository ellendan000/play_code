package top.bujiaban.test.domain;

import java.util.List;

public interface CustomerRepository {
    List<Customer> fetchAllCustomerWithProjects();

    void save(Customer customer);
}
