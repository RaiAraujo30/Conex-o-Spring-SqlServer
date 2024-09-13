package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProdutoTraducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    @NotNull
    private Produto produto;

    @NotNull
    private String idioma;

    private String nome;

    private String descricao;

    // Getters and Setters
}

