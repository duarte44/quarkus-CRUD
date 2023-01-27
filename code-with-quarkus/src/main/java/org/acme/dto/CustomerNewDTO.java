package org.acme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.entity.Customer;
import org.acme.entity.Servico;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerNewDTO {

    private Long id;

    private String name;

    private String lastName;

    private Integer age;

    private String email;

    private String item;

    private Integer quantidade;

    private Double preco;



}
