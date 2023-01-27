package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private Integer quantidade;
    private Double preco;

    public Double getTotal(){
        return this.preco * this.quantidade;
    }

    @ManyToOne
   // @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Servico(Long id, String item, Integer quantidade, Double preco) {
        this.id = id;
        this.item = item;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
