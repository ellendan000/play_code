package top.bujiaban.test.interfaces;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.bujiaban.test.domain.Customer;
import top.bujiaban.test.domain.Project;

@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    default Customer toCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getCustomerName());

        Project project = new Project();
        project.setName(request.getDefaultProjectName());

        customer.addProject(project);
        return customer;
    }
}
