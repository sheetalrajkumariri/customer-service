package Customer.controller;

import Customer.dto.CustomerRequest;
import Customer.dto.CustomerResponse;
import Customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
@Autowired
    private CustomerService customerService;

@PostMapping("/createCustomer")
    public CustomerResponse createCustomer(@RequestBody CustomerRequest request){
    log.info("Start::createCustomer()inside the CustomerController with the Request, {} ", request);
    return customerService.createCustomer(request);
}
@GetMapping("/findCustomerById/{customerId}")
    public CustomerResponse findCustomerById(@PathVariable int  customerId){
    log.info("Strat::findCustomerById()inside the CustomerController with the id, {} ", +customerId);
    return customerService.findCustomerById(customerId);
}
@GetMapping("/findAllCustomer")
    public List<CustomerResponse> findAllCustomer(){
    log.info("Start :: findAllCustomer()inside the CustomerController ");
    return customerService.findAllCustomer();
}
@DeleteMapping("/deleteCustomerById/{customerId}")
    public String deleteCustomerById(@PathVariable int customerId){
    log.info("Start:: deleteCustomerById()inside the CustomerController");
    return customerService.deleteCustomerId(customerId);
}
@DeleteMapping("/deleteAllCustomer")
    public String deleteAllStudent(){
    log.info("Start:: deleteAllCustomer()inside the CustomerController");
    return customerService.deleteAllCustomer();
}
 @PutMapping("/updateCustomer/{customerId}")
    public CustomerResponse updateCustomer(@PathVariable int  customerId, @RequestBody CustomerRequest request){
    log.info("Start:: updateCustomer()inside the CustomerController with id, {} ", customerId);
    return customerService.updateCustomer(customerId,request);
 }
 @GetMapping("/findAllCustomerPages/{page},{size}")
    public Page<CustomerResponse> findAllCustomerPages(@PathVariable int page, @PathVariable int size){
    log.info("Start:: findAllCustomerPages()inside the CustomerController");
    return customerService.findAllCustomerPages(page,size);
 }


}
