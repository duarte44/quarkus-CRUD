package org.acme.service;

import org.acme.entity.Customer;
import org.acme.entity.Servico;
import org.acme.repository.CustomerRepository;
import org.acme.repository.ServicoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
        servicoRepository.persist(customer.getServicos());
    }

    public Response findById(Long id){
       var respo = customerRepository.findById(id);
        return Response.ok(respo).build();

    }

    public Response delete(Long id){
        var respo = customerRepository.deleteById(id);
        return Response.ok(respo).build();
    }


}
