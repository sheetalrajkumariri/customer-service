package Customer.service;

import Customer.controller.CustomerController;
import Customer.dto.CustomerRequest;
import Customer.dto.CustomerResponse;
import Customer.entity.CustomerEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setAge(20);
        request.setName("Sheetal");

        CustomerEntity savedCustomer = new CustomerEntity();
        savedCustomer.setId(1);
        savedCustomer.setAge(20);
        savedCustomer.setName("Sheetal");

        CustomerResponse response = new CustomerResponse();
        response.setId(1);
        response.setAge(20);
        response.setName("Sheetal");

        Mockito.when(customerService.createCustomer(any(CustomerRequest.class))).thenReturn(response);

        mockMvc.perform(post("/customer/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.name").value("Sheetal"));
    }

    @Test
    public void findCustomerById() throws Exception {
        CustomerResponse response = new CustomerResponse();
        response.setId(1);
        response.setAge(20);
        response.setName("Sheetal");
        CustomerEntity customerEntity = new CustomerEntity(1, 20, "Sheetal");
        when(customerService.findCustomerById(1)).thenReturn(response);
        mockMvc.perform(get("/customer/findCustomerById/{customerId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.name").value("Sheetal"));

    }

    @Test
    public void findAllCustomer() throws Exception {
//        CustomerEntity list1 = new CustomerEntity(1, 20, "Sheetal");
//        CustomerEntity list2 = new CustomerEntity(2, 22, "Arnav");
//        List<CustomerEntity> mockList = Arrays.asList(list1, list2);

        CustomerResponse response1 = new CustomerResponse();
        response1.setId(1);
        response1.setAge(20);
        response1.setName("Sheetal");

        CustomerResponse response2 = new CustomerResponse();
        response2.setId(2);
        response2.setAge(21);
        response2.setName("Arnav");

        List<CustomerResponse> responseList = Arrays.asList(response1, response2);


        when(customerService.findAllCustomer()).thenReturn(responseList);
        mockMvc.perform(get("/customer/findAllCustomer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].name").value("Sheetal"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].age").value(21))
                .andExpect(jsonPath("$[1].name").value("Arnav"));

    }

    @Test
    public void deleteCustomerById() throws Exception {
        //CustomerEntity customerEntity = new CustomerEntity(1, 20, "Sheetal");
        when(customerService.deleteCustomerId(1))
                .thenReturn("Customer deleted successfully");

        mockMvc.perform(delete("/customer/deleteCustomerById/{customerId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer deleted successfully"));
    }
    @Test
    public void deleteAllCustomer()throws Exception {
        when(customerService.deleteAllCustomer())
                .thenReturn(" All customer deleted successfully");

        mockMvc.perform(delete("/customer/deleteAllCustomer"))
                .andExpect(status().isOk())
                .andExpect(content().string(" All customer deleted successfully"));
    }
    @Test
    public void updateCustomer() throws Exception {
        int customerId = 1;
        CustomerRequest request = new CustomerRequest();
        request.setAge(20);
        request.setName("Sheetal");

//        CustomerEntity updateCustomer = new CustomerEntity();
//        updateCustomer.setId(1);
//        updateCustomer.setAge(20);
//        updateCustomer.setName("Sheetal");

        CustomerResponse response = new CustomerResponse();
        response.setId(1);
        response.setAge(20);
        response.setName("Sheetal");

        when(customerService.updateCustomer(eq(customerId), any(CustomerRequest.class)))
                .thenReturn(response);
        mockMvc.perform(put("/customer/updateCustomer/{customerId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sheetal"))
                .andExpect(jsonPath("$.age").value(20));



    }




}


