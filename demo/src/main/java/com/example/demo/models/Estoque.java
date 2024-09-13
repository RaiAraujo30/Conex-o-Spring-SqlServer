package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstoque;

    @NotNull
    private Integer quantidade;

    private String codigo;

    @ManyToOne
    @JoinColumn(name = "idArmazem", nullable = false)
    @NotNull
    private Armazem armazem;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    @NotNull
    private Produto produto;

    // Getters and Setters
}
