package com.example.demo.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Armazem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArmazem;

    @NotNull
    private String nome;

    private String complemento;

    private String bairro;

    private Integer numero;

    private String rua;

    // Getters and Setters
}
