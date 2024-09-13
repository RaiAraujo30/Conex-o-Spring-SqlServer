package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @ManyToOne
    @JoinColumn(name = "idFornecedor", nullable = false)
    @NotNull
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    @NotNull
    private Categoria categoria;

    private LocalDate dataGarantia;

    private String status;

    @NotNull
    private Double precoVenda;

    private Double precoVendaMin;

    // Getters and Setters
}
