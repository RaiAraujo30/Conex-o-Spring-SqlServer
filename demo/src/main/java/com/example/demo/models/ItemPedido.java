package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    @NotNull
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    @NotNull
    private Produto produto;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Double preco;

    // Getters and Setters
}
