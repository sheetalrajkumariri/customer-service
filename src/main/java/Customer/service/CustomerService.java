package Customer.service;

import Customer.dto.CustomerRequest;
import Customer.dto.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse findCustomerById(int customerId);

    List<CustomerResponse> findAllCustomer();

    String deleteCustomerId(int customerId);

    String deleteAllCustomer();

    CustomerResponse updateCustomer(int customerId, CustomerRequest request);

    Page<CustomerResponse> findAllCustomerPages(int page, int size);
}
