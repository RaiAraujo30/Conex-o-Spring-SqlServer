package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Cliente;
// consulta9
// Qual é a quantidade e o percentual de clientes que realizaram compras no ano 2023,
//  que possuem limite de crédito maior que 700 e residem nos estados unidos? 
//  (a porcentagem deve ser calculada em relação a quantidade total de clientes que realizaram compras em 2023)
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "SELECT COUNT(DISTINCT c.id_cliente) AS quantidade_clientes, " +
    "CASE " +
    "    WHEN (SELECT COUNT(DISTINCT c2.id_cliente) " +
    "          FROM cliente c2 " +
    "          JOIN pedido p2 ON c2.id_cliente = p2.id_cliente " +
    "          WHERE YEAR(p2.data_prazo) = 2023) = 0 THEN 0 " +
    "    ELSE (COUNT(DISTINCT c.id_cliente) * 100.0 / " +
    "          (SELECT COUNT(DISTINCT c2.id_cliente) " +
    "           FROM cliente c2 " +
    "           JOIN pedido p2 ON c2.id_cliente = p2.id_cliente " +
    "           WHERE YEAR(p2.data_prazo) = 2023)) " +
    "END AS percentual_clientes " +
    "FROM cliente c " +
    "JOIN pedido p ON c.id_cliente = p.id_cliente " +
    "WHERE YEAR(p.data_prazo) = 2023 " +
    "AND c.limite_credito > 700 " +
    "AND c.pais = 'Estados Unidos'", nativeQuery = true)
     List<Object[]> findClientesByPedidos();

}
