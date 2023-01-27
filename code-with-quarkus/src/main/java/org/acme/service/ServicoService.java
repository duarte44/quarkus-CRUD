package org.acme.service;

import org.acme.entity.Customer;
import org.acme.entity.Servico;
import org.acme.repository.CustomerRepository;
import org.acme.repository.ServicoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ServicoService {

    @Inject
    CustomerRepository customerRepository;
    @Inject
    ServicoRepository servicoRepository;

    public List<Servico> findAllServicos(){
        return servicoRepository.findAll().list();
    }

   public void insert(Servico servico, Long id){
        Customer res = customerRepository.findById(id);
        servico.setCustomer(res);
        servicoRepository.persist(servico);
    }

}
