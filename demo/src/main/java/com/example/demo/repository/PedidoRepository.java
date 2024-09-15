package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Pedido;

// consulta 6
// Qual a quantidade e o percentual de pedidos com o valor total maior que 600, 
// encomendados de maneira presencial nos últimos 8 meses do ano 2023, encomendados
//  por clientes americanos com limite de crédito maior que 600?
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "SELECT COUNT(p.id_pedido) AS quantidade_pedidos, " +
                   "COUNT(p.id_pedido) * 100.0 / (SELECT COUNT(DISTINCT p2.id_cliente) " +
                                                "FROM pedido p2 " +
                                                "WHERE YEAR(p2.data_prazo) = 2023) AS percentual " +
                   "FROM pedido p " +
                   "JOIN item_pedido ip ON p.id_pedido = ip.id_pedido " +
                   "JOIN cliente c ON p.id_cliente = c.id_cliente " +
                   "WHERE p.modo_encomenda = 'presencial' " +
                   "AND c.pais = 'Estados Unidos' AND c.limite_credito > 600 " +
                   "AND YEAR(p.data_prazo) = 2023 AND p.data_prazo >= '2023-05-01' " +
                   "GROUP BY p.id_pedido " +
                   "HAVING SUM(ip.preco * ip.quantidade) > 600", nativeQuery = true)
    List<Object[]> findPedidosByCliente();
}

