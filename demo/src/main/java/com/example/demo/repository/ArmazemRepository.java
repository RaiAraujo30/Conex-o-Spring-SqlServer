package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Armazem;

public interface ArmazemRepository extends JpaRepository<Armazem, Long> {
    // consulta 3
    // Liste o nome e o endereço dos armazéns que possuem pelo menos 200 estoques de produtos
    //  da categoria “eletrodomésticos” e que possuem menos de 1000 estoques no total.
     @Query(value = "SELECT A.nome, A.complemento, A.bairro, A.numero, A.rua " +
                   "FROM armazem A, estoque E, produto P, categoria C " +
                   "WHERE C.nome = 'eletrodomésticos' AND " + 
                   "C.id_categoria = P.id_categoria AND " +
                   "P.id_produto = E.id_produto AND " +
                   "E.id_armazem = A.id_armazem " +
                   "GROUP BY A.nome, A.complemento, A.bairro, A.numero, A.rua " +
                   "HAVING COUNT(E.id_estoque) >= 200 AND " + 
                   "COUNT(E.id_estoque) < 1000", nativeQuery = true)
    List<Object[]> findArmazensByElectronics();
}
