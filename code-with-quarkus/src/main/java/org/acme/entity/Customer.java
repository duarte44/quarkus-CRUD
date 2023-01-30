package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is Required")
    @Size(min= 2, max = 14)
    private String name;

    @NotBlank(message = "Last Name is Required")
    private String lastName;

    @NotNull(message = "Age is Required")
    private Integer age;

    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Servico> servicos;

}
