package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private LocalDate dataPrazo;

    private String status;

    private String modoEncomenda;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    @NotNull
    private Cliente cliente;
    // Getters and Setters
}
