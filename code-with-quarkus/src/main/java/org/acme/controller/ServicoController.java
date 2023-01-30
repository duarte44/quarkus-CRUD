package org.acme.controller;

import org.acme.entity.Customer;
import org.acme.entity.Servico;
import org.acme.exceptions.ResponseError;
import org.acme.service.ServicoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Path("/api/servico")
public class ServicoController {

    @Inject
    ServicoService servicoService;

    @Inject
    Validator validator;

    @GET
    public List<Servico> retrieveCustomers(){

        List<Servico> servicos = new ArrayList<>();
        try {
            servicos = servicoService.findAllServicos();
        } catch (Exception e){
            e.printStackTrace();
        }

        return servicos;
    }

    @POST
    @Transactional
    @Path("{id}")
    public Response insert(Servico servico, @PathParam("id") Long id){


        Set<ConstraintViolation<Servico>> violations = validator.validate(servico);
        if(!violations.isEmpty()){
            return ResponseError.createFromValidation(violations).withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
            // se a lista de erros não estiver vazia, retorna 422
        }

        servicoService.insert(servico, id);

        return Response.status(Response.Status.CREATED.getStatusCode()).build();

    }
}
