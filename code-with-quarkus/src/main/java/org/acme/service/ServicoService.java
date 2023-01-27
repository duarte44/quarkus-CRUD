package org.acme.service;

import org.acme.entity.Customer;
import org.acme.entity.Servico;
import org.acme.repository.ServicoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ServicoService {

    @Inject
    ServicoRepository servicoRepository;

    public List<Servico> findAllServicos(){
        return servicoRepository.findAll().list();
    }

    public void addCustomer(Servico servico){
        servicoRepository.persist(servico);
    }
}
