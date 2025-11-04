package Customer.service;

import Customer.dto.CustomerRequest;
import Customer.dto.CustomerResponse;
import Customer.entity.CustomerEntity;
import Customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

//    public CustomerServiceTest() {
//        MockitoAnnotations.openMocks(this);
//
//    }


    @Test
    void testCreateCustomer() {

        CustomerRequest request = new CustomerRequest();
        request.setName("Mahi");
        request.setAge(24);

        CustomerEntity entity = new CustomerEntity();
        entity.setName("Mahi");
        entity.setAge(24);

        CustomerResponse response = new CustomerResponse();
        response.setName("Mahi");
        response.setAge(24);

        when(modelMapper.map(request, CustomerEntity.class)).thenReturn(entity);
        when(customerRepository.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, CustomerResponse.class)).thenReturn(response);

        CustomerResponse result = customerService.createCustomer(request);

        assertNotNull(result);
        assertEquals("Mahi", result.getName());
        assertEquals(24, result.getAge());


   }

    @Test
    void testFindCustomerById(){
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1);

        CustomerResponse response = new CustomerResponse();
        response.setId(1);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when( modelMapper.map(customer,CustomerResponse.class)).thenReturn(response);

        CustomerResponse found = customerService.findCustomerById(1);

        assertThat(found.getId()).isEqualTo(1);

    }
    @Test
    void testDeleteCustomerById(){
        CustomerEntity entity = new CustomerEntity();
        entity.setId(7);

        CustomerResponse response = new CustomerResponse();
        response.setId(7);

        doNothing().when(customerRepository).deleteById(7);
        String results = customerService.deleteCustomerId(7);

        assertEquals("Customer deleted successfully", results);


    }
    @Test
    void testFindAllCustomer(){
        CustomerEntity list1 = new CustomerEntity(1,23,"Sheetal");
        CustomerEntity list2  =new CustomerEntity(2,26,"Mahi");
        List<CustomerEntity> mockList = Arrays.asList(list1,list2);
        CustomerResponse response1 = new CustomerResponse();
        response1.setId(1);
        response1.setName("Sheetal");
        CustomerResponse response2 = new CustomerResponse();
        response2.setId(2);
        response2.setName("Mahi");
        List<CustomerResponse> responseList = Arrays.asList(response1,response2);

        when(customerRepository.findAll()).thenReturn(mockList);
        when(modelMapper.map(list1, CustomerResponse.class)).thenReturn(response1);
        when(modelMapper.map(list2, CustomerResponse.class)).thenReturn(response2);

        List<CustomerResponse> entity = customerService.findAllCustomer();

        assertNotNull(entity);
        assertEquals(2, entity.size());
        assertEquals("Sheetal", entity.get(0).getName());
        assertEquals("Mahi", entity.get(1).getName());

    }
    @Test
    void testDeleteAllCustomer(){
        CustomerEntity customerEntity1 = new CustomerEntity(1,23,"Sheetal");
        CustomerEntity customerEntity2 = new CustomerEntity(2,24,"Ruhi");
        CustomerEntity customerEntity3 = new CustomerEntity(3,25,"Rohit");


        CustomerResponse response1 = new CustomerResponse();
        response1.setId(1);
        response1.setName("Sheetal");
        response1.setAge(23);

        CustomerResponse response2 = new CustomerResponse();
        response2.setId(2);
        response2.setName("Ruhi");
        response2.setAge(24);

        CustomerResponse response3 = new CustomerResponse();
        response3.setId(3);
        response3.setName("Rohit");
        response3.setAge(25);


        doNothing().when(customerRepository).deleteAll();
        String result = customerService.deleteAllCustomer();

        assertEquals(" All customer deleted successfully", result);


    }
//@Test
//void testDeleteAllCustomer() {
//    doNothing().when(customerRepository).deleteAll();
//
//    String result = customerService.deleteAllCustomer();
//
//    assertEquals(" All customer deleted successfully", result);
//
//}
    @Test
    void testUpdateCustomer(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1);
        customerEntity.setName("Mahi");
        customerEntity.setAge(19);

        CustomerRequest request = new CustomerRequest();
        request.setName("Priya");
        request.setAge(20);

        CustomerResponse response = new CustomerResponse();
        response.setName("Priya");
        response.setAge(20);

        //when(customerRepository.findById(1)).thenReturn(Optional.of(customerEntity));
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        when(modelMapper.map(request,CustomerEntity.class)).thenReturn(customerEntity);
        when(modelMapper.map(customerEntity,CustomerResponse.class)).thenReturn(response);

        CustomerResponse result = customerService.createCustomer(request);

        assertNotNull(customerEntity);
        assertEquals("Priya", result.getName());
        assertEquals(20, result.getAge());




    }


}
