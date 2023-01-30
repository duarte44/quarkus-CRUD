package org.acme.service;

import org.acme.entity.Customer;
import org.acme.entity.Servico;
import org.acme.repository.CustomerRepository;
import org.acme.repository.ServicoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    ServicoRepository servicoRepository;


    public List<Customer> findAllCustomers(){
       return customerRepository.findAll().list();
    }

    public void addCustomer(Customer customer){

        customerRepository.persist(customer);
    }


    public Response findById(Long id){
       var respo = customerRepository.findById(id);
        return Response.ok(respo).build();

    }

    public Response delete(Long id){
        var respo = customerRepository.deleteById(id);
        return Response.ok(respo).build();
    }

    public Response update(Long id, Customer newCustomer){
        Customer customer = customerRepository.findById(id);

        if(customer != null){
            customer.setName(newCustomer.getName());
            customer.setLastName(newCustomer.getLastName());
            customer.setEmail(newCustomer.getEmail());
            customer.setAge(newCustomer.getAge());
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
