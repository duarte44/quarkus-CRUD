package org.acme.controller;



import lombok.AllArgsConstructor;
import org.acme.entity.Customer;
import org.acme.exceptions.ResponseError;
import org.acme.service.CustomerService;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;

import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Path("/api/customer")
@AllArgsConstructor
public class CustomerController {

    @Inject
    Validator validator;
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
    public Response insert (Customer customer){

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        if(!violations.isEmpty()){
            return ResponseError.createFromValidation(violations).withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
            // se a lista de erros não estiver vazia, retorna 422
        }

        customerService.addCustomer(customer);

        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){

        var user = customerService.findById(id);
        if(user != null){
            customerService.delete(id);
            return Response.noContent().build();

        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void update(@PathParam("id") Long id, Customer newCustomer){

        customerService.update(id, newCustomer);
    }

}
