package org.acme.controller;

import org.acme.entity.Customer;
import org.acme.entity.Servico;
import org.acme.service.ServicoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/api/servico")
public class ServicoController {

    @Inject
    ServicoService servicoService;

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
    public void addCustomer(Servico servico){
        servicoService.addCustomer(servico);
    }

}
