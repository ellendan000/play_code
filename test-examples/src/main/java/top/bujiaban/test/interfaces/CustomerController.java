package top.bujiaban.test.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.bujiaban.test.application.CustomerService;
import top.bujiaban.test.domain.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CustomerRequest request){
        Customer customer = CustomerMapper.MAPPER.toCustomer(request);
        customerService.createCustomer(customer);
    }
}
