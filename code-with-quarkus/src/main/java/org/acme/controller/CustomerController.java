package org.acme.controller;



import org.acme.entity.Customer;
import org.acme.entity.Servico;
import org.acme.service.CustomerService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api/customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id){
        var reposta = customerService.findById(id);
        return reposta;
    }

    @GET
    public List<Customer> retrieveCustomers(){
        List<Customer> customers = new ArrayList<>();
        try {
            customers = customerService.findAllCustomers();
        } catch (Exception e){
            e.printStackTrace();
        }

        return customers;
    }

    @POST
    @Transactional
    public void insert(Customer customer){
        customerService.addCustomer(customer);
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        var resposta = customerService.delete(id);

    }


}
