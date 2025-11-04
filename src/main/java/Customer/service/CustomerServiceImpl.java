package Customer.service;

import Customer.dto.CustomerRequest;
import Customer.dto.CustomerResponse;
import Customer.entity.CustomerEntity;
import Customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        log.info("Start:: createCustomer()inside the CustomerServiceImpl with the request, {} ", request);
        CustomerEntity customerEntity = modelMapper.map(request,CustomerEntity.class);
        customerEntity = customerRepository.save(customerEntity);
        log.info("End::createCustomer()inside the CustomerServiceImpl with the request, {} ", request);
        return modelMapper.map(customerEntity, CustomerResponse.class);
    }

    @Override
    public CustomerResponse findCustomerById(int customerId) {
        log.info("Start::findCustomerById()inside the CustomerServiceImpl with the id, {} ", customerId);
        CustomerEntity customerEntity =  customerRepository.findById(customerId).get();
        log.info("End::findCustomerById()inside the CustomerServiceImpl with the id, {} ", customerId);
        return modelMapper.map(customerEntity,CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> findAllCustomer() {
        log.info("Star::findAllCustomer()inside the CustomerServiceImpl");
        List<CustomerEntity> customerEntity = customerRepository.findAll();
        return customerEntity.stream().map(customerEntity1 -> modelMapper.map(customerEntity1,CustomerResponse.class)).toList();
    }

    @Override
    public String deleteCustomerId(int customerId) {
        log.info("Start:: deleteCustomerById()inside the CustomerServiceImpl");
        customerRepository.deleteById(customerId);
        return "Customer deleted successfully";
    }

    @Override
    public String deleteAllCustomer() {
        log.info("Start:: deleteAllCustomer()inside the CustomerServiceImpl");
        customerRepository.deleteAll();
        return " All customer deleted successfully";
    }

    @Override
    public CustomerResponse updateCustomer(int customerId, CustomerRequest request) {
        log.info("Start:: updateCustomer()inside the CustomerServiceImpl with the id, {} ", customerId);
        CustomerEntity entity = customerRepository.findById(customerId).get();
        modelMapper.map(request,entity);
        CustomerEntity updateCustomer = customerRepository.save(entity);
        log.info("End:: updateCustomer()inside the CustomerServiceImpl with the id, {} ", customerId);

        return modelMapper.map(updateCustomer,CustomerResponse.class);
    }

    @Override
    public Page<CustomerResponse> findAllCustomerPages(int page, int size) {
        log.info("Start :: findAllCustomerPages()inside the CustomerServiceImpl ");
        Pageable pageable = PageRequest.of(page,size);
        Page<CustomerEntity> entityPage = customerRepository.findAll(pageable);
        log.info("End :: findAllCustomerPages()inside the CustomerServiceImpl ");
        return entityPage.map(customerEntity -> modelMapper.map(customerEntity, CustomerResponse.class));
    }
}
