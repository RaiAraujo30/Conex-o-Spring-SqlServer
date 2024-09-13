package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Armazem;

public interface ArmazemRepository extends JpaRepository<Armazem, Long> {
    
     @Query(value = "SELECT A.nome, A.complemento, A.bairro, A.numero, A.rua " +
                   "FROM armazem A, estoque E, produto P, categoria C " +
                   "WHERE C.nome = 'eletrônicos' AND " +
                   "C.id_categoria = P.id_categoria AND " +
                   "P.id_produto = E.id_produto AND " +
                   "E.id_armazem = A.id_armazem " +
                   "GROUP BY A.nome, A.complemento, A.bairro, A.numero, A.rua " +
                   "HAVING COUNT(E.id_estoque) >= 2 AND " +
                   "COUNT(E.id_estoque) < 1000", nativeQuery = true)
    List<Object[]> findArmazensByElectronics();
}
