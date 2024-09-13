package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFornecedor;

    @NotNull
    private String tipoFornecedor;

    private String nome;

    private String pais;

    private String complemento;

    private String bairro;

    private Integer numero;

    private String rua;

    // Getters and Setters
}
