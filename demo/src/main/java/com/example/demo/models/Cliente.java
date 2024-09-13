package com.example.demo.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotNull
    private String nome;

    private LocalDate dataCadastro;

    private Double limiteCredito;

    private String pais;

    private String estado;

    private String cidade;

    private String complemento;

    private String bairro;

    private Integer numero;

    private String rua;

    // Getters and Setters
}
