package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do item necessário")
    private String item;
    @NotNull(message = "Quantidade não pode ser nula")
    private Integer quantidade;
    @NotNull(message = "Preço não pode ser nulo")
    private Double preco;

    public Double getTotal(){
        return this.preco * this.quantidade;
    }

    public String getResultado(){
        if(this.getTotal() < 2.0){
            return "Valor abaixo de 2R$ Reais";
        }
        else if (this.getTotal() < 10) {
            return "Valor acima de 2R$ mas abaixo de 10R$";
        }
        else{
            return "Valor acima de 10R$";
        }
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;


  }

